<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
	FILENAME:		urlConstructor.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	14/05/2018
	BRIEF DESCRIPTION:	
		provides a dynamic representation of the current context and provides links to previous parent pages
-->

<div class='centered spaced' >
	<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices'>Home</a>
	
	<c:forEach var="parameter" items="${param}" >
		<!-- choose statement to select correct link to previous page -->
		<c:choose>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'report_issue')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Report Issue</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'kb_search')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Knowledge Base</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'profile')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Profile</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'issue_base')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Issue Base</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'issue')}" >
				<!-- choose statement to select correct link to the previous page by using url parameter (src) -->
				<c:choose>
					<c:when test="${param.src == 'kb'}" >
						<span class='fa fa-chevron-right'></span>
						<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices?id=kb_search'>Knowledge Base</a>
					</c:when>
					<c:when test="${param.src == 'ib'}" >
						<span class='fa fa-chevron-right'></span>
						<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices?id=issue_base'>Issue Base</a>
					</c:when>
					<c:when test="${param.src == 'profile'}" >
						<span class='fa fa-chevron-right'></span>
						<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices?id=profile'>Profile</a>
					</c:when>
				</c:choose>
				
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'><c:out value="${report.title}" /></span> <!-- edit this later -->
			</c:when>
		</c:choose>
	</c:forEach>
</div>