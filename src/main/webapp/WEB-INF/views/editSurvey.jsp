<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="../resources/ico/favicon.ico">

        <title><c:out value="${survey.surveyname}"/></title>

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
    </head>
	<body role="document">

		<%@include file="includes/headerAdmin.jsp" %>

			<div class="row form-div-cus">
				<div class="col-sm-4">
					<div class="well">
						<p>
							<h2><span class="glyphicon glyphicon-user"></span> Hi, Admin!</h2><br>
						</p>
						Your Editing Panel for Survey :<br><br> <b><c:out value="${survey.surveyname}"/></br></b>
					</div>
				</div>
				<div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
					<form action="../surveyMaster${survey.surveyId}" method="POST" name="editSurveyForm" id="editSurveyForm">
					
					<input type="hidden" name="surveyId" value='${survey.surveyId}'>
					<%-- <input type="hidden" name="type" value='${survey.type }' > --%>
						<div class="form-group">
							<label for="surveyname">Name of the Survey</label>
							<div>
								<input type="text" class="form-control" name="surveyname" id="surveyname" placeholder="Enter Survey Name" value="${survey.surveyname }">
							</div>
						</div>
						<div class="form-group">
							<label for="">Description</label>
							<textarea class="form-control" rows="3" name="description" id ='description'>${survey.description }</textarea>
						</div>
						<div class="form-group">
							<label for="surveystart">Starts From &nbsp;</label>
							<a href="#" id="startDateCal" data-date="${survey.surveystart }"  data-date-format="dd-mm-yyyy" ><span class="glyphicon glyphicon-calendar" /></a> ( DD-MM-YYYY)
							<div>
								<input type="text" class="form-control" id="surveystart" name="surveystart" data-date-format="dd-mm-yyyy" readonly="readonly" value="${survey.surveystart }" >
							</div>
						</div>
						<div class="form-group">
							<label for="surveyend">Ends On &nbsp;</label>
							<a href="#" id="endDateCal" data-date="${survey.surveyend }"  data-date-format="dd-mm-yyyy"><span class="glyphicon glyphicon-calendar" /></a> ( DD-MM-YYYY)
							<input type="text" class="form-control" id="surveyend" name="surveyend" data-date-format="dd/mm/yyy" value="${survey.surveyend }" required="required" readonly="readonly" >
						</div>
						
						<div id="alert" class="alert alert-error" style="display: none;">
							<strong>The end date can not be less then the start date</strong>
						</div>
						<div class="form-group">
						
							
						
						</div>
			
						<div class="form-group">
							<div>
							<input type="hidden" name="updateStatus" value="updating" />
								<button type="submit" class="btn btn-primary">Update</button> &nbsp; &nbsp;
								<a href="../viewSurvey"><button type="button" class="btn btn-primary">Cancel</button></a>
							</div>
						</div>

						<!--<button type="submit" class="btn btn-lg btn-primary">Proceed</button>
						<button type="button" class="btn btn-info" id="validateBtn">Manual validate</button> -->
					</form>
				</div>
			</div>

		<%@include file="includes/footer.jsp" %>

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="../resources/js/bootstrap.min.js"></script>
		<script src="../resources/js/bootstrap-datepicker.js"></script>
		<script src="../resources/js/bootstrapValidator.js"></script>
		<script src="../resources/js/utility.js"></script>
	</body>
</html>

<script type="text/javascript">


	$(document).ready(function() {
		
		
		var startDate;// = new Date();
		var endDate;// = new Date();
		//endDate.setDate(startDate.getDate() + 1);
		
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() - 1);
		
		
		$('#startDateCal').datepicker()
		.on('changeDate', function(ev){
			if (ev.date.valueOf() < todayDate.valueOf()){
				var validator = $('#editSurveyForm').data('bootstrapValidator');
				//$('#alert').show().find('strong').text('The start date can not be less than today date.');
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'INVALID','notEmpty');
				$('#editSurveyForm').data('bootstrapValidator').updateMessage('surveystart', 'notEmpty', 'The start date can not be less than today.');
			}else if (endDate &&  ev.date.valueOf() > endDate.valueOf()){
				//$('#alert').show().find('strong').text('The start date can not be greater than the end date');
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'INVALID','notEmpty');
				$('#editSurveyForm').data('bootstrapValidator').updateMessage('surveystart', 'notEmpty', 'The start date can not be greater than the end date.');
			} else {
				//$('#alert').hide();
				startDate = new Date(ev.date);
				$('#surveystart').val($('#startDateCal').data('date')).change();
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveystart', 'NOT_VALIDATED').bootstrapValidator('validateField', 'surveystart');
			}
			$('#startDateCal').datepicker('hide');
		});
		
		$('#endDateCal').datepicker()
		.on('changeDate', function(ev){
			if (ev.date.valueOf() < todayDate.valueOf()){
				//$('#alert').show().find('strong').text('The end date can not be less than today date.');
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'INVALID','notEmpty');
				$('#editSurveyForm').data('bootstrapValidator').updateMessage('surveyend', 'notEmpty', 'The end date can not be less than today.');
			}else if (startDate && ev.date.valueOf() <= startDate.valueOf()){
				//$('#alert').show().find('strong').text('The end date can not be less than the start date');
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'INVALID','notEmpty');
				$('#editSurveyForm').data('bootstrapValidator').updateMessage('surveyend', 'notEmpty', 'The end date can not be less than the start date.');
			} else {
				//$('#alert').hide();
				endDate = new Date(ev.date);
				$('#surveyend').val($('#endDateCal').data('date'));
				$('#editSurveyForm').bootstrapValidator('updateStatus', 'surveyend', 'NOT_VALIDATED').bootstrapValidator('validateField', 'surveyend');
			}
			$('#endDateCal').datepicker('hide');
		}); 
		
		 $('#startDateCal')
	        .on('dp.change dp.show', function(e) {
	            // Validate the date when user change it
	            $('#editSurveyForm').bootstrapValidator('revalidateField', 'surveystart');
	        });
		

		$('#editSurveyForm').bootstrapValidator({
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
