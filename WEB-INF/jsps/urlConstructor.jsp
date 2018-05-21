<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
	FILENAME:		urlConstructor.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	14/05/2018
	BRIEF DESCRIPTION:	
		provides a dynamic representation of the current context and provides links to previous parent pages
-->

<div class='centered spaced' >
	<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices?id=itservices'><c:out value="Home" /></a>
	
	<c:forEach var="parameter" items="${param}" >
		<c:choose>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'report_issue')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Report Issue</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'kb_search')}" >
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'>Knowledge Base</span>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'kb_issue')}" >
				<span class='fa fa-chevron-right'></span>
				<a class='urlPart' href='${pageContext.servletContext.contextPath}/itservices?id=kb_search'>Knowledge Base</a>
				<span class='fa fa-chevron-right'></span>
				<span class='urlPart'><c:out value="issue" /></span> <!-- edit this later -->
			</c:when>
		</c:choose>
	</c:forEach>
</div>