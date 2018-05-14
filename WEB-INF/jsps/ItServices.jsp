<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<!-- testing etc -->
		<title>IT Services</title>
		<meta charset="UTF-8">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/Styles.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	<body onresize="">
		<div class='flex-container'>
			<div class='flex-row'>
				<ul class='links inlineList rAlign'>
					<li>Notices</li>
					<li>Knowledge</li>
					<li>My Activity</li>
					<li>users name</li>
				</ul>
			</div>
			<div class='flex-row'>
				<div class='titleWrapper'>
					<h1 class='pageTitle'><span style='color:white'>IT</span> SERVICES</h1>
					<div class='search'>
						<form id='kwSearch'>
							<input name='searchBox' type='text' placeholder='How can we help?' class='searchBox'><button type='submit' class='searchBtn'><span class='fa fa-search'></span></button>
						</form>
					</div>
				</div>
			</div>
			<div class='flex-row'>
				<ul class='grid links'>
					<li class='cell offset'>
						<a href="#" class='mimicBtn'>
							<h2>Search Knowledge</h2>
							<p>Search our knowledge base and find answers</p>
						</a>
					</li>
					<li class='cell'>
						<a href="#" class='mimicBtn'>
							<h2>Report an Issue</h2>
							<p>Experiencing a problem&#63; Report it here</p>
						</a>
					</li>
				</ul>
				<c:forEach begin="0" end="10" varStatus="loop">
					Index: ${loop.index}<br/>
				</c:forEach>
			</div>
		</div>
		
	
	</body>
</html>