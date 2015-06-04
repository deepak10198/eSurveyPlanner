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
		
			<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/4.9.0/bootstrap-social.css" rel="stylesheet">
		

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
		<link href="resources/css/font-awesome.css" rel="stylesheet">
	</head>

	<body role="document">

		<%@include file="includes/header.jsp" %>
		
		<div class="row form-div-cus" >
			<div class="col-lg-12" align="center">
				<div class="well">
					<p>
						<h2>${survey.surveyName} </h2>
						<h6><b>${survey.surveyDesc}</b></h6>	</p>
			<br>
			<br>
					<p>
					<h5>${ path}</h5>
					</p>
					
					<ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="#" class="btn btn-social-icon btn-twitter "><i class="fa fa-twitter "></i></a>
                    </li>
                  
                    <li>
                        <a href="#" class="btn btn-social-icon btn-facebook"><i class="fa fa-facebook fa-fw"></i></a>
                    </li>
                     <li>
                        <a href="#" class="btn btn-social-icon btn-google"><i class="fa fa-google-plus fa-fw"></i></a>
                    </li>
                </ul>
                <br><br>
                	<a role="button" class="btn btn-info btn-md" href="./">Go to Home</a>
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