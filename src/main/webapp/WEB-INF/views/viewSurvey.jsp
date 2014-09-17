<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="../../resources/ico/favicon.ico">

        <title><c:out value="${survey.surveyName}"/></title>

        <!-- Bootstrap core CSS -->
        <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="../../resources/css/bootstrap-theme.min.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <link href="../../resources/css/datepicker.css" rel="stylesheet">

        <link href="../../resources/css/bootstrapValidator.css" rel="stylesheet">

        <link href="../../resources/css/master.css" rel="stylesheet">
    </head>

    <body role="document">
        <%@include file="includes/headerUser.jsp" %>

        <div class="row form-div-cus">
            <div class="col-sm-4">
                <div class="well">
                    <p>
                    <h2><span class="glyphicon glyphicon-user"></span><c:out value="${survey.surveyName}"/></h2>
                    </p>
                   <c:out value="${survey.surveyDesc}"/>
                </div>
            </div>
			<div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
					<%-- <form:form name="f" action="submitSurveyResponse" method="POST" commandName="survey"> --%>
					<form action="submitSurveyResponse" method="POST">
					<input type="text" name="email"/>
						<input type="hidden" name="surveyId" value='${survey.surveyId}'>
					    <input type="hidden" name="surveyName" value='${survey.surveyName}'>
                                            
					
					
						<div class="form-group">
							<label for="surveyname"><h2> <c:out value="${survey.surveyName}"/></h2></label>
						</div>
						<c:forEach items="${survey.surveyQuestions}" var = "question" varStatus="status">
						
							<div class="form-group">
							
							
							<%-- <form:hidden path="surveyQuestions[${status.index}].questionId" value='${question.questionId}'/>
							<form:hidden path="surveyQuestions[${status.index}].ansTypeId" value='${question.ansTypeId}'/>
							<form:hidden path="surveyQuestions[${status.index}].quesAnswerId" value='${question.quesAnswerId}'/>
							<label for="question"><c:out value="${question.questionText}"/></label> --%>
							
							<input type="hidden" name="surveyQuestions[${status.index}].questionId" value='${question.questionId}'>
							<input type="hidden" name="surveyQuestions[${status.index}].ansTypeId" value='${question.ansTypeId}'>
							<input type="hidden" name="surveyQuestions[${status.index}].quesAnswerId" value='${question.quesAnswerId}'>
							<label for="question${status.index}"><c:out value="${question.questionText}"/></label>
							<div>
								<c:forEach var="answer" items="${question.answers}" varStatus="ansStatus">
									<%-- <form:hidden path="surveyQuestions[${status.index}].questionId" value='${question.questionId}'/>
                                	<input type="${question.ansType}"  name="surveyQuestions[${status.index}].answers[${ansStatus.index}].ansId" value="${answer.ansId}"/> <c:out value="${answer.ansText}"/></br>
                                     --%>
                                     
                                     <%-- <input type="${question.ansType}"  name="surveyQuestions[${status.index}].ansTextList" value="${answer.id}"/> <c:out value="${answer.text}"/></br> --%>
                                     
                                     <c:choose>
                                     
                                     
                                    	<c:when test="${question.ansTypeId == 1 }" >
                                    		<%-- <form:radiobutton  path="surveyQuestions[${status.index}].ansIdList" id="${answer.ansId}" value="${answer.ansId}" label="${answer.ansText}" checked=""/></br> --%>
                                    		<input type="radio"  name="surveyQuestions[${status.index}].ansTextList" id="surveyQuestions[${status.index}].ansTextList" value="${answer.id}"/> <c:out value="${answer.text}"/></br>
                                    		
                                    	</c:when>
                                    	<c:when test="${question.ansTypeId == 2 }" >
                                    		<%-- <form:checkbox  path="surveyQuestions[${status.index}].ansIdList" id="${answer.ansId}" value="${answer.ansId}" label="${answer.ansText}" checked=""/></br> --%>
                                    		<input type="checkbox"  name="surveyQuestions[${status.index}].ansTextList" id="surveyQuestions[${status.index}].ansTextList" value="${answer.id}"/> <c:out value="${answer.text}"/></br>
                                    	</c:when>
                                    </c:choose>
                                    
                                    
                                                            
                                </c:forEach>
                                                       
							</div>
							</div>
						</c:forEach>
						
						<div class="form-group">
							<div>
								<button type="submit" class="btn btn-primary">Submit Survey</button>
							</div>
						</div>

						<!--<button type="submit" class="btn btn-lg btn-primary">Proceed</button>
						<button type="button" class="btn btn-info" id="validateBtn">Manual validate</button> -->
					<%-- </form:form>	 --%>
						
					
					
					</form>	
				</div>

        </div>

	

	<%@include file="includes/footer.jsp" %>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap.min.js"></script>
        <script src="../../resources/js/bootstrap-datepicker.js"></script>
        <script src="../../resources/js/bootstrapValidator.js"></script>
        <script src="../../resources/js/utility.js"></script>
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
