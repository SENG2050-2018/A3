<!--
	FILENAME:		Header.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	22/05/2018
	BRIEF DESCRIPTION:	
		Masterpage header layout -> top navigation bar
-->

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class='flex-row'>
	<div class='navBar'>
		<div class='dropdown'>
			<button class='dropBtn' onclick="closeDropDown(0)"> ${user.firstName} ${user.surname} 
				<span class='fa fa-caret-down'></span>
			</button>
			<div class="dropdown-content" style='right:0'>
				<a href="${pageContext.servletContext.contextPath}/itservices?id=profile">Profile</a>
				<a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
			</div>
		</div>
		<a href="${pageContext.servletContext.contextPath}/itservices?id=kb_search" class='largeScreenOnly'>Knowledge</a>
		<c:if test="${sessionScope.isStaff == true}" >
			<div class='dropdown' >
				<button class='dropBtn largeScreenOnly' onclick="closeDropDown(1)">Admin</button>
				<div class="dropdown-content">
					<a href="${pageContext.servletContext.contextPath}/itservices?id=issue_base">View Issues</a>
					<c:if test="${sessionScope.isAdmin == true}" >
						<a href="${pageContext.servletContext.contextPath}/itservices?id=create_alert">Create New Alerts</a>
					</c:if>
				</div>
			</div>
		</c:if>
		
		<c:set var="maxNotices" value="${fn:length(notices)}" scope="session" />
		<c:if test="${fn:length(notices) > 99}" >
			<c:set var="maxNotices" value="99" scope="session" />
		</c:if>
		
		<div class='dropdown' >
			<button class='dropBtn' onclick="closeDropDown(2)"> Notices
				<c:if test="${maxNotices != 0}" >
					<span class="fa-stack fa-xs" style="color:var(--blue);">
						<i class="fa fa-circle fa-stack-2x"></i>
						<span class="fa-stack-1x fa-inverse"><c:out value="${maxNotices}" /></span>
					</span>
				</c:if>
			</button>
			<div class="dropdown-content">
				<c:forEach var ='notice' items='${notices}' begin="0" end="4" varStatus="status">
					<form action="itservices?id=issue" method="post">
						<input type='hidden' name='issue_id' value='${notice.id}' />
						<input type='hidden' name='src' value='profile' />
						<button type='submit' style="width:100%;border:0;border-top: solid 1px #2D3138;outline:0;background:white;font-size:14px;padding: 5px 5px;"><c:out value="${notice.title}" /></button>
					</form>
				</c:forEach>
			</div>
		</div>
		
		
		
	</div>
</div>