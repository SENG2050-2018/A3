<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>IT Services</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/Styles.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script>
		/** Closure function
		SETS lastClick IF setLastClick(x) call
		RETURNS lastClick IF setLastClick() call
		*/
		var setLastClick = (function (x){
			lastClick = -1;
			return function (x)
			{
				if (x === undefined){
					return lastClick; 
				}
				else {
					return lastClick = x;
				}
			};
		})();
		var set2ndLastClick = (function (x){
			secondlastClick = -2;
			return function (x)
			{
				if (x === undefined){
					return secondlastClick; 
				}
				else {
					return secondlastClick = x;
				}
			};
		})();
		
		/**
		For mobile support to assist with collapsing drop-down menus
		*/
		function closeDropDown(x){
			if ("ontouchstart" in document.documentElement)	//if statement checks for touch device
			{
				if (setLastClick() != x && setLastClick() != -1){
					document.getElementsByClassName("dropdown-content")[setLastClick()].style.display='none';
					document.getElementsByClassName("dropdown-content")[x].style.display='block';
				}
				if (setLastClick() == x){
					document.getElementsByClassName("dropdown-content")[x].style.display='none';
					if (set2ndLastClick() == setLastClick()){
						document.getElementsByClassName("dropdown-content")[setLastClick()].style.display='block';
					}
				}
				set2ndLastClick(setLastClick())
				setLastClick(x);
			}
		}
		
		
		</script>

	</head>
	<body>
		<div class='flex-container'>
			<!-- Top navigation bar -->
			<jsp:include page="Header.jsp" />
			
			<!-- Main page image with searchbar overlay -->
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
			
			<!-- Extra navigational links -->
			<div class='flex-row'>
				<ul class='grid links'>
					<li class='cell offset'>
						<a href="#" class='mimicBtn'>
							<h2>Search Knowledge</h2>
							<p>Search our knowledge base and find answers</p>
						</a>
					</li>
					<li class='cell'>
						<a href="<%=request.getContextPath()%>/itservices?id=report_issue" class='mimicBtn'>
							<h2>Report an Issue</h2>
							<p>Experiencing a problem&#63; Report it here</p>
						</a>
					</li>
				</ul>
				
				<!-- Display for maintenance alerts, etc, set by system administrators -->
				<h1 class='subtitle'>Alerts</h1>
				<ul class='alerts grid links'>
					<li class='cellSpan'>
						<h2>Example - Scheduled Maintenance</h2>
						<p>Scheduled for now. click to see more details etc etc..</p>
					</li>
				</ul>
			</div>
		</div>
		
	
	</body>
</html>