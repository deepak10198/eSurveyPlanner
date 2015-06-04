package com.esp.userExcel;


import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.esp.handler.HomeHandler;   

import com.esp.service.GenericService;

import com.esp.dto.UserDetailsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class userExcel{
		
	@Autowired
	@Qualifier("SurveyQuestionMappingService")
	private GenericService surveyQuestionMappingService;
	@Autowired
	@Qualifier("SurveyResponseService")
	private GenericService surveyResponseService;
	@Autowired
	@Qualifier("UserMasterService")
	private  GenericService userMasterService;
	@Autowired
	@Qualifier("SurveyMasterService")
	private GenericService surveyMasterService;
	@Autowired
	@Qualifier("SurveyTypeMasterService")
	private GenericService surveyTypeMasterService;
	@Autowired
	@Qualifier("AnswerMasterService")
	private GenericService answerService;
	@Autowired
	@Qualifier("AnswerTypeMasterService")
	private GenericService answerTypeMasterService;
@Autowired
private  HomeHandler handler;
 static Integer userId = 101;

	
	
	public static List<UserDetailsDTO> mapUserDataToList(String filePath)
	{
		
		try
		{
			List<UserDetailsDTO> userDetail = new ArrayList<UserDetailsDTO>();
			
			FileInputStream fis = new FileInputStream(filePath);
			
			
			Workbook workbook = null;
			workbook = new HSSFWorkbook(fis);
			
			int numberOfSheets = workbook.getNumberOfSheets();
			for(int i=0; i < numberOfSheets; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
			
				
				Iterator<Row> rowIterator = sheet.iterator();
				int rowCount = sheet.getLastRowNum();
				System.out.println("Row number ------------- "+rowCount);
				
				
				for(int c=0; c <= rowCount; c++)
				{
					
					ArrayList<String> columnData = new ArrayList<String>();
					UserDetailsDTO userdet = new UserDetailsDTO();
					Row row = rowIterator.next();
			
						int columnNum = row.getLastCellNum();
						System.out.println("Column number ------------- "+columnNum);
							
						for(int col = 0; col < columnNum; col++)
						{
							Cell cell = row.getCell(col, Row.CREATE_NULL_AS_BLANK);
							
							
							if(cell.getCellType() == Cell.CELL_TYPE_STRING )
							{
								
								if(cell.getStringCellValue()==""|| cell.getStringCellValue()==null)
								{System.out.println("blank");}
								else
								{
									columnData.add(cell.getRichStringCellValue().toString());
									System.out.println("String"+cell.getStringCellValue());
								}
							
							}
						
						
							else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
							{columnData.add(String.valueOf(""));	
							System.out.println("blank"+cell.getStringCellValue());}
						
							else
							{
								String date = cell.getDateCellValue().toString();
									SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
									Date pdate = formatter.parse(date);
									SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
									
								columnData.add(format.format(pdate).toString());
								
							System.out.println("other"+format.format(pdate).toString());
							}
							
							
							
						}
						
					
					
						
						System.out.println("--"+columnData.size());
						for(int m=0 ; m<columnData.size();m++)
						{
							System.out.println(m+"---"+columnData.get(m));
						}
						System.out.println("Storing data to list-----------");
						for(String col : columnData)
						{
							System.out.println("check ------------------------------------------------------- "+col);
						}
						
						
							userdet.setFirstName(columnData.get(0));
							userdet.setMiddleName(columnData.get(1));
							userdet.setLastName(columnData.get(2));
							userdet.setGender(columnData.get(3));
							
							userdet.setdob(columnData.get(4));
							
							userdet.setEmail(columnData.get(5));
							userdet.setLoginId(columnData.get(6));
							userdet.setMartialStatus(columnData.get(7));
							userdet.setPassword(columnData.get(8));
							
							userDetail.add(userdet);
							 
							
					        
					        
						columnData.clear();
						
						
						System.out.println(" list-----------"+userDetail.size());
						
					}
				
			
				
			}
				workbook.close();
				fis.close();
				
				for(UserDetailsDTO userdet : userDetail)
				{
					System.out.println("User Details:"+ userdet.getFirstName()+" "+userdet.getMiddleName()+" "+userdet.getLastName()+"\t "+userdet.getEmail());
					
				}
				
			return userDetail;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}