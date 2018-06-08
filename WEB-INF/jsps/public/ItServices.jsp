<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
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
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='titleWrapper'>
					<h1 class='pageTitle'><span style='color:white'>IT</span> SERVICES</h1>
					<div class='search'>
						<form action="${pageContext.servletContext.contextPath}/itservices?id=searchKnowledge" id='searchKnowledge' method="post">
							<input name='searchBox' type='text' placeholder='How can we help?' class='searchBox'><button type='submit' class='searchBtn'><span class='fa fa-search'></span></button>
						</form>
					</div>
				</div>
			</div>
			
			<!-- Extra navigational links -->
			<div class='flex-row'>
				<ul class='grid links'>
					<li class='cell offset'>
						<form action="${pageContext.servletContext.contextPath}/itservices?id=kb_search" method="post">
							<button class='mimicBtn' type='submit'>
								<h2>Search Knowledge</h2>
								<p>Search our knowledge base and find answers</p>
							</button>
						</form>
					</li>
					<li class='cell'>
						<form action="${pageContext.servletContext.contextPath}/itservices?id=report_issue" method="post">
							<button class='mimicBtn' type='submit'>
								<h2>Report an Issue</h2>
								<p>Experiencing a problem&#63; Report it here</p>
							</button>
						</form>
					</li>
				</ul>
				
				<c:if test="${fn:length(alerts) != 0}" >
					<!-- Display for maintenance alerts, etc, set by system administrators -->
					<h1 class='subtitle'>Alerts</h1>
					<ul class='alerts grid links'>
						<c:forEach var='alert' items='${alerts}'>
							<li class='cellSpan'>
								<h2>
									<form action="itservices?id=remove_alert" method="post">
										<c:out value="${alert.title}" />
										<c:if test="${sessionScope.isAdmin == true}">
											<input type="hidden" name="alert_id" value="${alert.id}" >
											<button type='submit' class='mimicBtn' style='max-width:120px;float:right;color:white;font-size:var(--stdFont);'>Remove Alert</button>
										</c:if>
									</form>
								</h2>
								<p><c:out value="${alert.description}" /></p>
							</li>
						</c:forEach>	
					</ul>
				</c:if>
			</div>
		</div>
		
	
	</body>
</html>