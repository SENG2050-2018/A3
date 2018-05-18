<!--
	FILENAME:		Header.jsp
	DEVELOPER(S):	Bradley Turner
	
	LAST MODIFIED:	14/05/2018
	BRIEF DESCRIPTION:	
		Masterpage header layout -> top navigation bar
-->

<div class='flex-row'>
	<div class='navBar'>
		<div class='dropdown'>
			<button class='dropBtn' onclick="closeDropDown(0)"> Users name
				<span class='fa fa-caret-down'></span>
			</button>
			<div class="dropdown-content" style='right:0'>
				<a href="#">Profile</a>
				<a href="#">Logout</a>
			</div>
		</div>
		<a href="<%=request.getContextPath()%>/itservices?id=kb_search" class='largeScreenOnly'>Knowledge</a>
		<div class='dropdown' >
			<button class='dropBtn' onclick="closeDropDown(1)"> Notices</button>
			<div class="dropdown-content">
				<a href="#">dynamic creation</a>
				<a href="#">dynamic creation</a>
			</div>
		</div>
		
		
	</div>
</div>