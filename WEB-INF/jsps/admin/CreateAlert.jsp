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
			<jsp:include page="../public/Header.jsp" />
			
			<div class='flex-row spacing'></div>
			<jsp:include page="../public/urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<form class='centered form' action='itservices?id=create_alert' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<c:choose>
						<c:when test="${sessionScope.received == true}">
							<section class='title'> <!-- Title -->
								<h1>Create Alert</h1>
								<h2>Alert successfully created!</h2>
								<c:remove var="received" scope="session" />
								<a href="${pageContext.servletContext.contextPath}/itservices" class='mimicBtn' style='color:white; padding: 15px 15px 15px 15px; width:calc(100% - 30px);'>Click here to return back to the home page.</a>
							</section>
						</c:when>
						<c:otherwise>
							<section class='title'>
								<h1>Create Alert</h1>
							</section>
							<section>
								<input type='hidden' name='sent' value="true">
							
								<label for='title'>Alert Title: </label>
								<input type='text' name='title'>
								
								<label for 'description'>Alert Description: </label>
								<textarea name='description'></textarea>
								
								<p>
									<label for='startDate'>Start date of alert: </label><input type='date' name='startDate'>
								</p>
								
								<p>
									<label for='startTime'>Start time of alert: </label><input type='time' name='startTime'>
								</p>
								
								<p>
									<label for='endDate'>End date of alert: </label><input type='date' name='endDate'>
								</p>
								
								<p>
									<label for='endTime'>End time of alert: </label><input type='time' name='endTime'>
								</p>
							</section>
							<button type='submit' class='mimicBtn' style='font-size:var(--stdFont); max-width: 150px; color:white; padding: 10px 15px; text-align:center;'>Create Alert!</button>
							<button type='clear' class='mimicBtn' style='font-size:var(--stdFont); max-width: 150px; color:white; padding: 10px 15px; text-align:center;'>Clear</button>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</body>
	
	
</html>