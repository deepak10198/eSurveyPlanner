<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../resources/ico/favicon.ico">

<title>eSurveyPlanner</title>

<style>
a.reUline:hover {
	text-decoration: none;
}
</style>

<!-- Bootstrap core CSS -->
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


<link
	href="https://cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">


<link href="../resources/css/datepicker.css" rel="stylesheet">

<link href="../resources/css/bootstrapValidator.css" rel="stylesheet">

<link href="../resources/css/master.css" rel="stylesheet">
<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script src="http://getbootstrap.com/dist/js/bootstrap.js"></script>
</head>

<body role="document">

	<%@include file="includes/headerAdmin.jsp"%>

	<div class="row form-div-cus table-responsive">
		<div class="col-sm-5 table-responsive">
			<div class="well table-responsive ">
				<p>
				<h2>
					<span class="glyphicon glyphicon-user"></span> Hi, Admin!
				</h2>
				<br>
				<table class="table table-striped">

					<tr>
						<td><b>Survey Name:</b></td>
						<td>${survey.surveyname}</td>
					</tr>
					<tr>
						<td><b>Survey Description:</b></td>
						<td>${survey.description }</td>
					</tr>
					<tr>
						<td><b>Survey start Date</b></td>
						<td>${survey.surveystart }</td>
					</tr>
					<tr>
						<td><b>Survey End Date</b></td>
						<td>${survey.surveyend }</td>
					</tr>
					<tr>
						<td><b>Users Submitted</b></td>
						<td>${userSurvey.surveyCount }</td>
					</tr>
					
					<tr><br><br>
						<td colspan="2"><a href="${link}" target="_blank"><b>${link}</b></a></td>
					</tr>
				</table>
				</p>

			</div>


			<c:choose>
				<c:when test="${survey.published == 'Pending' }">
				
					<a href="${path }/e/survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Edit</button></a>
							
					<button type="Button" class="btn btn-primary" disabled="disabled">Generate
						Report</button>
					<a href="${path }/send_survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Send Link</button></a>
								<a href="${path }/publish${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Publish</button></a>
							
				<a href="${path }/d/survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Delete</button></a>

				</c:when>
				<c:when test="${survey.published == 'Closed' }">
					<a href="${path }/download/survey${survey.surveyId }"
						target="_blank"><button type="Button" class="btn btn-primary">Generate
							Report</button></a>

					
				</c:when>

				<c:when test="${survey.published == 'Active' }">
						<a href="${path }/e/survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Edit</button></a>
			
					<a href="${path }/download/survey${survey.surveyId }"
						target="_blank"><button type="Button" class="btn btn-primary">Generate
							Report</button></a>
					<a href="${path }/send_survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Send Link</button></a>
								<a href="${path }/publish${survey.surveyId }"><button
							type="Button" class="btn btn-primary">Publish</button></a>
							
				<a href="${path }/d/survey${survey.surveyId }"><button
							type="Button" class="btn btn-primary" disabled="disabled">Delete</button></a>

				</c:when>


			</c:choose>


		</div>
		<c:choose>
			<c:when test="${survey.published == 'Closed' }">
				<div class=" col-sm-7 well"
					style="background: rgba(255, 0, 0, 0.9); border: 1px solid black;">
					<center>
						<b> <c:out value=" ${survey.published }" />
						</b>
					</center>
				</div>
			</c:when>
			<c:when test="${survey.published == 'Active' }">
				<div class=" col-sm-7 well"
					style="background: rgba(0, 204, 0, 1.0); border: 1px solid black;">
					<center>
						<b> <c:out value=" ${survey.published } " />
						</b>
					</center>
				</div>
			</c:when>
			<c:when test="${survey.published == 'Pending' }">
				<div class=" col-sm-7 well"
					style="background: rgba(255, 255, 0, 0.8); border: 1px solid black;">
					<center>
						<b> <c:out value=" ${survey.published } " />
						</b>
					</center>
				</div>
			</c:when>
		</c:choose>





		<div class="col-sm-7 table-responsive"
			style="border: 1px solid #d9d9d9; padding: 1em; border-radius: 4px;">

			<c:forEach items="${userSurvey.surveyQuestions }" var="question"
				varStatus="status">

				<div>

					<input type="hidden"
						name="surveyQuestions[${status.index}].questionId"
						value='${question.questionId}'> <input type="hidden"
						name="surveyQuestions[${status.index}].ansTypeId"
						value='${question.ansTypeId}'> <input type="hidden"
						name="surveyQuestions[${status.index}].quesAnswerId"
						value='${question.quesAnswerId}'>
					<table id="paginationtable" class="table table-hover ">
						<tr>
							<td><font color="#CC0000"><b>${status.count }.</b>
									&nbsp; <label for="question${status.index}"><c:out
											value="${question.questionText}" /></label></font></td>
							<td align="right"><span
								class="label label-danger label-as-badge"><b>${question.surveyQuestionCount
										}</b> </span></td>
						</tr>
					</table>
					<div>
						<table class="table table-striped ">
							<c:forEach var="answer" items="${question.answers}"
								varStatus="ansStatus">

								<c:choose>

									<c:when test="${question.ansTypeId == 1 }">

										<tr>
											<td><label
												name="surveyQuestions[${status.index}].ansTextList"
												id="surveyQuestions[${status.index}].ansTextList"
												value="${answer.id}"> <c:out value="${answer.text}" /></label></br></td>


											<td width="50%">

												<div class="progress">

													<div class="progress-bar progress-bar-striped  " role="progressbar"
														aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
														style="width:<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / question.surveyQuestionCount) }" />">
														<fmt:formatNumber type="percent" minFractionDigits="1"
															value="${(answer.elementCount / question.surveyQuestionCount) }" />
													</div>

												</div> <%-- <div class="progress">
	                              
  										<div class="progress-bar  progress-bar-success " role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / question.surveyQuestionCount) }" />">
  									 	<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / userSurvey.surveyCount) }" />
  									 	</div>
  									 									
	                              	</div> --%>

											</td>
										</tr>
									</c:when>
									<c:when test="${question.ansTypeId == 2 }">
										<tr>
											<td><label
												name="surveyQuestions[${status.index}].ansTextList"
												id="surveyQuestions[${status.index}].ansTextList"
												value="${answer.id}"> <c:out value="${answer.text}" /></label></br></td>

											<td width="50%">

												<div class="progress">

													<div class="progress-bar " role="progressbar"
														aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
														style="width:<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / question.surveyQuestionCount) }" />">
														<fmt:formatNumber type="percent" minFractionDigits="1"
															value="${(answer.elementCount / question.surveyQuestionCount) }" />
													</div>
												</div> <%-- <div class="progress">
	                              
  										<div class="progress-bar progress-bar-success  " role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / question.surveyQuestionCount) }" />">
  									 	<fmt:formatNumber type="percent" minFractionDigits="1" value="${(answer.elementCount / userSurvey.surveyCount) }" />
  									 	</div>
  									 									
	                              	</div> --%>


											</td>
										</tr>
									</c:when>
								</c:choose>



							</c:forEach>



						</table>
					</div>


				</div>
			</c:forEach>






		</div>

	</div>

	<%@include file="includes/footer.jsp"%>

	<!-- Bootstrap core JavaScript
		================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-datepicker.js"></script>
	<script src="resources/js/bootstrapValidator.js"></script>
	<script src="resources/js/utility.js"></script>

	<script src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>


</body>
</html>

<script type="text/javascript">
$(document).ready(function() {
    $('#paginationtable').dataTable({   
    	
    	 responsive: true,
         autoWidth: true,
        jQueryUI: true,
         processing: true,
         "aLengthMenu": [[5,10, 20, -1], [5, 10, 20, "All"]],
         
         
    		    sWrapper: "dataTables_wrapper form-inline"
    		 
    		 
    });
} );

	$(document).ready(function() {
		
		
		var startDate;// = new Date();
		var endDate;// = new Date();
		//endDate.setDate(startDate.getDate() + 1);
		
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() - 1);
		
		
		$('#startDateCal').datepicker()
		.on('changeDate', function(ev){
			if (ev.date.valueOf() < todayDate.valueOf()){
				var validator = $('#createSurveyForm').data('bootstrapValidator');
				//$('#alert').show().find('strong').text('The start date can not be less than today date.');
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'INVALID','notEmpty');
				$('#createSurveyForm').data('bootstrapValidator').updateMessage('surveystart', 'notEmpty', 'The start date can not be less than today.');
			}else if (endDate &&  ev.date.valueOf() > endDate.valueOf()){
				//$('#alert').show().find('strong').text('The start date can not be greater than the end date');
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'INVALID','notEmpty');
				$('#createSurveyForm').data('bootstrapValidator').updateMessage('surveystart', 'notEmpty', 'The start date can not be greater than the end date.');
			} else {
				//$('#alert').hide();
				startDate = new Date(ev.date);
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'NOT_VALIDATED').bootstrapValidator('validateField', 'surveystart');
			}
			$('#startDateCal').datepicker('hide');
		});
		
		$('#endDateCal').datepicker()
		.on('changeDate', function(ev){
			if (ev.date.valueOf() < todayDate.valueOf()){
				//$('#alert').show().find('strong').text('The end date can not be less than today date.');
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'INVALID','notEmpty');
				$('#createSurveyForm').data('bootstrapValidator').updateMessage('surveyend', 'notEmpty', 'The end date can not be less than today.');
			}else if (startDate && ev.date.valueOf() < startDate.valueOf()){
				//$('#alert').show().find('strong').text('The end date can not be less than the start date');
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'INVALID','notEmpty');
				$('#createSurveyForm').data('bootstrapValidator').updateMessage('surveyend', 'notEmpty', 'The end date can not be less than the start date.');
			} else {
				//$('#alert').hide();
				endDate = new Date(ev.date);
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#createSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'NOT_VALIDATED').bootstrapValidator('validateField', 'surveyend');
			}
			$('#endDateCal').datepicker('hide');
		}); 
		
		 $('#startDateCal')
	        .on('dp.change dp.show', function(e) {
	            // Validate the date when user change it
	            $('#createSurveyForm').bootstrapValidator('revalidateField', 'surveystart');
	        });
		

		$('#createSurveyForm').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				surveyname: {
					validators: {
						notEmpty: {
							message: 'The survey name is required and cannot be empty'
						},
						stringLength: {
	                        max: 100,
	                        message: 'The Survey name must be less than 100 characters'
	                    }
					}
				},
				description: {
					validators: {
						stringLength: {
	                        max: 500,
	                        message: 'The Survey description must be less than 500 characters'
	                    }
					}
				},
				surveystart: {
					validators: {
						notEmpty: {
							message: 'The start date is required and cannot be empty'
						},
						date: {
							format: 'DD-MM-YYYY',
							message: 'The value is not a valid date'
						}
					}
				},
				surveyend: {
					validators: {
						notEmpty: {
							message: 'The end date is required and cannot be empty'
						},
						date: {
							format: 'DD-MM-YYYY',
							message: 'The value is not a valid date'
						}
					}
				}
			}
		});
	});
</script>
