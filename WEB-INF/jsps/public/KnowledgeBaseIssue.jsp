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
			<jsp:include page="urlConstructor.jsp" />
			
			<!-- Main page image with searchbar overlay -->
			<div class='flex-row'>
				<div class='centered form'>
					<section class='title'>
						<h1><c:out value="${report.title}" /></h1>
					</section>
					<section>
						<p>
							<p><b>Issue State:</b> <span class="inverse"><c:out value="${report.state}" /></span></p>
							<b>Category:</b> <span class="inverse"><c:out value="${report.category}" /></span> <br>
							<p>
								<b>Submitted by User:</b> <c:out value="${report.reporter}" /> <br>
								<b>Lodged: </b><c:out value="${report.reported}" />
							</p>
						</p>
					</section>
					<section>
						<c:if test="${sessionScope.isStaff == true}" >
							Able to access internal websites: <c:out value="${report.internalAccess}" /> </br>
							Tried using an alternate browser: <c:out value="${report.altBrowser}" /> </br>
							Tried restarting my computer: <c:out value="${report.pcRestart}" /> </br> </br>
						</c:if>
						<p>
							Description: <c:out value="${report.description}" /> 
						</p>
					</section>
					<section>
						<p>
							<b>Resolved: 
								<c:if test="${report.state == 'Resolved' || report.state == 'Completed' || report.state == 'Knowledgebase'}" >
									<c:out value="${report.resolved}" /> 
								</c:if>
							</b>
						</p>
						<p>
							<b>Resolution Details: <c:out value="${report.resolution}" /></b> <br>
						</p>
					</section>
					
					<!-- Section for IT Staff to accelerate the workflow -->
					<c:if test="${sessionScope.isStaff == true}" >
						<section>
							<form action="itservices?id=issue" method="post">
								<input type='hidden' name='issue_id' value='${report.id}'>
								<input type='hidden' name='src' value='${sessionScope.src}'>
								
								<c:if test="${report.state == 'New'}" > 
									<input type='hidden' name='flag' value='in-progress'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px'>Mark In-Progress</button></h4>
								</c:if>
								<c:if test="${report.state == 'In-progress'}" > 
									<input type='hidden' name='flag' value='completed'>
									<h4>Admin:</h4>
									<label for='resolutionDetails'>How to resolve the issue:</label><textarea name='resolutionDetails' style='width:100%;height: 100px;' required></textarea>
									<h4> <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px'>Mark Completed</button></h4>
								</c:if>
								<c:if test="${report.state == 'Completed'}" > 
									<h4>Admin: Marked complete. Waiting on reporter.</h4>
								</c:if>
								<c:if test="${report.state == 'Resolved'}" > 
									<input type='hidden' name='flag' value='knowledgebase'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px; color:white; padding: 10px 15px; text-align:center;' >Mark KnowledgeBase</button></h4>
								</c:if>
								<c:if test="${report.state == 'Knowledgebase'}" > 
									<input type='hidden' name='flag' value='resolved'>
									<h4>Admin: <button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px; color:white; padding: 10px 15px; text-align:center;' >Remove from KnowledgeBase</button></h4>
								</c:if>
							</form>
						</section>
					</c:if>
					
					<!-- Section for user who reported the issue to accelerate the workflow -->
					<c:if test="${report.reporter == user.userName}" >
						<c:if test="${report.state == 'Completed'}" > 
							<section>
								<form action="itservices?id=issue" method="post">
									<input type='hidden' name='issue_id' value='${report.id}'>
									<input type='hidden' name='src' value='${sessionScope.src}'>
									<input type='hidden' name='flag' value='resolved'>
									<h4><button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px; color:white; padding: 10px 15px; text-align:center;'>Resolve the issue?</button></h4>
								</form>
								<form action="itservices?id=issue" method="post">
									<input type='hidden' name='issue_id' value='${report.id}'>
									<input type='hidden' name='src' value='${sessionScope.src}'>
									<input type='hidden' name='flag' value='in-progress'>
									<h4><button type='submit' class='mimicBtn' style='font-size: var(--stdFont); max-width: 300px; color:white; padding: 10px 15px; text-align:center;'>Issue still not fixed?</button></h4>
								</form>
							</section>
						</c:if>
					</c:if>
					<section>
						<c:if test="${fn:length(comments) != 0}">
							<h2>Comments</h2>
							<c:forEach var ='comment' items='${comments}'>
								<div>
									<h4 style="margin-bottom: 5px;"><u><c:out value="${comment.commenterUserName}" /></u>:</h4>
									<c:out value="${comment.userComment}" />
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${!(report.state == 'Resolved' || report.state == 'Knowledgebase')}" >
							<h2> Add Comment </h2>
							<form action="itservices?id=issue" method="post">
								<input type='hidden' name='issue_id' value='${report.id}' />
								<input type='hidden' name='user_id' value='${report.reporter}' />
								<input type='hidden' name='src' value='${sessionScope.src}'>
								<textarea name='comment' style='width:100%;height: 100px;'></textarea>
								<br>
								<button type='submit' class='mimicBtn' style='font-size:var(--stdFont); max-width: 150px; color:white; padding: 10px 15px; text-align:center;'>Submit</button>
								<button type='reset' class='mimicBtn' style='font-size:var(--stdFont); max-width: 150px; color:white; padding: 10px 15px; text-align:center;'>Clear</button>
							</form>
						</c:if>
					</section>
				</div>
				
			</div>
		</div>
	</body>
</html>