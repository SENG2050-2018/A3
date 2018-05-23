<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
	FILENAME:		Header.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	22/05/2018
	BRIEF DESCRIPTION:	
		Masterpage header layout -> top navigation bar
-->

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
					<a href="#">View issues</a>
					<a href="#">Create / edit users</a>
					<a href="#">Create new alerts</a>
				</div>
			</div>
		</c:if>
		<div class='dropdown' >
			<button class='dropBtn' onclick="closeDropDown(2)"> Notices</button>
			<div class="dropdown-content">
				<a href="#">dynamic creation</a>
				<a href="#">dynamic creation</a>
			</div>
		</div>
		
		
		
	</div>
</div>