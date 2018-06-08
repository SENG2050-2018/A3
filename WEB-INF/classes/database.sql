DROP TABLE alert, issue_comment, user_roles;
DROP VIEW knowledge_base;
DROP TABLE issue_reports, roles, users; 
-- =================== TABLE CREATION ===================

CREATE TABLE users (
	user_name 	VARCHAR(15) 	NOT NULL, /* Twitter uses a 15 char user_name. */
	user_pass 	VARCHAR(128) 	NOT NULL, /* DB should allow for long passwords. */
	first_name 	VARCHAR(35) 	NOT NULL, /* Found documentation that 35 char is a standard in the UK for first and last names, and emails should be 255 chars. */
	surname 	VARCHAR(35)		NOT NULL,
	email 		VARCHAR(255) 	NOT NULL,
	contact_number VARCHAR(15) 	NOT NULL,
	PRIMARY KEY (user_name)
);

INSERT INTO users VALUES
('Administrator','admin','System','Admin','ADMIN','ADMIN'),
('u080300','adminpass1','Bradley','Turner','c3259038@newcastle.edu.au','xx1'),
('u080201','adminpass2','Dean','Morton','c@newcaslte.edu.au','xx2'),
('u080033','humperdale','Hubert','Cumberdale','HC@gmail.com','xx3'),
('u080049','jfkidding','Jeremy','Fischer','JF@bigpond.com','xx4'),
('u080113','smithers','John','Smith','thisisarduos@some.email.com','xx5'),
('u080067','whipitrealgood','Ella','Good','hellagood@email.com','xx6'),
('u080062','autumn1996','John','Barnaby','johnbarnaby@midsomer.com','xx7'),
('u080002','sept1990','Lennie','Briscoe','detbriscoe@law.order.com','xx8'),
('u080096','feb2015','Margaret','Carter','peggy@mcu.com','xx9'),
('u080417','blackcoffee29','James','Japp','dcijapp@agatha.christie.com','xx10');





CREATE TABLE roles (
	role_name VARCHAR(20) NOT NULL PRIMARY KEY
);

INSERT INTO roles VALUES
('system_admin'),
('it_staff'),
('public_user');





CREATE TABLE user_roles (
	user_name VARCHAR(15) NOT NULL,
	role_name VARCHAR(20) NOT NULL,
	PRIMARY KEY (user_name, role_name),
	FOREIGN KEY (user_name) REFERENCES users(user_name), 
	FOREIGN KEY (role_name) REFERENCES roles(role_name)
);

INSERT INTO user_roles VALUES
('Administrator','system_admin'),
('u080300','system_admin'),
('u080201','system_admin'),
('u080033','public_user'),
('u080049','public_user'),
('u080113','public_user'),
('u080067','public_user'),
('u080062','public_user'),
('u080002','public_user'),
('u080096','it_staff'),
('u080417','it_staff');





CREATE TABLE issue_reports (
	issue_id 			INT 			NOT NULL 	AUTO_INCREMENT,
	reporter_user_name 	VARCHAR(15)		NOT NULL,
	title 				VARCHAR(50)		NOT NULL,
	issue_state 		VARCHAR(25)		NOT NULL 	DEFAULT 'new',
	category 			VARCHAR(25)		NOT NULL,
	description 		VARCHAR(1000) 	NOT NULL,
	reported 			TIMESTAMP 		NOT NULL 	DEFAULT	CURRENT_TIMESTAMP,
	resolved 			TIMESTAMP		NULL 		DEFAULT NULL,
	resolution_details 	VARCHAR(1000),
	
	internal_access 	BOOLEAN 		NOT NULL 	DEFAULT FALSE,
	alt_browser 		BOOLEAN 		NOT NULL 	DEFAULT FALSE,
	pc_restart 			BOOLEAN 		NOT NULL 	DEFAULT FALSE,
	
	PRIMARY KEY (issue_id),
	FOREIGN KEY (reporter_user_name) REFERENCES users(user_name),
	CONSTRAINT CHK_State 	CHECK (issue_state = 'new' OR issue_state='in-progress' OR issue_state='waiting on third party' OR issue_state='waiting on reporter' OR issue_state='completed' OR issue_state='resolved'  OR issue_state='knowledgebase'),
	CONSTRAINT CHK_Category CHECK (category = 'network' OR category = 'software' OR category = 'hardware' OR category = 'email' OR category = 'account')
);

INSERT INTO issue_reports(reporter_user_name, title, issue_state, category, description, reported, resolved, resolution_details, internal_access, alt_browser, pc_restart) VALUES
('u080033','cant connect to internet',		'new',						'network',	'help ive been trying to connect my phone to the wifi but its not connecting',						CURRENT_TIMESTAMP,						NULL,NULL,FALSE,TRUE,TRUE), 																						-- new
('u080049','word wont load corrupted file?','in-progress',				'software',	'was working on an assignment at home then came to uni and file wont load saying its corrupted?',	CURRENT_TIMESTAMP,						NULL,NULL,TRUE,FALSE,TRUE), 													-- in-progress
('u080067','blue screen',					'in-progress',				'hardware',	'everytime i restart this computer it has a blue screen',											CURRENT_TIMESTAMP,						NULL,NULL,FALSE,FALSE,TRUE), 																									-- waiting on third party
('u080067','too much spam!',				'completed',				'email',	'im constantly getting spam emails, is there anything i can do to stop it?',						TIMESTAMP('2018-05-05', '16:30:01'),	CURRENT_TIMESTAMP,'setup spam detection',TRUE,TRUE,TRUE), 									-- completed
('u080033','wrong account details',			'resolved',					'account',	'my first name is spelled wrong in my account',														TIMESTAMP('2018-05-03','16:31:01'),		CURRENT_TIMESTAMP,'reset users account details',FALSE,FALSE,FALSE), 												-- resolved
('u080049','cant open http:somesever',		'knowledgebase',			'network',	'been trying to connect to this website for ages',													TIMESTAMP('2018-04-25', '10:58:01'),	CURRENT_TIMESTAMP,'invalid server url',FALSE,TRUE,TRUE), 												-- kb
('u080062','cant install office360',		'knowledgebase',			'software',	'ive been trying to install office360 at home but its just not working',							TIMESTAMP('2018-05-01', '13:30:11'),	CURRENT_TIMESTAMP,'some process regarding how to install office360',TRUE,FALSE,TRUE),-- kb
('u080062','computer wont turn on',			'knowledgebase',			'hardware',	'tried everything to get this pc to start but it refuses to',										TIMESTAMP('2018-05-02', '16:40:01'),	CURRENT_TIMESTAMP,'loose connection to wall power socket',FALSE,FALSE,TRUE), 					-- kb
('u080002','not receiving any emails',		'knowledgebase',			'email',	'for almost a month now i havnt recieved any emails',												TIMESTAMP('2018-04-27', '08:30:01'),	CURRENT_TIMESTAMP,'emails were being redirected to spam folder',TRUE,TRUE,FALSE); 						-- kb

CREATE VIEW knowledge_base 
AS SELECT * FROM issue_reports
WHERE issue_state = 'knowledgebase';





CREATE TABLE issue_comment (
	comment_id 			INT 			NOT NULL 	AUTO_INCREMENT,
	issue_id 			INT 			NOT NULL,
	commenter_user_name VARCHAR(15) 	NOT NULL,
	user_comment 		VARCHAR(255) 	NOT NULL,
	date_posted			TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (comment_id),
	FOREIGN KEY (issue_id) 				REFERENCES issue_reports (issue_id),
	FOREIGN KEY (commenter_user_name) 	REFERENCES users(user_name)
);

INSERT INTO issue_comment (issue_id, commenter_user_name, user_comment) VALUES
(8, 'u080096', 'Hi John, have you tried unplugging the computer from the wall and plugging it back in?'),
(8, 'u080062', 'Just tried it, seems to have worked, how silly of me!');





CREATE TABLE alert (
	alert_id 			INT 			NOT NULL 	AUTO_INCREMENT,
	creater_user_name 	VARCHAR(15) 	NOT NULL,
	title 				VARCHAR(35) 	NOT NULL,
	description			VARCHAR(255) 	NOT NULL,
	start_time 			TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
	end_time 			TIMESTAMP 		NOT NULL,
	PRIMARY KEY (alert_id),
	FOREIGN KEY (creater_user_name) REFERENCES users(user_name)
);

INSERT INTO alert (creater_user_name, title, description, end_time) VALUES 
('u080300','System Maintenance','On 21/05/2018 from 13:24 AEST servers will be undergoing maintenance for approx 3 hours', TIMESTAMP('2018-05-21', '15:30:01'));



