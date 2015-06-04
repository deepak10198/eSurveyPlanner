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

            var quesCount = 0;
            var ansCount = [];	
    		
            
            function addQuestionAnswer() {
            	
            	 var table = document.getElementById("questionTable");
                 //alert(">>2");
                 var questionValue = document.getElementById("question").value;
            	
            	if(questionValue){
            		ansCount[quesCount]=0;
                    
                    var row = table.insertRow(table.rows.length);
                    row.id = "question" + quesCount;
                    row.class = "";
                    
                    var rowId = row.id;

                    var cell1 = row.insertCell(0);
                    //cell1.innerHTML = "<label> Question</label>: " + questionValue + "<input type='hidden' name ='surveyQuestions[" + quesCount + "].questionText' value='"+questionValue+"' >";
                    cell1.innerHTML = "<label> Question</label>: " + questionValue + "<input type='hidden' name ='surveyQuestions[" + quesCount + "].questionText' id='questionText"+quesCount+"' >";
                   
                    cell1.innerHTML += "<br/><div class='form-group'> "+
    							"<label for=''>Type of Answer -  </label>"+
                                 	"<c:forEach var='answerTypeMaster' items='${answerTypeMaster}' varStatus='types'>";
                                        if('${answerTypeMaster.id}'==1){
                                        		cell1.innerHTML +="&nbsp; <input type='radio' name='surveyQuestions[" + quesCount + "].ansTypeId' value='${answerTypeMaster.id}' id='ansType"+quesCount+"${types.count}' checked onClick='uncheck("+quesCount+")'>"+
	     										"<c:out value='${answerTypeMaster.ansTypeText}' /> (Single Choice) ";
                                        }else if('${answerTypeMaster.id}'==2){
                                    		cell1.innerHTML +="&nbsp; <input type='radio' name='surveyQuestions[" + quesCount + "].ansTypeId' value='${answerTypeMaster.id}' id='ansType"+quesCount+"${types.count}' onClick='uncheck("+quesCount+") '>"+
     										"<c:out value='${answerTypeMaster.ansTypeText}' /> (Multiple Choice)";
                                        }
                                        else if('${answerTypeMaster.id}'==3){
                                    		cell1.innerHTML +="&nbsp; <input type='radio' name='surveyQuestions[" + quesCount + "].ansTypeId' value='${answerTypeMaster.id}' id='ansType"+quesCount+"${types.count}' onClick='check("+quesCount+")'>"+
     										"<c:out value='${answerTypeMaster.ansTypeText}'  /> (Free text)";
                                        } 
                                 	cell1.innerHTML += "</c:forEach>"+
                                                           
    						"</div>"+
    						
    						"<div class='form-group'>"+
    							"<label for='surveyname'>Answers: </label>"+
    						
    								"	<div class='form-group'>"+
    								"<div style='display:table-column;  width:96%; height:100%; float:left; '>	<input type='text' class='form-control'  name=answers"+quesCount+" id='answers"+quesCount+"' placeholder='Answers'/></div>"+
    								"<div style='display:table-column;  width:4%; height:100%; float:left; vertical-align: bottom;' align='center' id='divOther"+quesCount+"' ><input type='checkbox' name='otherinfo' id='otherinfo"+quesCount+"'/></div>"+  							
    								
    								"<button type='button' class='btn btn-default' id='addanswer"+quesCount+"' onclick='addDynamicRow("+quesCount+")'>Add</button>"+
    								"<table id=answerTable"+quesCount+" class='table table-striped'></table>"+						
    								
    								"</div>"+		
    				"</div>";
                    
    				document.getElementById("questionText"+quesCount).value = questionValue;
    				
    				 var mandateStatus;
					 var mandate;
		            	if (document.getElementById('mandatoryStatustrue').checked == true) { mandateStatus = "true"; mandate="Mandatory";}
		            	else  {mandateStatus = "false"; mandate="Non-Mandatory";}
                     
					 var cell3 = row.insertCell(1);
                   	 cell3.innerHTML = mandate + "<input type='hidden' name ='surveyQuestions[" + quesCount + "].mandatory'  id='mandatory" + quesCount + "'>";   
                   	 document.getElementById("mandatory"+quesCount).value=mandateStatus;
					 
    				 var cell2 = row.insertCell(2);
                    cell2.innerHTML ="<a href='#'><span class='glyphicon glyphicon-trash' onclick='deleteQuestion("+row.id+")'></span></a>";
                    
                    document.getElementById("question").value = "";
                    quesCount += 1;
            		
            	}else {
            		
            		//alert("First provide some question Text!");
            	}
            	
            	

            }

            function deleteQuestion(rowId) {
                rowId.remove();
                //quesCount -= 1;
            }
            
            
			function addDynamicRow(tableId){
				var table = document.getElementById('answerTable'+tableId);
				var row_count = table.rows.length;
				var answerValue = document.getElementById('answers'+tableId).value;
				
				if (row_count>=10){
					alert("Sorry! You can add maximum of 10 answers!");
						
				} else if(answerValue) {
					
					
					var row = table.insertRow(table.rows.length);	
					row.id = "answer"+tableId+row_count;
					row.class="";
					
					var rowId=row.id;
					
					var cell1 = row.insertCell(0);
					//cell1.innerHTML = "<label> Answer</label>: "+answerValue+"<input type='hidden' name ='surveyQuestions[" + tableId + "].ansTextList' value='"+answerValue+"' >";
					cell1.innerHTML = "<label> Answer</label>: "+answerValue+"<input type='hidden' name ='surveyQuestions[" + tableId + "].ansTextList' id='ansTextList"+tableId+row_count+"' >";
					document.getElementById("ansTextList"+tableId+row_count).value = answerValue;
					
					
					
					if( document.getElementById("otherinfo"+tableId).checked==true)
					{
						var otherinfo = "true";$("#otherinfo"+tableId).attr("disabled", true);
						$("#otherinfo"+tableId).prop("checked", false);
						var otherdisplay ="Comment box Enabled";
					}
					else{var otherinfo = "false";var otherdisplay =""; }
					
					var cell3 = row.insertCell(1);
					cell3.innerHTML = otherdisplay+"<input type='hidden' name='surveyQuestions[" + tableId + "].other' id='other"+tableId+row_count+"'/>";
					document.getElementById("other"+tableId+row_count).value=otherinfo;
					
					var cell2 = row.insertCell(2);
					cell2.innerHTML =  "<a href='#'><span class='glyphicon glyphicon-trash' onclick='deleteDynamic("+rowId+")'></span></a>";
					
					document.getElementById('answers'+tableId).value="" ;
					
					
					  $('#mySwitch').bootstrapSwitch('toggleState');
	                 	$('#mySwitch').bootstrapSwitch('setState',true); 
					ansCount[tableId] +=1;
								
				}else{
					
					//alert("First provide some answer Text!");
				}
					
			}
			
			function deleteDynamic(rowId){
				rowId.remove();
				//ansCount[rowId] -=1;
			}
            
        </script>

        <link href="resources/css/datepicker.css" rel="stylesheet">

        <link href="resources/css/bootstrapValidator.css" rel="stylesheet">

        <link href="resources/css/master.css" rel="stylesheet">
        
          <link href="resources/css/bootstrap-switch.css" rel="stylesheet">
          
         <link href="resources/css/bootstrap-toggle.min.css" rel="stylesheet">
          
        
    </head>

    <body role="document">

        <%@include file="includes/header.jsp" %>

        <div class="row form-div-cus">
            <div class="col-sm-4">
                <div class="well">
                    <p>
                    <h2><span class="glyphicon glyphicon-user"></span> Hi, Admin!</h2>
                    </p>
                    Add Questions and their answers for the Survey
                </div>
            </div>
            <div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
                <form id="questionForm" action="saveQuestionAns" method="POST">
                    <input type="hidden" name="surveyId" value='${surveyDTO.surveyId}'>
                    <input type="hidden" name="surveyName" value='${surveyDTO.surveyName}'>
                    <div class="output">
                        <label for="surveyname">${surveyDTO.surveyName}</label>

                    </div>
                    <div class="form-group">
                        <!-- <label for="">Type of Answer - </label>
                        <input type="radio"  name="ansType" value="single"/>Single (Radio Buttons)
                        <input type="radio"  name="ansType" value="multiple"/>Multiple (CheckBoxex) -->
                    </div>

                    <div class="form-group">
                        <label for="question">Questions: </label><br/>
						<div class="col-sm-16" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
							<table id="questionTable" class="table table-striped">
								<!-- Space to add the Questions dynamically. -->
							</table>
						</div>
						<br/>
								</table>
	 <div class="form-group">
           <div style="display:table-row; width:100%; float:left; "> 
   				<div style="display:table-column;  width:65%; height:100%; float:left; " ><input type="text" class="form-control" id="question" placeholder="Question"/>	</div>
  
<div style="display:table-column;  width:35%; height:100%; float:left; vertical-align: top;" align="center" ><input type='checkbox' name='mandatoryStatus'  data-toggle="toggle" value='true' id='mandatoryStatustrue' checked  data-on="Mandatory" data-off="Non-Mandatory" data-onstyle="danger" data-offstyle="success"/>
						</div>
		 </div>
      </div>

                        <button type="button" class="btn btn-default" onclick="addQuestionAnswer()">Add</button>
                        <br/>
                        


                    </div>

                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-primary" id="submitButton">Proceed</button>
                             <a href="./e/survey${surveyDTO.surveyId}" >  <button type="button" id ="submitButton" class="btn btn-primary">Back</button> </a>
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
         <script src="resources/js/bootstrap-switch.js"></script> 
          <script src="resources/js/bootstrap-toggle.min.js"></script>
    </body>
</html>
<script type="text/javascript">
$("[name='mandatoryStatus']").bootstrapToggle('status');
$(document).ready(function() {

	$('#submitButton').on('click', function(){
		var row_count = $('#questionTable tr').length;
		
		if(row_count==0){
			$('#question').attr('required',true);
		}else{
			$('#question').removeAttr('required');
			for (var i=0; i<quesCount;i++){
				
				if( $('#answerTable'+i).length){
					var ans_count = $('#answerTable'+i+' tr').length;
					if(ans_count==0){
						$('#answers'+i).attr('required',true);
					}else{
						$('#answers'+i).removeAttr('required');
					}
					
				}
				
				
			}
		}
		
		
		
		
	});
	
	/* $('#questionForm').submit(function(e){	
		var row_count = $('#questionTable tr').length;
		
		if(row_count==0 && $('#question').valueOf()){
			e.preventDefault();	
			alert('Please add the question!');
		}else{
			for (var i=0; i<quesCount;i++){
				
				if( $('#answerTable'+i).length){
					var ans_count = $('#answerTable'+i+' tr').length;
					if(ans_count==0 && $('#answers'+i).valueOf()){
							e.preventDefault();	
							var ques=$('#questionText'+i).val();
							alert("Please add the answers for question:"+ ques );
							break;
						}
				}
			}
		}
	}); */
	
	

			
	});

function check(value)
{
	
		 $("#answers"+value).attr("disabled", "disabled"); 
		 $("#otherinfo"+value).attr("disabled", "disabled"); 
		 $("#addanswer"+value).hide();
		 $("#answerTable"+value).hide();
		 
	
	}
function uncheck(value)
{
		 $("#answers"+value).removeAttr("disabled"); 
		 $("#otherinfo"+value).removeAttr("disabled"); 
		 $("#addanswer"+value).show();
		 $("#answerTable"+value).show();
		 
	
	
	}
</script>

