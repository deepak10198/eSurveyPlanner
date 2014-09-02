<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="resources/ico/favicon.ico">

		<title><fmt:message key="ServerError"/></title>

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
						<h2><span class="glyphicon glyphicon-user"></span> Error Page!</h2>
					</p>
					Please go to the Home Page!
				</div>
			</div>
			<div class="col-sm-8">
				<div class="list-group">
					
						<h4 class="list-group-item-heading">Error Occured</h4>
						<p class="list-group-item-text">: ${pageContext.errorData.throwable.cause} </p>
						<p class="list-group-item-text">: ${exception.message} </p>
						<p class="list-group-item-text">: ${error} </p>
						<p class="list-group-item-text">: ${error.stackTrace} </p>
					
					
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