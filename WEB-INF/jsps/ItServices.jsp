<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>IT Services</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1">
		<link rel='stylesheet' href="${pageContext.servletContext.contextPath}/css/Styles.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
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
						<form id='kwSearch'>
							<input name='searchBox' type='text' placeholder='How can we help?' class='searchBox'><button type='submit' class='searchBtn'><span class='fa fa-search'></span></button>
						</form>
					</div>
				</div>
			</div>
			
			<!-- Extra navigational links -->
			<div class='flex-row'>
				<ul class='grid links'>
					<li class='cell offset'>
						<a href="${pageContext.servletContext.contextPath}/itservices?id=kb_search" class='mimicBtn'>
							<h2>Search Knowledge</h2>
							<p>Search our knowledge base and find answers</p>
						</a>
					</li>
					<li class='cell'>
						<a href="${pageContext.servletContext.contextPath}/itservices?id=report_issue" class='mimicBtn'>
							<h2>Report an Issue</h2>
							<p>Experiencing a problem&#63; Report it here</p>
						</a>
					</li>
				</ul>
				
				<!-- Display for maintenance alerts, etc, set by system administrators -->
				<h1 class='subtitle'>Alerts</h1>
				<ul class='alerts grid links'>
					<c:forEach var='alert' items='${alerts}'>
						<li class='cellSpan'>
							<h2><c:out value="${alert.title}" /></h2>
							<p><c:out value="${alert.description}" /></p>
						</li>
					</c:forEach>	
				</ul>
			</div>
		</div>
		
	
	</body>
</html>