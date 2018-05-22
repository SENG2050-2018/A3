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
		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/Styles.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		

	</head>
	<body>
		<div class='flex-container'>
			<div class='flex-row'>
				<form class='centered form' action='j_security_check' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<section class='title'>
						<h1>Login error</h1>
					</section>
					<a href="${pageContext.servletContext.contextPath}/WEB-INF/jsps/Login.jsp">Return to login</a>
				</form>
			</div>
		</div>
	</body>
	
	
</html>