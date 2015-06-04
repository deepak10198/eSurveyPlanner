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

<title><c:out value="eSurvey Planner" /></title>

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

<link href="../resources/css/slider.css" rel="stylesheet">

<link href="../resources/css/grayscale.css" rel="stylesheet">


<style type="text/css">
.dialog-background {
	
}
</style>

</head>

<body role="document"
	style="background-color:black; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; max-width: 100%; overflow-x: hidden; background-size: cover;">

	<%@include file="includes/welcomeHeader.jsp"%>

	 <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h3 class="brand-heading">eSurvey Planner</h3>
                        <p class="intro-text">
                        Surveying Just got Easy
                        
                        </p>
                        <a href="#about" class="btn btn-circle page-scroll">
                            <i class="fa fa-angle-double-down animated"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </header>
  <section id="about" class="container content-section text-center" >
        <div class="row" >
            <div class="col-lg-8 col-lg-offset-2">
                <h2>About eSurvey Planner</h2>
                <br>
                <p>eSurvey Planner ........................</p>
               
            </div>
        </div>
    </section>

  <section id="contact" class="container content-section text-center">
        <div class="row" style="background-color: rgba(0, 0, 0, 0.3)">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Contact </h2>
                <p></p>
                <p><a href=""></a>
                </p>
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                  
                    <li>
                        <a href="https://plus.google.com/+Startbootstrap/posts" class="btn btn-default btn-lg"><i class="fa fa-google-plus fa-fw"></i> <span class="network-name">Google+</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </section>

   

		


	<footer>
		<%@include file="includes/footer.jsp"%>
	</footer>

	<!-- ================================================ Bootstrap core JavaScript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

	<script src="../resources/js/jqBootstrapValidation.js"></script>
	<script src="../resources/js/jqBootstrapValidation.js"></script>
	<script src="../resources/js/bootstrap-datepicker.js"></script>
	<script src="../resources/js/bootstrapValidator.js"></script>
	<script src="../resources/js/utility.js"></script>
		<script src="../resources/js/grayscale.js"></script>
	<script src="../resources/js/bootbox.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>


</body>
</html>
<script>
    $('.carousel').carousel({
        interval: 2000 //changes the speed
    })
    </script>

