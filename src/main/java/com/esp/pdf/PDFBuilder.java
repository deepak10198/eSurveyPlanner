package com.esp.pdf;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.esp.dto.*;

public  class PDFBuilder extends AbstractITextPdfView  {
	
 protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
	
	 	Paragraph start, end, description, surveyCount;	
	 	
	 	 String URL="logo.png";
	        Image image = Image.getInstance(URL);
	        
	        doc.add(image);
	        doc.getPageNumber();
	        

	 	
	 	
	 	SurveyUIDTO survey = (SurveyUIDTO) model.get("survey");
	 	Date date = new Date();
	 	String DATE_FORMAT = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		dateForm.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		String today = dateForm.format(date);
		SimpleDateFormat ft =  new SimpleDateFormat (DATE_FORMAT,Locale.getDefault());
		
		
		Date strdate = ft.parse(survey.getSurveystart().toString());
		Date enddate = ft.parse(survey.getSurveyend().toString());
		
		
		doc.addTitle(survey.getSurveyName());
		doc.addCreationDate();
		
		doc.add(Chunk.NEWLINE);
		
		Paragraph title= new Paragraph(survey.getSurveyName().toUpperCase()+"\n",FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Font.BOLD,BaseColor.BLACK));
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(Chunk.NEWLINE);
		
	 	
	 	
	 	description = new Paragraph(survey.getSurveyDesc(), FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK));
	 	doc.add(description);
	 	doc.add(Chunk.NEWLINE);
	 		 
	 	start = new Paragraph("Start Date: \t "+dateFormat.format(strdate) , FontFactory.getFont(FontFactory.HELVETICA, 11,BaseColor.BLACK ));
	 	end = new Paragraph("End Date : \t "+dateFormat.format(enddate), FontFactory.getFont(FontFactory.HELVETICA, 11,BaseColor.BLACK ));
	 		
	 	doc.add(start);
	 	doc.add(end);
		doc.add(new Paragraph("\n"+"Report Generated on :\t\t"+today,FontFactory.getFont(FontFactory.HELVETICA, 12,BaseColor.RED )));
	 	doc.add(Chunk.NEWLINE);
	 	
	 	doc.add(new Paragraph("Current Status:\t "+survey.getPublished(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, BaseColor.BLUE )));
	 
	 	doc.add(Chunk.NEWLINE);
	 	surveyCount = new Paragraph("Total number of users :\t \t"+survey.getSurveyCount(),FontFactory.getFont(FontFactory.TIMES,12,Font.BOLD));
	 	doc.add(surveyCount);
	 	doc.add(Chunk.NEWLINE);

	 	
	 	PdfPTable table = new PdfPTable(1);
	 	table.setWidthPercentage(100);
	 	
	 	 PdfPCell cell;
	        // we add a cell with colspan 3
	        cell = new PdfPCell(new Phrase("Record",FontFactory.getFont(FontFactory.TIMES_ROMAN, 18,Font.BOLD,BaseColor.WHITE)));
	        //cell.setColspan(3);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setPadding(10.0f);
	      
	        cell.setBackgroundColor(BaseColor.BLACK);
	        table.addCell(cell);
	    	doc.add(table);
	      
	    
	     PdfPTable questionTable = new PdfPTable(3);
		 
	     questionTable.setWidthPercentage(100); 
	     questionTable.setTotalWidth(new float[]{ 20, 144, 30 });
	     questionTable.setSpacingBefore(15f);
	     
	     PdfPCell cellHeader;
	     cellHeader = new PdfPCell(new Phrase("S No."));
	     cellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
	     cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	     cellHeader.setPadding(6.0f);
	     questionTable.addCell(cellHeader);
	     cellHeader = new PdfPCell(new Phrase("Question"));
	     cellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
	     cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	     cellHeader.setPadding(6.0f);
	     questionTable.addCell(cellHeader);
	     cellHeader = new PdfPCell(new Phrase("Count"));
	     cellHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
	     cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	     cellHeader.setPadding(6.0f);
	     questionTable.addCell(cellHeader);
	     
	     
		 int i=1;
		for(QuestionUIDTO surveyQues : survey.getSurveyQuestions())
		{	
			questionTable.addCell("\t"+i);
			
			questionTable.addCell(surveyQues.getQuestionText().toString());
			questionTable.addCell((new BigDecimal(surveyQues.getSurveyQuestionCount())).toString());
			i++;
			questionTable.addCell("");
				PdfPTable answerTable = new PdfPTable(3);
				answerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				if(surveyQues.getAnsTypeId() != 3)
				{
					for(ElementDTO surveyAns : surveyQues.getAnswers())
					{
						
						answerTable.addCell(surveyAns.getText().toString());
						answerTable.addCell((new BigDecimal(surveyAns.getElementCount())).toString());
						PdfPTable otherAnsTable = new PdfPTable(1);
					
							if(("true").equals(surveyAns.getOther().toString()))
							{	
								
								List<String> othert = surveyAns.getOtherText();
								for(String otherText : othert )
								{
									if(!otherText.equals(null))
									{
										PdfPCell otherAnsCell = new PdfPCell(new Phrase(otherText));
										otherAnsCell.setBorder(Rectangle.NO_BORDER);
										otherAnsTable.addCell(otherAnsCell);
									}
								}
							}
							
						 answerTable.addCell(otherAnsTable);
					}
				}
				else
				{

					for(ElementDTO surveyAns : surveyQues.getAnswers())
					{
						if(surveyAns.getText() == null)
						{
							
						}
						else
						{
							answerTable.addCell(surveyAns.getText().toString());
							answerTable.addCell("");
							answerTable.addCell("");
						}
					}
					
				}
				questionTable.addCell(answerTable);
				questionTable.addCell("");
			
		}
	 	
	 
	 	doc.add(questionTable);
	 	
	 	
	 
	 	
	 	
	 	

	

 }
	 	
}