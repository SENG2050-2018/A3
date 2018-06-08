Student submission for SENG2050 Semester 1, 2018.
Submission by:
	-	Bradley Turner 	(c3259038)
	-	Dean	Morton	(c3252227)
	
How to deploy:
	1.	If you are NOT running a MySQL Server locally follow these steps: 
		a.	Download PuTTy from https://www.putty.org/
		b.	Configure PuTTy to connect to host jumpgate.newcastle.edu.au via port 22,
			using SSH as the connection type and forward port 30001 to teachdb:3306
		c.	After establishing a connection you will be prompted for UON login credentials.
	2.	Extract zipped folder contents in tomcat's directory 'webapps'
	3.	Launch tomcat
	4. 	Open browser and navigate to http://localhost:8080/c3259038_c3252227_FinalProject/itservices
	
Submission directory structure:
	+ css
		+ login.css
		+ multiPage.css
		+ Styles.css
	+ images
		+ Technology.png
	+ js
		+ dropdown-mobile-support.js
		+ multiPage.js
	+ META-INF
		+ context.xml
	+ WEB-INF
		+ classes
			+ beans
				+ Alert.class
				+ Comment.class
				+ Report.class
				+ User.class
			+ Alert.java
			+ Comment.java
			+ DataAccess.class
			+ DataAccess.java
			+ FrontController.class
			+ FrontController.java
			+ Logout.class
			+ Logout.java
			+ Report.java
			+ User.java
		+ jsps
			+ admin
				+ CreateAlert.jsp
				+ IssueBase.jsp
			+ public
				+ Header.jsp
				+ ItServices.jsp
				+ KnowledgeBase.jsp
				+ KnowledgeBaseIssue.jsp
				+ Profile.jsp
				+ ReportIssue.jsp
				+ urlConstructor.jsp
			+ Login.jsp
			+ LoginError.jsp
		+ lib
			+ mysql-connector-java-5.1.36-bin.jar
			+ taglibs-standard-impl-1.2.5.jar
			+ taglibs-standard-spec-1.2.5.jar
		+ sql
			+ database.sql
		+ web.xml
	+ README.txt
	
	
	
	
	
	
	
A Note to the marker:
	Look im going to be honest with you, to run our web application the URL does NOT conform
	to the assignment specifications. I realised this too late, and to change our code to run
	this way would make us run overtime. - Brad Turner
	OUR URL TO RUN THIS PROJECT IS:
	http://localhost:8080/c3259038_c3252227_FinalProject/itservices
	
	USEFUL LOGIN CREDENTIALS:
	user_name			|			password			|			role
	Administrator		|			admin				|		system_admin
	U080033				|			humperdale			|		public_user
	U080096				|			feb2015				|		it_staff
	
	DIFFERENCE IN ROLES:
	- public users have access to all public jsps
	- it staff have access to all public jsps AND can view and modify the issue base
	- system admins have full access to the website and can create alerts.