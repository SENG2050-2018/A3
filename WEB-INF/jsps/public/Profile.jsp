<!DOCTYPE html>
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
			<jsp:include page="urlConstructor.jsp"/>
			
			<div class='flex-row'>
				<div class='centered form'>
					<section class='title largeScreenOnly'>
						<h1>Profile</h1>
					</section>
					<section>
					
						<h2><c:out value="${user.firstName}" /> <c:out value="${user.surname}" /> <span class='small'>( <c:out value="${user.userName}" /> )</span></h2>
					
						<p class='contactInfo'>
							<span><b>Email:</b></span> <c:out value="${user.email}" />  <br>
							<span><b>Contact number:</b></span> <c:out value="${user.contactNumber}" />  <br>
						</p>
					</section>
					
				</div>
				<div class='centered form'>
					<section class='title'>
						<h2>Current transactions</h2>
					</section>
					<c:set var='count' value='${fn:length(current)}' />
					<c:if test='${count != 0}' >
						<ul class='grid links'>
							<c:forEach var='report' items='${current}'>
								
								<li class='cellSpan'>
									<form action="${pageContext.servletContext.contextPath}/itservices?id=issue" method="post">
										<input type='hidden' name='issue_id' value='${report.id}' />
										<input type='hidden' name='src' value='profile' />
										<button type='submit' class='mimicLink'>
											<h2><c:out value="${report.title}" /></h2>
											<p>State: <span class='inverse'><c:out value="${report.state}" /></span></p>	
											<p>Reported: <span class='inverse'><c:out value="${report.reported}" /></span></p>	
										</button>
									</form>
								</li>
								
							</c:forEach>
							
						</ul>
					</c:if>
					<c:if test='${count == 0}'>
						<p>You do not have any current issues reported.</p>
					</c:if>
				</div>
				<div class='centered form'>
					<section class='title'>
						<h2>Previous transactions</h2>
					</section>
					<c:set var='count' value='${fn:length(previous)}' />
					<c:if test='${count != 0}' >
						<ul class='grid links'>
							<c:forEach var='report' items='${previous}'>
								
								<li class='cellSpan'>
									<form action="${pageContext.servletContext.contextPath}/itservices?id=issue" method="post">
										<input type='hidden' name='issue_id' value='${report.id}' />
										<input type='hidden' name='src' value='profile' />
										<button type='submit' class='mimicLink'>
											<h2><c:out value="${report.title}" /></h2>
											<p>State: <span class='inverse'><c:out value="${report.state}" /></span></p>	
											<p>Reported: <span class='inverse'><c:out value="${report.reported}" /></span></p>	
										</button>
									</form>
								</li>
								
							</c:forEach>
							
						</ul>
					</c:if>
					<c:if test='${count == 0}'>
						<p>You do not have any previously reported issues.</p>
					</c:if>
				</div>
				<div class='whitespace'></div>
			</div>
		</div>
	</body>
</html>