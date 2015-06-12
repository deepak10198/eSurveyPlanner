<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script src="http://getbootstrap.com/dist/js/bootstrap.js"></script>
<script>
	function waiting() {
		$('#sending').show();
//		window.scrollTo(0, 0);

		

		$("html, body").animate({ scrollTop: 0 }, 20);
	}
</script>
<style>
.progress {
	background: rgba(245, 245, 245, 1);
	background: -webkit-linear-gradient(top, rgba(48, 21, 21, 1) 0%,
		rgba(245, 245, 245, 1) 100%);
	background: linear-gradient(to bottom, rgba(48, 21, 21, 1) 0%,
		rgba(245, 245, 245, 1) 100%);
	border: 0px solid rgba(71, 24, 24, 1);
	border-radius: 4px;
	height: 30px;
}



}
</style>
</head>

<body role="document">

	<%@include file="includes/header.jsp"%>


	<div class="row form-div-cus">
		<div class="col-lg-12" id="sending" hidden="hidden" >
			<div class="progress">
				<div class="progress-bar progress-bar-success progress-bar-striped active" role="progress-bar"
					style="width: 100%">
					<h5><b>Sending</b></h5></div>
			</div>

 
		</div>

		<div class="col-sm-4">
			<div class="well">
				<p>
				<h2>
					<span class="glyphicon glyphicon-user"></span> Hi, Admin!
				</h2>
				</p>
				<br>
				<h6>
					<b>Here you can send Survey URL to the Users....</b>
				</h6>
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
				</table>

				</p>

			</div>
		</div>
		<div class="col-sm-8"
			style="border: 1px solid #d9d9d9; padding: 1em; border-radius: 4px;">
			<form action="mail" method="POST" name="sendmailform"
				id="sendmailform">

				<input type="hidden" name="surveyId" id="surveyId"
					value="${survey.surveyId }" />
				<div class="form-group">
					<label for="sendername">From</label>
					<div>
						<input type="text" class="form-control" name="sender" id="sender"
							placeholder="Senders Email Address">
					</div>
				</div>
				<div class="form-group">
					<label for="senderpassword">Password</label>
					<div>
						<input type="password" class="form-control" name="password"
							id="password" placeholder="Senders Password">
					</div>
				</div>
				<div class="form-group">
					<label for="receivername">To</label>
					<div>
						<select class="form-control" name="rListId" id="rListId">
							<option value="-1">Select User List</option>
							<c:forEach var="userlist" items="${user}">

								<option value="${userlist.userlistId }">${userlist.userListName
									}</option>

							</c:forEach>

						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="Subject">Subject</label>
					<div>
						<input type="text" class="form-control" name="subject"
							id="subject" value="${survey.surveyname } - Survey" disabled>
						<input type="hidden" name="subject" id="subject"
							value="${survey.surveyname }" />
					</div>
				</div>

				<div class="form-group">
					<label for="message">Message</label>
					<textarea class="form-control" rows="8" cols="50" name="message"
						id='message'>Dear User,&#13;&#10;&#13;&#10;${survey.description }&#13;&#10;&#13;&#10;Please fill this survey in between ${survey.surveystart } and ${survey.surveyend }</textarea>
				</div>

				<div class="form-group">
					<label for="Link">Survey Link</label>
					<div>
						<input type="text" class="form-control" name="Link" id="Link"
							value="${path }/s/survey${survey.surveyId }" disabled="disabled">
						<input type="hidden" name="surveylink" id="surveylink"
							value="${path }/s/survey${survey.surveyId }" />
					</div>
				</div>



				<button type="submit" class="btn btn-primary" onClick="waiting()">Send</button>

			</form>
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
</body>
</html>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var startDate;// = new Date();
						var endDate;// = new Date();
						//endDate.setDate(startDate.getDate() + 1);

						var todayDate = new Date();
						todayDate.setDate(todayDate.getDate() - 1);

						$('#startDateCal')
								.datepicker()
								.on(
										'changeDate',
										function(ev) {
											if (ev.date.valueOf() < todayDate
													.valueOf()) {
												var validator = $(
														'#createSurveyForm')
														.data(
																'bootstrapValidator');
												//$('#alert').show().find('strong').text('The start date can not be less than today date.');
												$('#surveystart').val(
														$('#startDateCal')
																.data('date'))
														.change();
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveystart',
																'INVALID',
																'notEmpty');
												$('#createSurveyForm')
														.data(
																'bootstrapValidator')
														.updateMessage(
																'surveystart',
																'notEmpty',
																'The start date can not be less than today.');
											} else if (endDate
													&& ev.date.valueOf() > endDate
															.valueOf()) {
												//$('#alert').show().find('strong').text('The start date can not be greater than the end date');
												$('#surveystart').val(
														$('#startDateCal')
																.data('date'))
														.change();
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveystart',
																'INVALID',
																'notEmpty');
												$('#createSurveyForm')
														.data(
																'bootstrapValidator')
														.updateMessage(
																'surveystart',
																'notEmpty',
																'The start date can not be greater than the end date.');
											} else {
												//$('#alert').hide();
												startDate = new Date(ev.date);
												$('#surveystart').val(
														$('#startDateCal')
																.data('date'))
														.change();
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveystart',
																'NOT_VALIDATED')
														.bootstrapValidator(
																'validateField',
																'surveystart');
											}
											$('#startDateCal').datepicker(
													'hide');
										});

						$('#endDateCal')
								.datepicker()
								.on(
										'changeDate',
										function(ev) {
											if (ev.date.valueOf() < todayDate
													.valueOf()) {
												//$('#alert').show().find('strong').text('The end date can not be less than today date.');
												$('#surveyend').val(
														$('#endDateCal').data(
																'date'));
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveyend',
																'INVALID',
																'notEmpty');
												$('#createSurveyForm')
														.data(
																'bootstrapValidator')
														.updateMessage(
																'surveyend',
																'notEmpty',
																'The end date can not be less than today.');
											} else if (startDate
													&& ev.date.valueOf() < startDate
															.valueOf()) {
												//$('#alert').show().find('strong').text('The end date can not be less than the start date');
												$('#surveyend').val(
														$('#endDateCal').data(
																'date'));
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveyend',
																'INVALID',
																'notEmpty');
												$('#createSurveyForm')
														.data(
																'bootstrapValidator')
														.updateMessage(
																'surveyend',
																'notEmpty',
																'The end date can not be less than the start date.');
											} else {
												//$('#alert').hide();
												endDate = new Date(ev.date);
												$('#surveyend').val(
														$('#endDateCal').data(
																'date'));
												$('#createSurveyForm')
														.bootstrapValidator(
																'updateStatus',
																'surveyend',
																'NOT_VALIDATED')
														.bootstrapValidator(
																'validateField',
																'surveyend');
											}
											$('#endDateCal').datepicker('hide');
										});

						$('#startDateCal').on(
								'dp.change dp.show',
								function(e) {
									// Validate the date when user change it
									$('#createSurveyForm').bootstrapValidator(
											'revalidateField', 'surveystart');
								});

						$('#sendmailform')
								.bootstrapValidator(
										{
											message : 'This value is not valid',
											feedbackIcons : {
												valid : 'glyphicon glyphicon-ok',
												invalid : 'glyphicon glyphicon-remove',
												validating : 'glyphicon glyphicon-refresh'
											},
											fields : {
												sender : {
													validators : {
														notEmpty : {
															message : 'Email id is required and cannot be empty'
														},
														emailAddress : {
															max : 100,
															message : 'fill Valid Email id'
														}
													}
												},

												password : {
													validators : {
														notEmpty : {
															message : 'Password required '
														},

													}
												},
												surveyend : {
													validators : {
														notEmpty : {
															message : 'The end date is required and cannot be empty'
														},
														date : {
															format : 'DD-MM-YYYY',
															message : 'The value is not a valid date'
														}
													}
												}
											}
										});
					});
</script>
