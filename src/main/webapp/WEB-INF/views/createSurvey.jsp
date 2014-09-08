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
							<h2><span class="glyphicon glyphicon-user"></span> Hi, Admin!</h2>
						</p>
						Want to create an online Survey? Just Starting filling the form !
					</div>
				</div>
				<div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
					<form action="submitSurveyMaster" method="POST">
						<div class="form-group">
							<label for="surveyname">Name of the Survey</label>
							<div>
								<input type="text" class="form-control" name="surveyname" id="surveyname" placeholder="Enter Survey Name">
							</div>
						</div>
						<div class="form-group">
							<label for="">Description</label>
							<textarea class="form-control" rows="3" name="description"></textarea>
						</div>
						<div class="form-group">
							<label for="surveystart">Starts From &nbsp;</label><span class="glyphicon glyphicon-calendar"></span> (In format MM-DD-YYYY)
							<div>
								<input type="text" class="form-control" id="surveystart" name="surveystart" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="surveyend">Ends On &nbsp;</label><span class="glyphicon glyphicon-calendar"></span> (In format MM-DD-YYYY)
							<input type="text" class="form-control" id="surveyend" name="surveyend" placeholder="">
						</div>
						<div class="form-group">
							<label for="">Type of Survey</label>
							<div class="radio">
								<label>
									<input type="radio" name="type" id="optionsRadios1" value="fixed" checked>
									Fixed Survey (All Questions are of same type)
								</label>
							</div>
							<!-- <div class="radio">
								<label>
									<input type="radio" name="type" id="optionsRadios2" value="customized">
									Customized Survey (Type of questions can vary)
								</label>
							</div> -->
						</div>

						<div class="form-group">
							<div>
								<button type="submit" class="btn btn-primary">Proceed</button>
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
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datepicker.js"></script>
		<script src="resources/js/bootstrapValidator.js"></script>
		<script src="resources/js/utility.js"></script>
	</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
		//$('#surveystart').datepicker();
		//$('#surveyend').datepicker();

		$('#createsurveyform').bootstrapValidator({
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
						}
					}
				},
				surveystart: {
					validators: {
						notEmpty: {
							message: 'The start date is required and cannot be empty'
						},
						date: {
							format: 'MM/DD/YYYY',
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
							format: 'MM/DD/YYYY',
							message: 'The value is not a valid date'
						}
					}
				}
			}
		});
	});
</script>
