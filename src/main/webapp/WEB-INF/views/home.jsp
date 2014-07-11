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
					Please select the option from the menu.
				</div>
			</div>
			<div class="col-sm-8">
				<div class="list-group">
					<a href="createSurvey" class="list-group-item">
						<h4 class="list-group-item-heading">Create new Survey</h4>
						<p class="list-group-item-text">Click to start a new survey.</p>
					</a>
					<a href="viewSurvey" class="list-group-item">
						<h4 class="list-group-item-heading">View Survey</h4>
						<p class="list-group-item-text">Click to view your already created surveys and publish them. </p>
					</a>
					<a href="createUserList" class="list-group-item">
						<h4 class="list-group-item-heading">Create User List</h4>
						<p class="list-group-item-text">Click to create the list of users to which you want to send the survey link.</p>
					</a>
				</div>
			</div>
		</div>
		
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		<%@include file="includes/footer.jsp" %>

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datepicker.js"></script>
		<script src="resources/js/bootstrapValidator.js"></script>
	</body>
</html>