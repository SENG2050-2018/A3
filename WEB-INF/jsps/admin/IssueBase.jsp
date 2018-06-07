<!DOCTYPE html>
<!--
	FILENAME:		Login.jsp
	DEVELOPER(S):	Bradley Turner and Dean Morton
	
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
		<link rel='stylesheet' href="${pageContext.servletContext.contextPath}/css/multiPage.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		
		<script src='${pageContext.servletContext.contextPath}/js/dropdown-mobile-support.js'></script>
		<script src='${pageContext.servletContext.contextPath}/js/multiPage.js'></script>
	</head>
	<body>
		<div class='flex-container'>
			<!-- Top navigation bar -->
			<jsp:include page="../public/Header.jsp" />
			
			<div class='flex-row spacing'></div>
			<jsp:include page="../public/urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='centered form' action='' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<section class='title'>
						<h1>Issues</h1>
					</section>
					<section>
						<p>
							Showing <c:out value="${fn:length(reports)}" /> results
						</p>
					</section>
					<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
					<div id="wrap">
						<c:forEach var='report' items='${reports}'>
							<div>
							
								<form action="${pageContext.servletContext.contextPath}/itservices?id=issue" method="post">
									<input type='hidden' name='issue_id' value='${report.id}' />
									<input type='hidden' name='src' value='ib' />
									
									<button type='submit' class='mimicLink'>
									
										<h2><c:out value="${report.title}" /></h2>
										<p>State: <span class='inverse'><c:out value="${report.state}" /></span>Category: <span class='inverse'><c:out value="${report.category}" /></span></p>	
										<p><c:out value="${report.description}" /></p>
										
									</button>
									
								</form>
							
							</div>
						</c:forEach>
					</div>
					<div class="smallWhitespace"></div>
					<button class="prev" style="vertical-align:middle"><span>Previous Page </span></button>
					<button class="next" style="vertical-align:middle"><span>Next Page </span></button>
					<script src='${pageContext.servletContext.contextPath}/js/multiPage.js'></script>
				</div>
				<div class="smallWhitespace"></div>
			</div>
		</div>
	</body>
	
	
</html>