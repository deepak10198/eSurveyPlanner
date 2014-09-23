<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
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

		 <!--  script src="resources/js/utility.js"></script>-->
		
		<script>
			
			var counter=0;	
		
			function addDynamicRow(){
				var table = document.getElementById("answerTable");
				row_count = table.rows.length;
				var answerValue = document.getElementById("answer").value;
				
				
				if (row_count>=10){
					alert("Sorry! You can add maximum of 10 answers!");
						
				} else if(answerValue.trim()){
					
					var row = table.insertRow(row_count);	
					//row.id = "answer"+table.rows.length;]
					row.id = "answer"+counter;
					row.class="";
					
					var rowId=row.id;
										
					var cell1 = row.insertCell(0);
					//cell1.innerHTML = "<label> Answer</label>: "+answerValue+"<input type='hidden' name ='ansTextList' id='ansTextList"+counter+"' value='"+answerValue+"' >";
					cell1.innerHTML = "<label> Answer</label>: "+answerValue+"<input type='hidden' name ='ansTextList' id='ansTextList"+counter+"' >";
					document.getElementById("ansTextList"+counter).value=answerValue;
					
										
					var cell2 = row.insertCell(1);
					//cell2.innerHTML = "<button type='button' class='btn btn-default' onclick='deleteDynamic("+rowId+")'>Delete</button>";
					cell2.innerHTML = "<a href='#'><span class='glyphicon glyphicon-trash' onclick='deleteDynamic("+rowId+")'></span></a>";
					
					
					document.getElementById("answer").value="" ;
					counter +=1;
								
				} /* else {
					
					alert("First provide some answer Text!");
				} */
					
			}
			
			function deleteDynamic(rowId){
				rowId.remove();
				//ansCount -=1;
				
			}
			
			
			
		</script>

		<link href="resources/css/datepicker.css" rel="stylesheet">

		<link href="resources/css/bootstrapValidator.css" rel="stylesheet">
		
		<link href="resources/css/master.css" rel="stylesheet">
	</head>

	<body role="document">

		<%@include file="includes/header.jsp" %>

			<div class="row form-div-cus">
				<div class="col-sm-4">
					<div class="well">
						<p>
							<h2><span class="glyphicon glyphicon-user"></span>${surveyDTO.surveyName}</h2>
						</p>
						Give the answer type for each question.
					</div>
				</div>
				<div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
					<form action="addQuestions" method="POST" id="answerForm">
                                            
                        <input type="hidden" name="surveyId" value='${surveyDTO.surveyId}'>
					    <input type="hidden" name="surveyName" value='${surveyDTO.surveyName}'>
                                            
						<div class="output">
							<label for="surveyname">${surveyDTO.surveyName}</label>
							
						</div>
						<div class="form-group">
							<label for="">Type of Answer - </label>
							<div>
                            <c:forEach var="answerTypeMaster" items="${answerTypeMaster}" varStatus="types">
                            	<%-- <input type="radio"  name="ansTypeId" value="${answerTypeMaster.id}"/>${answerTypeMaster.ansTypeText} --%>
                            	
                            	<c:choose>
									<c:when test="${answerTypeMaster.id==1}">
										<input type="radio" name="ansTypeId" value="${answerTypeMaster.id}" id="ansType${types.count}" checked>
										<c:out value="${answerTypeMaster.ansTypeText}" />(Single Choice)
									</c:when>
									<c:when test="${answerTypeMaster.id==2}">
										<input type="radio" name="ansTypeId" value="${answerTypeMaster.id}" id="ansType${types.count}">
										<c:out value="${answerTypeMaster.ansTypeText}" />(Multiple Choice)
									</c:when>
								</c:choose>
                            	
                            </c:forEach>
                            </div>            
						</div>
						
						<div class="form-group">
							<label for="surveyname">Answers: </label>
							
								<input type="text" class="form-control" name="answer[]" id="answer"  placeholder="Answers"/>
								
								<button type="button" name="addAnswer" id="addAnswer" class="btn btn-default" onclick="addDynamicRow()">Add</button>
								<br/>
								<!--  button type="button" class="btn btn-default">Delete</button-->
								<table id="answerTable" class="table table-striped">
									<!-- Space to add answers dynamically. -->
								</table>						
								
							
						</div>

						<div class="form-group">
							<div>
								<button type="submit" class="btn btn-primary" name="submitButton" id="submitButton">Proceed</button>
							</div>
						</div>						
						
						
						
					</form>
				</div>
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
	</body>
</html>
<script type="text/javascript">
$(document).ready(function() {

	$('#submitButton').on('click', function(){
		var row_count = $('#answerTable tr').length;
		
		if(row_count==0){
			$('#answer').attr('required',true);
		}else{
			$('#answer').removeAttr('required');
		}
		
		
	});
	
	$('#answerForm').submit(function(e){	
		var row_count = $('#answerTable tr').length;
		
		if(row_count==0 && $('#answer').valueOf()){
			e.preventDefault();	
			alert('Please add the answer!');
		}
	});
	
});
</script>

