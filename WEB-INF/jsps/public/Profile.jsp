<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<%	//Prevents the user from reloading the page
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
		%>
		<title>IT Services</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1">
		<link rel='stylesheet' href="${pageContext.servletContext.contextPath}/css/Styles.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		
		<script src='${pageContext.servletContext.contextPath}/js/dropdown-mobile-support.js'></script>
	</head>
	<body>
		<div class='flex-container'>
			<!-- Top navigation bar -->
			<jsp:include page="Header.jsp" />
			
			<div class='flex-row spacing'></div>
			<jsp:include page="urlConstructor.jsp"/>
			
			<div class='flex-row'>
				<div class='centered form'>
					<section class='title'>
						<h1>Profile</h1>
					</section>
					<section>
						<h2><c:out value="${user.firstName}" /> <c:out value="${user.surname}" /> <span class='small'>( <c:out value="${user.userName}" /> )</span></h2>
					</section>
					<section>
						<p>
							email: <c:out value="${user.email}" />  <br>
							contact number: <c:out value="${user.contactNumber}" />  <br>
						</p>
					</section>
					
				</div>
				
			</div>
		</div>
	</body>
</html>