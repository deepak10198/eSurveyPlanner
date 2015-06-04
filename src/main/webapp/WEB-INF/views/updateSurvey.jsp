<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="resources/ico/favicon.ico">

		<title>eSurveyPlanner</title>

		<!-- Bootstrap core CSS -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<!-- Bootstrap theme -->
		<link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">

		<!-- Just for debugging purposes. Don't actually copy this line! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<link href="resources/css/datepicker.css" rel="stylesheet">

		<link href="resources/css/bootstrapValidator.css" rel="stylesheet">
		
		<link href="resources/css/master.css" rel="stylesheet">
		
<!-- 		 <link href="resources/css/bootstrap-switch.css" rel="stylesheet">
		   --><link href="resources/css/bootstrap-toggle.min.css" rel="stylesheet">
	<script>



</script>

        <style>
        
       
.borderless td, .borderless th {
    border: none;
}

        
        
        </style>
        
        <script>
         function sendinfo(i)
         {
        	 var surveyId =parseInt( $('#surveyId'+i).val());
        	 var surveyname = $('#surveyname'+i).val();
        	var type = $('#type'+i).val();
        	var description = $('#description'+i).val();
        	 var questionText = $('#questionText'+i).val();
        	 var mandatory ="";
        	 if($('#mandatoryStatus'+i).prop('checked')==true)
        		{
        		 mandatory = "true";
        		 }
        	 else{
        		  mandatory= "false";
        	 }
        	 var qid = parseInt($('#questionid'+i).val());
        	
        	 
        	 $.ajax({
        		 
        		type:"POST",
        		url: "e/question"+qid,
        		data:"questionId="+qid+"&questionText="+questionText+"&mandatory="+mandatory+"&surveyId="+surveyId+"&status="+"updated",
        		success: function(response){
        			//alert("updated");
        		},
        	 	error: function(e){
        	 		
        	 	}
        		 
        	 });
        	 
        	 
         }
        
        </script>
		
		
	</head>
<body role="document">
<%@include file="includes/header.jsp" %>
<div class="row form-div-cus">
	<div class="col-lg-12" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
		<div class="well"><font color="#33CC33"><b><center>Survey metadata has been updated</center></b></font></div>
		
			
<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->	
<div class="col-lg-12 well" >
	<div class="form-group ">
		<c:choose>
			<c:when test="${empty survey.surveyQuestions }">
			<table class="table">
			<tr><th><center> There is no Question in this Survey </center></th></tr></table>
				<c:choose>
			<c:when test="${ survey.published == 'Pending' }">
					<form action="surveyMaster" method="Post">
						<input type="hidden" name="surveyId" value="${survey.surveyId }" />
						<input type="hidden" name="surveyname" value="${survey.surveyName }" />
						<input type="hidden" name="type" value="${survey.type }" />
						<input type="hidden" name="description" value="${survey.surveyDesc }" />
											
					<center>	<button type="submit" class="btn btn-primary">Add Question Answers</button></center>
					</form>
					</c:when>
					<c:otherwise>
						<center><a href="d/survey${survey.surveyId}"  ><button type="submit" class="btn btn-danger">Delete</button></a></td>
   			 									</center>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div class="col-lg-12 well" id="quesAnst">
					<div class="form-group ">
					<input type="hidden" name="surveyId" value="${survey.surveyId }" />
					<input type="hidden" name="surveyname" value="${survey.surveyName }" />
					<input type="hidden" name="type" value="${survey.type }" />
					<input type="hidden" name="description" value="${survey.surveyDesc }" />
						<c:forEach items="${survey.surveyQuestions}" var = "question" varStatus="status">
							<table class="table table-hover" id="quesTable">
								<tr id="questext${status.index}"><th width="1%" style="vertical-align: middle">${status.count }.</th><td width="80%" ><b><c:out  value="${question.questionText}"  /></b></td>
								
								<td width="14%">
								<c:choose>
									<c:when test="${ question.mandatory == 'true' }"><span class="label label-danger label-as-badge">Mandatory</span></c:when>
									<c:otherwise><span class="label label-success label-as-badge"><b>Non-Mandatory</b></span></c:otherwise>
								</c:choose>
								</td>
								
								
								
								<td width="5%"><form action="./d/question${question.questionId}" method="Post">
											<input type="hidden" name="questionid" id="questionid${status.index }" value="${question.questionId}" />
											
											<input type="hidden" name="surveyId" id="surveyId" value="${survey.surveyId }" />
											<input type="hidden" name="surveyname" id="surveyname" value="${survey.surveyName }" />
											<input type="hidden" name="type" id="type" value="${survey.type }" />
											<input type="hidden" name="description" id="description" value="${survey.surveyDesc }" />
										<c:choose>
											<c:when test="${ survey.published == 'Pending' }">
									<center><button type="submit" class="glyphicon glyphicon-trash"></button></center></c:when></c:choose>
								</form>
								
								</td>
								<td id="quesEdit${status.index}">
							<button type="button" class="glyphicon glyphicon-pencil" onClick="edit(${status.index})"></button></td></tr>
   			 						
   			 						<!-- ----------------------------------- -->	
   			 						
   			 						<tr id="questextedit${status.index}" hidden>
   			 						<th width="1%" style="vertical-align: middle">${status.count }.</th>
   			 						<td width="80%"  >

													<form action="" onSubmit="sendinfo(${status.index})"
														method="Post">
														<table>
															<tr>
																<td width="72%"><input type="text"
																	class="form-control" name="questionText"
																	id="questionText${status.index}"
																	value="${question.questionText }" /></td>

																<td width="8%"><c:choose>
																		<c:when test="${ question.mandatory == 'true' }">
																			<input type='checkbox'
																				class='mandatoryStatusClass${status.index}'
																				name='mandatory' id='mandatoryStatus${status.index}'
																				checked data-toggle="toggle" data-on="Mandatory"
																				data-width="150" data-height="15"
																				data-off="Non-Mandatory" data-onstyle="danger"
																				data-offstyle="success" />
																		</c:when>
																		<c:otherwise>

																			<input type='checkbox'
																				class='mandatoryStatusClass${status.index}'
																				name='mandatory' id='mandatoryStatus${status.index}'
																				data-toggle="toggle" data-on="Mandatory"
																				data-width="150" data-height="15"
																				data-off="Non-Mandatory" data-onstyle="danger"
																				data-offstyle="success" />

																		</c:otherwise>


																	</c:choose></td>

																<td width="2%"><input type="hidden" name="surveyId"
																	id="surveyId${status.index}"
																	value="${survey.surveyId }" /> <input type="hidden"
																	name="surveyname" id="surveyname${status.index}"
																	value="${survey.surveyName }" /> <input type="hidden"
																	name="type" id="type${status.index}"
																	value="${survey.type }" /> <input type="hidden"
																	name="description" id="description${status.index}"
																	value="${survey.surveyDesc }" /></td>

																<td width=5%><button type="submit"
																		class="glyphicon glyphicon-ok"></button></td>
																<td width=5%><button type="button"
																		class="glyphicon glyphicon-remove"
																		onclick="cancelEdit(${status.index})"></button></td>

															</tr>
														</table>
													</form>

												</td>	
										</tr>
								</table>
							<table  class="table table-striped">	<c:choose>
									
									<c:when test="${question.ansTypeId == 1 || question.ansTypeId == 2 }" >			
										
										<c:forEach var="answer" items="${question.answers}" varStatus="ansStatus">
											<tr><th width="2%" >-</th><td><c:out   value="${answer.text } " /></td></tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr><td><input type="text" value="Answer Is of Free Text Type"  class="form-control" disabled="disabled"/></td></tr>
										</c:otherwise>
								
									</c:choose>
									</table>
						</c:forEach>
				
			</div>
		</div>	 
		<c:choose>
			<c:when test="${ survey.published == 'Pending' }">
				<form action="surveyMaster" method="Post">
					<input type="hidden" name="surveyId" value="${survey.surveyId }" />
					<input type="hidden" name="surveyname" value="${survey.surveyName }" />
					<input type="hidden" name="type" value="${survey.type }" />
					<input type="hidden" name="description" value="${survey.surveyDesc }" />
										
					<button type="submit" class="btn btn-primary">Add </button>
						<a href=./viewSurvey><button type="button" class="btn btn-primary">Done</button></a>
				</form>
			
				</c:when>
				<c:otherwise>
				<a href=./viewSurvey><center>	<button type="button" class="btn btn-primary">Done</button></center></a>
				</c:otherwise>
				</c:choose>
		</c:otherwise>
							
							
		
		</c:choose>
		
	</div>
	
	</div>
</div>		
				
	
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
	
</div>		
		
						
<%@include file="includes/footer.jsp" %>

		   <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap-datepicker.js"></script>
        <script src="resources/js/bootstrapValidator.js"></script>
        <script src="resources/js/utility.js"></script>
          <script src="resources/js/bootstrap-switch.js"></script> 
           <script src="resources/js/bootstrap-toggle.min.js"></script>
</body>
</html>

<script type="text/javascript">

function edit(value)
{
	$("#quesEdit"+value).hide();
	$("#questext"+value).hide();
	$("#questextedit"+value).show();
//	$('.mandatoryStatusClass'+value).bootstrapSwitch('state',true,false);
	
}
function cancelEdit(value)
{
	$("#quesEdit"+value).show();
	$("#questext"+value).show();
	$("#questextedit"+value).hide();
	
	
}



$(document).ready(function() {
	
	$("#questiontab").on("click", function(){
		
		$("#questiont").show();
		$("#answert").hide();
		
		
	});
$("#answertab").on("click", function(){
		
		$("#questiont").hide();
		$("#answert").show();
		
		
	});
	
	
$('.mandatoryStatusClass').bootstrapSwitch('status');	


	
	
	
	
});
</script>
