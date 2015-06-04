<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../resources/ico/favicon.ico">

<title><c:out value="${survey.surveyName}" /></title>

<!-- Bootstrap core CSS -->
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">




<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<link href="../resources/css/datepicker.css" rel="stylesheet">

<link href="../resources/css/bootstrapValidator.css" rel="stylesheet">

<link href="../resources/css/master.css" rel="stylesheet">
<script>
        function newWindow()
        {
        	window.location="../home";
        }
        
        
        </script>
</head>

<body role="document" 
	style="background: url('../resources/images/surveyBackground.jpg') no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">

	<%@include file="includes/welcomeHeader.jsp"%>
	<div class="row form-div-cus">
		<div class="col-sm-4">
			<div class="well">
				<table>
					<tr>
						<td width="5%"><span class="glyphicon glyphicon-user"></span></td>
						<td>&nbsp; &nbsp;<b><c:out value="${survey.surveyName}" /></b></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><c:out value="${survey.surveyDesc}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td width="60%"><b>Start Date :</b></td>
						<td><c:out value="${survey.surveystart}" /></td>
					</tr>
					<tr>
						<td width="60%"><b>End Date :</b></td>
						<td><c:out value="${survey.surveyend}" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-8"
			style="border: 1px solid #d9d9d9; padding: 1em; border-radius: 4px; background-color: rgba(0, 0, 0, 0.3);">
			<form action="submitSurveyResponse" method="POST"
				name="surveyResponseForm" id="surveyResponseForm"
				onSubmit="return checkmandatory(this)">
				
				
				<c:choose>
				<c:when test="${empty user }">
			<b>Your Email address is :- </b><br> <input type="email"
					name="surveyEmail" class="form-control" id="em"
					required /> 
					</c:when>
					<c:otherwise>
					
					<h3>Hello <c:out value='${user.firstName }'/></h3>
					<br>
					
					<b>Your Email address is :- </b><br> <input type="email"
					name="surveyEmail" value="${user.email }"class="form-control" id="em" readonly="readonly"required /> 
					</c:otherwise>
					
					
				</c:choose>
					
				
				
				
				
				
				 <input type="hidden" name="surveyId"
					value='${survey.surveyId}'> <input type="hidden"
					name="surveyName" value='${survey.surveyName}'>
				<div class="form-group">
					<h2>
						<label for="surveyname"> <c:out
								value="${survey.surveyName}" /></label>
					</h2>
				</div>
				<font color="RED" size=5px><b>*</b></font> Indicates Mandatory Questions  <br> <br>
				<input type="hidden" id="totalquestions"
					value="${survey.surveyQuestions.size() }" />
				<c:forEach items="${survey.surveyQuestions}" var="question"
					varStatus="status">

					<div class="form-group">
						<input type="hidden"
							name="surveyQuestions[${status.index}].questionId"
							value='${question.questionId}'> <input type="hidden"
							name="surveyQuestions[${status.index}].ansTypeId"
							value='${question.ansTypeId}'> <input type="hidden"
							name="surveyQuestions[${status.index}].quesAnswerId"
							value='${question.quesAnswerId}'>
						<table class="table table-hover ">
							<tr>
								<td width="80%"><font color="Black"><b>${status.count
											}.</b> &nbsp; <c:choose>
											<c:when test="${question.mandatory == 'true' }">
												<label for="question${status.index}"><c:out
														value="${question.questionText}" />&nbsp;<font
													color="RED" size=4px><b>*</b></font></label>
												<td align="right"></td>
												<input type="hidden" value="${question.mandatory }"
													id="mandatory${status.index }" />
												<input type="hidden" value="${question.ansTypeId }"
													id="ansType${status.index }" />
											</c:when>
											<c:otherwise>
												<label for="question${status.index}"><c:out
														value="${question.questionText}" /></label>
												<input type="hidden" value="${question.ansTypeId }"
													id="ansType${status.index }" />
												<input type="hidden" value="${question.mandatory }"
													id="mandatory${status.index }" />
											</c:otherwise>
										</c:choose> </font></td>
								<td width="20%" hidden="true" id="mandatetd${status.index }">
									<c:choose>
										<c:when test="${question.mandatory == 'true' }">
											<span class="label label-danger label-as-badge"><b>Mandatory
													Question</b></span>

										</c:when>
									</c:choose> </font>
								</td>
							</tr>
						</table>
						<div class="err">
							<table class="table table-striped ">
								<c:choose>
									<c:when test="${question.ansTypeId == 3 }">
										<tr>
											<td><input type="text" class="form-control"
												class="answerField"
												name="surveyQuestions[${status.index}].ansTextList"
												id="surveyQuestions[${status.index}].ansTextList"
												onkeypress="removet(${status.index})" /></td>
										</tr>
									</c:when>
									<c:otherwise>

										<c:forEach var="answer" items="${question.answers}"
											varStatus="ansStatus">
											<c:choose>
												<c:when test="${question.ansTypeId == 1 }">
													<c:choose>
														<c:when test="${answer.other == 'true' }">
															<tr>
																<td width=5%><input type="radio"
																	class="ansTextList${status.index}"
																	name="surveyQuestions[${status.index}].ansTextList"
																	id="surveyQuestions[${status.index}].ansTextList"
																	value="${answer.id}"
																	onChange='return check(${status.index})' /></td>
																<td><c:out value="${answer.text}" /></td>
															</tr>
															<tr id="otherTr${status.index}" hidden=true>
																<td colspan="2"><input type="text"
																	class="form-control"
																	name="surveyQuestions[${status.index}].otherText"
																	id="surveyQuestions[${status.index}].otherText"
																	placeholder="Please specify" /></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td width=5%><input type="radio"
																	class="ansTextList${status.index}"
																	name="surveyQuestions[${status.index}].ansTextList"
																	id="surveyQuestions[${status.index}].ansTextList"
																	value="${answer.id}"
																	onClick='return uncheck(${status.index})' /></td>
																<td><c:out value="${answer.text}" /></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:when test="${question.ansTypeId == 2 }">
													<c:choose>
														<c:when test="${answer.other == 'true' }">
															<tr>
																<td width=5%><input type="checkbox"
																	class="ansTextList${status.index}"
																	name="surveyQuestions[${status.index}].ansTextList"
																	id="surveyQuestions[${status.index}].ansTextList"
																	value="${answer.id}"
																	onChange='return check(${status.index})' /></td>
																<td><c:out value="${answer.text}" /></td>
															</tr>
															<tr id="otherTr${status.index}" hidden=true>
																<td colspan="2"><input type="text"
																	class="form-control"
																	name="surveyQuestions[${status.index}].otherText"
																	id="surveyQuestions[${status.index}].otherText"
																	placeholder="Please specify" /></td>
															</tr>
														</c:when>
														<c:otherwise>
															<tr>
																<td width=5%><input type="checkbox"
																	class="ansTextList${status.index}"
																	placeholder="Please specify"
																	name="surveyQuestions[${status.index}].ansTextList"
																	id="surveyQuestions[${status.index}].ansTextList"
																	value="${answer.id}"
																	onClick='return uncheck(${status.index})' /></td>
																<td><c:out value="${answer.text}" /></td>
															</tr>
														</c:otherwise>
													</c:choose>
												</c:when>

											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
				</c:forEach>
				

				<div class="form-group">
					<div>
						<input type="submit" class="btn btn-primary" id="submitButton"
							value="Submit Survey"></input>
					</div>
				</div>
			</form>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>

	<!-- ================================================ Bootstrap core JavaScript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

	<script src="../resources/js/jqBootstrapValidation.js"></script>
	<script src="../resources/js/jqBootstrapValidation.js"></script>
	<script src="../resources/js/bootstrap-datepicker.js"></script>
	<script src="../resources/js/bootstrapValidator.js"></script>
	<script src="../resources/js/utility.js"></script>
	<script src="../resources/js/bootbox.min.js"></script>


</body>
</html>

<script type="text/javascript">
function check(value)
{
	
        $("#otherTr"+value).toggle();
   
   	}
function uncheck(value)
{
	  $('#mandatetd'+value).hide();
        $("#otherTr"+value).hide();
        $("#submitButton").removeAttr("disabled");
        
        
   return true;
   	}
function removet(value)
{
	
	  $('#mandatetd'+value).hide();
        $("#submitButton").removeAttr("disabled");
        
        
   return true;
   	}

  function checkmandatory(form){

	 var count = parseInt(document.getElementById("totalquestions").value);
	 
	 
	 for(var i=0; i<count;i++)
	{
		var mdcount = $('#mandatory'+i).val();
		var anstype =parseInt($('#ansType'+i).val());
		 
			if(mdcount == 'true')
				{
				if( anstype != 3)
				{
					var chk = document.getElementsByName('surveyQuestions['+i+'].ansTextList');
					 var len = chk.length;
					 var flag = 0;
					 for(var j=0 ;j<len;j++)
						 {
						if(!chk[j].checked)
						{
							flag++;		
						}
						else
							{
							flag = 0;
							break;
							}
						
						 }
					 if(flag>0)
						 {
						 	document.getElementById('surveyQuestions['+i+'].ansTextList').focus();
						 	$('#mandatetd'+i).show();
						 	
						 	return false;
						 }
					
					}
				else{
					
					var chkq = document.getElementById("surveyQuestions["+i+"].ansTextList").value;
					var chk = chkq.trim();
				
					if(chk == "" || chk.length == 0 || chk == null)
						{
						
						document.getElementById('surveyQuestions['+i+'].ansTextList').focus();
					 	$('#mandatetd'+i).show();
					 	return false;
						
						}
					
					
				}
				
				 
				}
			
			
		 
		 }
	 
	 
 }
  

 $(document).ready(function(){
	
		$('#surveyResponseForm').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				surveyEmail: {
					validators: {
						emailAddress: {
							message: '<font color="white" size="4px"><b>Please Reload and fill a Valid Email Address..</b></font>'
						},
						
					}
				},
			}
			});
 });
	
	

</script>