<!DOCTYPE html>
<!--
	FILENAME:		Login.jsp
	DEVELOPER(S):	Bradley Turner and Dean Morton
	
	LAST MODIFIED:	18/05/2018
	BRIEF DESCRIPTION: Admins can view and edit Registered Users 	
		
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
						<h1>View Users</h1>
					</section>
					<section>
							Click on an user to edit them
					</section>
				
					<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
					<div id="wrap">
						<c:forEach var ='User' items='${User}'>
						
									<div>
										<h2>User Name: <c:out value="${User.userName}" /></h2>
										</br>
										First Name : <c:out value="${User.firstName}" />
										</br>
										Last Name: <c:out value="${User.surname}" />
										</br>
										Email: <c:out value="${User.email}" />
										</br>
										Phone Number: <c:out value="${User.contactNumber}" />
										</div>
														
						</c:forEach>
						</div>	
						</br>
						</br>
			
											
											
						<button class="prev" style="vertical-align:middle"><span>Previous Page </span></button>
						<button class="next" style="vertical-align:middle"><span>Next Page </span></button>
						<script src='${pageContext.servletContext.contextPath}/js/multiPage.js'></script>
				</div>
			</div>
		</div>
	</body>
	
	
</html>