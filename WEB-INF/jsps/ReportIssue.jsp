<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<title>IT Services</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/css/Styles.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		

	</head>
	<body>
		<div class='flex-container'>
			<!-- Top navigation bar -->
			<jsp:include page="Header.jsp" />
			
			<div class='flex-row spacing'></div>
			<jsp:include page="urlConstructor.jsp" />
			
			<div class='flex-row'>
				<form class='centered form' action='' method='post'> <!-- Method should be post so data sent to the server is concealed -->
					<section class='title'> <!-- Title -->
						<h1>Report Issue</h1>
						<p class='small'>Experiencing a problem? Report it here</p>
					</section>
					
					<section> <!-- urgent it assistance -->
						<p>
							For <span class='red'>Urgent IT Asssistance</span> please contact +61 2 XXX XXXXX
						</p>
						<p>
							Phone Support Hours: 8am - 6pm (AEST) Monday to Friday, University Working Days
						</p>
					</section>
					
					<section> <!-- required data -->
						<input name='userID' type='hidden' text='usersId'>
						
						<label for='title'><span class='red'>*</span>Briefly describe your issue</label>
						<input name='title' type='text' required>
						
						<label for='description'><span class='red'>*</span>Please describe your issue in detail</label>
						<textarea name='description' required></textarea>
					</section>
					
					<section> <!-- Non required additional data -->
						<h2>Additonal Information</h2>
						<h3>My Location</h3>
						<p>
							<label for='building'>Building: </label>
							<select name='building'>
								<option value='default' selected disabled>--</option>
								<option value='CT'>CT Building</option>
								<option value='A'>Auchmuty Library</option>
								<option value='GP'>GP Building</option>
								<option value='HC'>Huxley Library</option>
								<option value='SSH'>Shortland Student Hub</option>
								<option value='HSH'>Hunter Student Hub (Student Services Building)</option>
								<option value='other'>Other</option>
							</select>
						</p>
						<p>
							<label for='room'>Room: </label>
							<input name='room' type='text'>
						</p>
					</section>
					
					<button type='reset'>Clear</button>
					<button type='submit'>Submit</button>
					
					
					
					
					
				</form>
			</div>
		
	</body>
</html>