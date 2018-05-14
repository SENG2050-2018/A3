DROP TABLE Roles;
DROP TABLE Person;
DROP TABLE UserRoles;
DROP TABLE IssueReports;
DROP TABLE IssueComments;

-- =================== TABLE CREATION ===================
CREATE TABLE Roles (
	roleID				int					IDENTITY(1,1)	PRIMARY KEY,
	name				varchar(255)		NOT NULL
);

CREATE TABLE Person (
	personID			int					IDENTITY(1,1)	PRIMARY KEY,
	userName			varchar(255)		NOT NULL,
	password			varchar(255)		NOT NULL,		--will hash + salt later
	CONSTRAINT CHK_Access CHECK (domainAccess = 'public' OR domainAccess = 'admin')
);

CREATE TABLE UserRoles (
	personID			int					NOT NULL 		FOREIGN KEY REFERENCES Person(personID),
	roleID				int					NOT NULL		FOREIGN KEY REFERENCES Roles(roleID)
);

CREATE TABLE IssueReports (
	issueID				int 				IDENTITY(1,1) 	PRIMARY KEY,		--auto increment the issueID key
	reporterID			int					NOT NULL 		FOREIGN KEY REFERENCES Person(personID),
	title 				varchar(255)		NOT NULL,
	issueState			varchar(255)		NOT NULL		DEFAULT 'new'
	category			varchar(255)		NOT NULL,
	description 		varchar(255)		NOT NULL,
	resolutionDetails	varchar(255),		--allows nulls since cant have resolution if issue isnt resolved
	reported			date				NOT NULL		DEFAULT	GETDATE(),	--sets the reported issue date to now
	resolved			date,				--allows nulls since cant have resolution if issue isnt resolved
	CONSTRAINT CHK_State 	CHECK (issueState = 'new' OR issueState='in-progress' OR issueState='waiting on third party' OR issueState='waiting on reporter' OR issueState='completed' OR issueState='not accepted' OR issueState='resolved' OR issueState='knowledge-base'),
	CONSTRAINT CHK_Category CHECK (category = 'network' OR category = 'software' OR category = 'hardware' OR category = 'email' OR category = 'account')
);

CREATE TABLE IssueComments (
	commentID			int					IDENTITY(1,1)	PRIMARY KEY,
	issueID				int					NOT NULL		FOREIGN KEY REFERENCES IssueReports(issueID),
	userComment			varchar(255)		NOT NULL,
);

CREATE TABLE KnowledgeBase (
	kbID				int					IDENTITY(1,1)	PRIMARY KEY,
	issueID				int					NOT NULL		FOREIGN KEY REFERENCES IssueReports(issueID)	
);

-- =================== DATA INSERTIONS ===================
INSERT INTO Roles (name)
VALUES ('system_admin'), ('public_user');

INSERT INTO Person (userName, password)
VALUES ('BTurner', 'pass123'), ('JohnSmith','Smithers'), ('MaryAnn','maryhadalittlelamb');

INSERT INTO UserRoles (personID, roleID)
SELECT personID, roleID FROM Person, Roles
WHERE Person.userName = 'BTurner' AND Roles.name = 'system_admin';

INSERT INTO UserRoles (personID, roleID)
SELECT personID, roleID FROM Person, Roles
WHERE Person.userName = 'JohnSmith' AND Roles.name = 'public_user';

INSERT INTO UserRoles (personID, roleID)
SELECT personID, roleID FROM Person, Roles
WHERE Person.userName = 'MaryAnn' AND Roles.name = 'public_user';

INSERT INTO IssueReports (reporterID, title, issueState, category, description)
VALUES ((SELECT personID FROM Person WHERE Person.userName='JohnSmith'), 'Cant log in','new','account','help. i cant seem to login for some reason even though my password is correct.');





