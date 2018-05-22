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
			
			<div class='flex-row spacing'></div>
			<jsp:include page="urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='centered form'>
					<section class='title'>
						<h1>Issue title</h1>
					</section>
					<section>
						<p>
							category: xxx <br>
							submitted by user: xxxx <br>
						</p>
					</section>
					<section>
						<p>
							description <br>
							resolution <br>
						</p>
					</section>
					<section>
						<p> comments </p>
					</section>
				</div>
				
			</div>
		</div>
	</body>
</html>