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
							<h3><span class="glyphicon glyphicon-user"></span> Hi, Admin!</h3>
						</p>
						<br>
						Here you can create the user list which can be mapped to surveys!
					</div>
					
					<div class="well">
					<b><span class="glyphicon glyphicon-hand-up"></span> &nbsp;Click here to see   <a  href="viewUserLists" style="text-decoration:none">Existing User List</a> ....</b>
					</div>
				</div>
				
					
			
				<div class="col-sm-8" style="border:1px solid #d9d9d9; padding:1em; border-radius:4px;">
				
					<form role="form" action="userList" method="POST" id="createuserlist" name="uploadFile" enctype="multipart/form-data">
					
						<div class="form-group">
							<label for="userlistname">Name of the User List</label>
							<div>
								<input type="text" class="form-control" name="userListName" id="userListName" placeholder="Enter User List Name">
							</div>
						</div>
						<div class="form-group">
							<label for="">Description</label>
							<textarea class="form-control" rows="3" name="description"></textarea>
						</div>
						<div class="form-group">
							<label for="file">Browse the Excel sheet</label> (Sheet in Excel Format ( .xls ))&nbsp; &nbsp;<a href=format target="_blank" data-toggle="tooltip" title="User-List Description"><span class="glyphicon glyphicon-pushpin"></span></a>
							<div>
								<input type="file" class="form-control" id="file" name="file" placeholder="Survey file">
							</div>
						</div>
						

						<div class="form-group">
							<div>
								<button type="submit" class="btn btn-primary">Proceed</button>
							</div>
						</div>
					
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
		$('[data-toggle="tooltip"]').tooltip(); 
		
		
		$('#createuserlist').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				userListName: {
					validators: {
						notEmpty: {
							message: 'The UserList Name is required and cannot be empty'
						}
					}
				},
				
				file: {
					validators: {
						
							file:	 {
												 extension: 'xls',
			                    	   			 type: 'application/vnd.ms-excel',
			                    	   			 maxSize: 20971520,// 2048 * 1024
			                    	  			 message: 'The selected file is not valid'
									}
								}
							
					},
					
				
			}
		});
	});
</script>
