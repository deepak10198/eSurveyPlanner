<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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

        <!--  script src="resources/js/utility.js"></script>-->

        <script>

            var counter = 0;

            function addQuestions() {
            	var questionValue = document.getElementById("question").value;
                
            	if(questionValue.trim()){
                	 var table = document.getElementById("questionTable");
                     
     				
                     var row = table.insertRow(table.rows.length);
                     
                     row.id = "question" + counter;
                     row.class = "";
                     
                     var cell1 = row.insertCell(0);
                     //cell1.innerHTML ="<label> Question</label>: "+ questionValue + "<input type='hidden' name ='questionText'  value='" + questionValue + "'>";
					 cell1.innerHTML ="<label> Question</label>: "+ questionValue + "<input type='hidden' name ='questionText'  id='questionText" + counter + "'>";
					 document.getElementById("questionText"+counter).value=questionValue;
					 
                     
                     var cell2 = row.insertCell(1);
                     //cell2.innerHTML = "<button type='button' class='btn btn-default' onclick='deleteQuestion(" + row.id + ")'>Delete</button>";
                     cell2.innerHTML = "<a href='#'><span class='glyphicon glyphicon-trash' onclick='deleteQuestion("+row.id+")'></span></a>";
                     
                     document.getElementById("question").value = "";
                     counter += 1;
                	
                }  /* else {
                	
                	alert("First provide some Quesion Text!");
                }   */            
               
            }

            function deleteQuestion(rowId) {
                rowId.remove();
                //counter -= 1;
            }
        </script>

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
                    Add Questions for the Survey
                </div>
            </div>
            <div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
                <form action="saveQuestions" method="POST" id="questionForm">
                    <input type="hidden" name="surveyId" value='${surveyDTO.surveyId}'>
                    <input type="hidden" name="surveyName" value='${surveyDTO.surveyName}'>
                    <input type="hidden" name="ansId" value='${surveyDTO.ansId}'>
                    <input type="hidden" name="ansTypeId" value='${surveyDTO.ansTypeID}'>
                    <div class="output">
                        <label for="surveyname">${surveyDTO.surveyName}</label>

                    </div>
                    <div class="form-group">
                        <!-- <label for="">Type of Answer - </label>
                        <input type="radio"  name="ansType" value="single"/>Single (Radio Buttons)
                        <input type="radio"  name="ansType" value="multiple"/>Multiple (CheckBoxex) -->
                    </div>

                    <div class="form-group">
                        <label for="question">Questions: </label>
						
						<table id="questionTable" class="table table-striped">
							<!-- Space to add questions dynamically. -->
						</table>
                        <input type="text" class="form-control" id="question" placeholder="Question"/>
						<button type="button" class="btn btn-default" onclick="addQuestions()">Add</button>
                        <br/>
                        



                    </div>

                    <div class="form-group">
                        <div>
                            <button type="submit" id ="submitButton" class="btn btn-primary">Proceed</button>
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

	$('#submitButton').on('click', function(){
		var row_count = $('#questionTable tr').length;
		
		if(row_count==0){
			$('#question').attr('required',true);
		}else{
			$('#question').removeAttr('required');
		}
		
		
	});
	
	$('#questionForm').submit(function(e){	
		var row_count = $('#questionTable tr').length;
		
		if(row_count==0 && $('#question').valueOf()){
			e.preventDefault();	
			alert('Please add the question!');
		}
	});
	
});
</script>
