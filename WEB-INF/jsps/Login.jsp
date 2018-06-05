<!DOCTYPE html>
<!--
	FILENAME:		Login.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	18/05/2018
	BRIEF DESCRIPTION:	
		
-->

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>IT Services</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/login.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		

	</head>
	<body>
		<div class='flex-container'>
			<div class='flex-row'>
				<form class='centered form' action='j_security_check' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<section class='title'>
						<h1>IT Services Login</h1>
					</section>
					<div class='login'>
						<section>
							<label for='j_username'>Username: </label>
							<input name='j_username' type='text'>
							
							<label for='j_password'>Password: </label>
							<input name='j_password' type='password'>
							<input type='submit' value="Login" />
						</section>
					</div>
				</form>
			</div>
		</div>
	</body>
	
	
</html>