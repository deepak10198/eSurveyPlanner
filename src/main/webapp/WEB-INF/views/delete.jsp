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

<Script>
window.onload = function(){
	  document.forms['survey'].submit();
}
</Script>

</head>

<body>
	<%@include file="includes/headerAdmin.jsp"%>
	<c:choose>

		<c:when test="${result == 'surveyDeleted' }">
			<c:redirect url="/viewSurvey" />
		</c:when>


		<c:when test="${result == 'userListDeleted' }">
			<c:redirect url="/viewUserLists" />
		</c:when>
		<c:when test="${result == 'questionDeleted' || result =='questionUpdated'}">
			<form action="../surveyMaster${survey.surveyId }" name="survey"
				method="POST" hidden="true">
				<input type="hidden" name="surveyId" value="${survey.surveyId }" />
				<input type="hidden" name="surveyname" value="${survey.surveyName }" />
				<input type="hidden" name="type" value="${survey.type }" /> <input
					type="hidden" name="description" value="${survey.surveyDesc }" />
				<input type="hidden" name="updateStatus" value="Already_Updated" />
				<input type="Submit">
			</form>
		</c:when>
	
	

	</c:choose>

</body>
</html>