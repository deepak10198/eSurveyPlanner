<%-- 
    Document   : login
    Created on : Sep 15, 2014, 12:49:12 PM
    Author     : Rakesh.K
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="resources/ico/favicon.ico">


<title>eSurvey Planner</title>
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
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 450px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
	background-color: rgba(0, 0, 0, 0.6);
}

.table th,.table td {
	border-top: none !important;
}

h3 {
	color: white;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'
	style="background:url('http://www.hdwallpapersjpg.com/wp-content/uploads/2015/03/Digital-Math-Free-Wallpaper.jpg') no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">
	<%@include file="includes/welcomeHeader.jsp"%>


	<div id="login-box">

		<h3 style="font-color: white">Login with User name and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='j_spring_security_check' />" method='POST'>

			<table class="table">
				<tr>
					<td style="color: white;"><b>User:</b></td>
					<td><input type='text' name='username' value=''></td>
				</tr>
				<tr>
					<td style="color: white;"><b>Password:</b></td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Login" class="btn" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
	<%@include file="includes/welcomeFooter.jsp"%>
</body>
</html>