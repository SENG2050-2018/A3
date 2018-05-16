<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
	FILENAME:		urlConstructor.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	14/05/2018
	BRIEF DESCRIPTION:	
		provides a dynamic representation of the current context and provides links to previous parent pages
-->

<div class='centered spaced' >
	<!--
	<c:forTokens items="${requestScope['javax.servlet.forward.request_uri']}" delims="/?" var="part" varStatus='status'>
		<c:choose>
			<c:when test="${part == 'itservices'}" >
				<a class='urlPart' href='%=request.getContextPath()%>/itservices'><c:out value="Home" /></a>
			</c:when>
		</c:choose>
		<c:if test="${!((status.last) || part == 'Assignment3')}"><span class='fa fa-chevron-right'></span></c:if>
	</c:forTokens>
	<c:forEach items="${paramValues}" var="param" varStatus='status'>
	
	</c:forEach>
	
	-->
	<a class='urlPart' href='<%=request.getContextPath()%>/itservices?id=itservices'><c:out value="Home" /></a>
	
	<c:forEach var="parameter" items="${param}" >
		<c:choose>
			<c:when test="${(parameter.key == 'id') && (parameter.value == 'report_issue')}" >
				<span class='fa fa-chevron-right'></span>
				<a class='urlPart' href='<%=request.getContextPath()%>/itservices?id=report_issue'><c:out value="Report Issue" /></a>
			</c:when>
		
		</c:choose>
	</c:forEach>
</div>