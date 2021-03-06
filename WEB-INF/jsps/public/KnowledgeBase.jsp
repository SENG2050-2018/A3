<!DOCTYPE html>
<!--
	FILENAME:		Login.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	18/05/2018
	BRIEF DESCRIPTION:	
		
-->

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<jsp:include page="urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='centered form' action='' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<section class='title'>
						<h1>Knowledge Base</h1>
					</section>
					<section>
						<p>
							Showing <c:out value="${fn:length(reports)}" /> results
						</p>
					</section>
					
					<ul class='grid links'>
						<c:forEach var='report' items='${reports}'>
							
							<li class='cellSpan'>
								<form action="${pageContext.servletContext.contextPath}/itservices?id=issue" method="post">
									<input type='hidden' name='issue_id' value='${report.id}' />
									<input type='hidden' name='src' value='kb' />
									<button type='submit' class='mimicLink'>
										<h2><c:out value="${report.title}" /></h2>
										<p>Category: <span class='inverse'><c:out value="${report.category}" /></span></p>	
										<p><c:out value="${report.description}" /></p>
									</button>
								</form>
							</li>
							
						</c:forEach>
					</ul>
				</div>
				<div class='whitespace'></div>
			</div>
		</div>
	</body>
	
	
</html>