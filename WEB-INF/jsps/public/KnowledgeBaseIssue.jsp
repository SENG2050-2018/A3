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
			<jsp:include page="urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='centered form'>
					<section class='title'>
						<h1><c:out value="${report.title}" /></h1>
					</section>
					<section>
						<p>
							<b>Category:</b> <c:out value="${report.category}" /> <br>
							<b>Submitted by User:</b> <c:out value="${report.reporter}" /> <br>
						</p>
					</section>
					<section>
					Able to access internal websites: <c:out value="${report.internalAccess}" /> </br>
					Tried using an alternate browser: <c:out value="${report.altBrowser}" /> </br>
					Tried restarting my computer: <c:out value="${report.pcRestart}" /> </br> </br>
					
					</section>
					<section>
						<p>
							Description: <c:out value="${report.description}" /> 
						</p>
						<p>
							<b>Resolution: <c:out value="${report.resolution}" /></b> <br>
						</p>
					</section>
					
					<!-- Section for IT Staff to accelerate the workflow -->
					<c:if test="${sessionScope.isStaff == true}" >
						<section>
							<form action="itservices?id=issue" method="post">
								<input type='hidden' name='issue_id' value='${report.id}'>
								<input type='hidden' name='src' value='${sessionScope.src}'>
								
								<c:if test="${report.state == 'new'}" > 
									<input type='hidden' name='flag' value='in-progress'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px'>Mark In-Progress</button></h4>
								</c:if>
								<c:if test="${report.state == 'in-progress'}" > 
									<input type='hidden' name='flag' value='completed'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px'>Mark Completed</button></h4>
								</c:if>
								<c:if test="${report.state == 'completed'}" > 
									<h4>Admin: Marked complete. Waiting on reporter.</h4>
								</c:if>
								<c:if test="${report.state == 'resolved'}" > 
									<input type='hidden' name='flag' value='knowledgebase'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px' >Mark KnowledgeBase</button></h4>
								</c:if>
								<c:if test="${report.state == 'knowledgebase'}" > 
									<input type='hidden' name='flag' value='resolved'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px' >Remove from KnowledgeBase</button></h4>
								</c:if>
							</form>
						</section>
					</c:if>
					
					<!-- Section for user who reported the issue to accelerate the workflow -->
					<c:if test="${report.reporter == user.userName}" >
						<section>
							<form action="itservices?id=issue" method="post">
								<input type='hidden' name='issue_id' value='${report.id}'>
								<input type='hidden' name='src' value='${sessionScope.src}'>
								
								<c:if test="${report.state == 'completed'}" > 
									<input type='hidden' name='flag' value='resolved'>
									<h4><button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px'>Resolve the issue?</button></h4>
								</c:if>
							</form>
						</section>
					</c:if>
					<section>
						<h4>Comments</h4>
						<c:forEach var ='CommentBean' items='${CommentBean}'>
							<div>
								<h2>User Name: <c:out value="${User.userName}" /></h2>
								
								First Name : <c:out value="${User.firstName}" />
								
								Last Name: <c:out value="${User.surname}" />
								
								Email: <c:out value="${User.email}" />
								
								Phone Number: <c:out value="${User.contactNumber}" />
							</div>
						</c:forEach>
						<!-- Need to display all the comments here where report.issue_id = comment.issue_id -->

					
						<p> Add Comment </p>
						<form action="comment" method="post">
							<input type='hidden' name='issue_id' value='${report.id}' />
							<input type='hidden' name='user_id' value='${report.reporter}' />
							<textarea name='comment' rows="4" cols="50"></textarea>
							</br>
							<button type='reset'>Clear</button>
							<button type='submit'>Submit</button>
						</form>
					</section>
				</div>
				
			</div>
		</div>
	</body>
</html>