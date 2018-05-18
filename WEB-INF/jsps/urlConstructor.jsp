<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
	FILENAME:		urlConstructor.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	14/05/2018
	BRIEF DESCRIPTION:	
		provides a dynamic representation of the current context and provides links to previous parent pages
-->

<div class='centered spaced' >
	<a class='urlPart' href='<%=request.getContextPath()%>/itservices?id=itservices'><c:out value="Home" /></a>
	
	<c:forEach var="parameter" items="${param}" >
		<c:choose>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'report_issue')}" >
				<span class='fa fa-chevron-right'></span>
				<a class='urlPart' href='<%=request.getContextPath()%>/itservices?id=report_issue'><c:out value="Report Issue" /></a>
			</c:when>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'kb_search')}" >
				<span class='fa fa-chevron-right'></span>
				<a class='urlPart' href='<%=request.getContextPath()%>/itservices?id=kb_search'><c:out value="Knowledge Base" /></a>
			</c:when>
		</c:choose>
	</c:forEach>
</div>