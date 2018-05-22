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
		<div class='dropdown' >
			<button class='dropBtn' onclick="closeDropDown(1)"> Notices</button>
			<div class="dropdown-content">
				<a href="#">dynamic creation</a>
				<a href="#">dynamic creation</a>
			</div>
		</div>
		
		
	</div>
</div>