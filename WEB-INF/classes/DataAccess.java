

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.io.Serializable;
import beans.*;
import java.text.SimpleDateFormat;

public class DataAccess 
{
	/** + getAlerts()
	*	Preconditions: none.
	*	Postconditions: Returns a list of beans.Alert objects containing all the active alerts in the database.
	*/
	public List<Alert> getAlerts(){
		String query = "SELECT * FROM alert WHERE ((start_time <= CURRENT_TIMESTAMP) AND (end_time >= CURRENT_TIMESTAMP))";
		List<Alert> alerts = new LinkedList<>();
		
		Context ctx = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				Alert temp = new Alert();
				temp.setId(rs.getString(1));
				temp.setTitle(captialise(rs.getString(3)));
				temp.setDescription(captialise(rs.getString(4)));
				alerts.add(temp);
			}
			
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
				ctx.close();
				
				//finally return the list
				return alerts;
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/** + getAlerts()
	*	Preconditions: alert_id is initialised and a valid id.
	*	Postconditions: Updates the alert's end time to current timestamp, effectively removing it from the website.
	*/
	public void removeAlert(String alert_id) {
		if (alert_id == null){
			System.out.println("null values in removeAlert");
		}
		
		String update = "UPDATE alert SET end_time = DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 1 MINUTE) WHERE alert_id = ?"; 
		
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(update);
			stmt.setString(1, alert_id);
			stmt.execute();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
				ctx.close();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/** + newComment(creater_user_name, title, description, start_time, end_time)
	*	Preconditions: All parameters need to be initialised and non null.
	*	Postconditions:	Accesses the MySQL Database via a prepared statement that creates a new alert row
	*					containing all sanitised parameters.
	*/
	public void newAlert(String creater_username, String title, String description, String start_time, String end_time) {
		if (creater_username == null || title == null  || description == null || start_time == null ||  end_time == null)	{
			System.out.println("null fields in newAlert");
			return;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date startDate = sdf.parse(start_time);
			java.util.Date endDate = sdf.parse(end_time);
			
			String insert = "INSERT INTO alert (creater_user_name, title, description, start_time, end_time) "; 
			insert += "VALUES (?,?,?,?,?)";
			
			Context ctx = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try
			{
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
				conn = ds.getConnection();
				stmt = conn.prepareStatement(insert);
				stmt.setString(1, creater_username);
				stmt.setString(2, title);
				stmt.setString(3, description);
				stmt.setTimestamp(4, getTimestamp(startDate));
				stmt.setTimestamp(5, getTimestamp(endDate));
				
				stmt.execute();
				
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					stmt.close();
					conn.close();
					ctx.close();
				}
				catch (NamingException e)
				{
					e.printStackTrace();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	





	/** + getUsers(user_name)
	*	Preconditions: user_name cannot be null and must be a valid user_name.
	*	Postconditions: Returns a specific beans.User object from the database specified by user_name.
	*/
	public User getUsers(String user_name){ //change to prepared statement
		String query = "SELECT user_name, first_name, surname, email, contact_number FROM users";
		User temp = new User();
		
		Context ctx = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				if (rs.getString(1).equals(user_name)) 
				{
					temp.setUserName(rs.getString(1));
					temp.setFirstName(captialise(rs.getString(2)));
					temp.setSurname(captialise(rs.getString(3)));
					temp.setEmail(rs.getString(4));
					temp.setContactNumber(rs.getString(5));
					break;
				}
			}
			
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
				ctx.close();
				
				//finally return the list
				return temp;
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/** + getUsers()
	*	Preconditions: none
	*	Postconditions: Returns a list of beans.User objects containing all the public users in the database.
	*/
	public List<User> getUsers() { 
		String query = "SELECT * FROM users";
		List<User> users = new LinkedList<>();
		
		Context ctx = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				User temp = new User();
				temp.setUserName(rs.getString(1));
				temp.setFirstName(captialise(rs.getString(3)));
				temp.setSurname(captialise(rs.getString(4)));
				temp.setEmail(rs.getString(5));
				temp.setContactNumber(rs.getString(6));
				
				
				users.add(temp);
				

			}
			
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
				ctx.close();
				
				//finally return the list
				return users;
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
	}}
	
	


	
	/** + getReports(selector, userId)
	*	Preconditions: selector cannot be null, and has to be = to either "all", "knowledgebase" or "userNotices".
	*	Postconditions: Returns a list of beans.Report objects, choosing from 3 paths: 
	*		-	selector = "all"
	*			returns all issue reports in the database
	*		-	selector = "knowledgebase"
	*			returns all issues in the database with issue state = "knowledgebase"
	*		-	selector = "userNotices"
	*			returns all issues in the database with issue state = "completed" and 
	*			reporter_user_name = userId
	*/
	public List<Report> getReports(String selector, String userId){
		if ((selector != "knowledgebase") && (selector != "userNotices") && (selector != "all") && (selector != "usersCurrent") && (selector != "usersPrevious"))		{return null;}
		else
		{
			List<Report> reports = new LinkedList<>();
			String query = "";
			
			//Switch statement to select which query to execute
			switch (selector)
			{
				case "knowledgebase":	
					query = "SELECT * FROM issue_reports WHERE (issue_reports.issue_state = 'knowledgebase')";
					break;
				case "all":
					query = "SELECT * FROM issue_reports ORDER BY FIELD(issue_reports.issue_state, 'new', 'in-progress', 'completed', 'resolved', 'knowledgebase')";
					break;
				case "userNotices":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "' AND issue_reports.issue_state='completed')";
					break;
				case "usersCurrent":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "') ORDER BY FIELD(issue_reports.issue_state, 'new', 'in-progress', 'completed', 'resolved', 'knowledgebase')";
					break;
				case "usersPrevious":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "' AND issue_reports.issue_state='resolved') ORDER BY FIELD(issue_reports.issue_state, 'new', 'in-progress', 'completed', 'resolved', 'knowledgebase')";
					break;
			}
			
			//desclare variables for the database access
			Context ctx = null;
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try
			{
				//set variables for database access
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
				conn = ds.getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);

				//loop over result set and add results to Report objects (JavaBean) and append to list for output
				while (rs.next())
				{
					Report temp = new Report();
					
					temp.setId(rs.getString(1));
					temp.setReporter(rs.getString(2));
					temp.setTitle(captialise(rs.getString(3)));
					temp.setState(captialise(rs.getString(4)));
					temp.setCategory(captialise(rs.getString(5)));
					temp.setDescription(captialise(rs.getString(6)));
					
					Timestamp dbSqlTimestamp = rs.getTimestamp(7);
					if (dbSqlTimestamp != null){
						String formattedDate = new SimpleDateFormat("dd/MM/yyyy  h:mm a").format(dbSqlTimestamp);
						temp.setReported(formattedDate);
					}
					
					dbSqlTimestamp = rs.getTimestamp(8);
					if (dbSqlTimestamp != null){
						String formattedDate = new SimpleDateFormat("dd/MM/yyyy  h:mm a").format(dbSqlTimestamp);
						temp.setResolved(formattedDate);
					}
					
					
					
					temp.setResolution(rs.getString(9));
					temp.setInternalAccess(rs.getString(10));
					temp.setAltBrowser(rs.getString(11));
					temp.setPcRestart(rs.getString(12));
					
					reports.add(temp);
				}
			}
			catch (NamingException e)		{e.printStackTrace();}
			catch (SQLException e)			{e.printStackTrace();}
			finally
			{
				//try block in case variables werent created successfully
				try {
					rs.close();
					stmt.close();
					conn.close();
					ctx.close();
					
					//finally return the output list
					return reports;
				}
				catch (NamingException e)	{e.printStackTrace();}
				catch (SQLException e)		{e.printStackTrace();}
				catch (Exception e)			{e.printStackTrace();}
				
				//if all else fails return null
				return null;
			}
		}
	}
	
	/** + getReports(report_id)
	*	Preconditions: report_id cannot be null and has to be a valid report_id.
	*	Postconditions:	Returns the beans.Report object that report_id matches to.
	*/
	public Report getReports(String report_id){
		String query = "SELECT * FROM issue_reports WHERE (issue_reports.issue_id = '" + report_id + "')";
		Report temp = new Report();
		
		Context ctx = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				temp.setId(rs.getString(1));
				temp.setReporter(rs.getString(2));
				temp.setTitle(captialise(rs.getString(3)));
				temp.setState(captialise(rs.getString(4)));
				temp.setCategory(captialise(rs.getString(5)));
				temp.setDescription(captialise(rs.getString(6)));
				
				Timestamp dbSqlTimestamp = rs.getTimestamp(7);
				if (dbSqlTimestamp != null){
					String formattedDate = new SimpleDateFormat("dd/MM/yyyy  h:mm a").format(dbSqlTimestamp);
					temp.setReported(formattedDate);
				}
				
				dbSqlTimestamp = rs.getTimestamp(8);
				if (dbSqlTimestamp != null){
					String formattedDate = new SimpleDateFormat("dd/MM/yyyy  h:mm a").format(dbSqlTimestamp);
					temp.setResolved(formattedDate);
				}
				
				temp.setResolution(captialise(rs.getString(9)));
				
				if (rs.getString(10).equals("1")){
					temp.setInternalAccess("Yes");
				}
				else {
					temp.setInternalAccess("No");
				}
				
				if (rs.getString(11).equals("1")){
					temp.setAltBrowser("Yes");
				}
				else {
					temp.setAltBrowser("No");
				}
				
				if (rs.getString(12).equals("1")){
					temp.setPcRestart("Yes");
				}
				else {
					temp.setPcRestart("No");
				}
			}
			
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				stmt.close();
				conn.close();
				ctx.close();
				
				//finally return the list
				return temp;
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/** + newReport(reporter, title, category, description, ia, ab, pr)
	*	Preconditions: All parameters need to be initialised and non null.
	*	Postconditions:	Accesses the MySQL Database via a prepared statement that creates a new issue_report row
	*					containing all sanitised parameters.
	*/
	public void newReport(String reporter, String title, String category, String description, boolean ia, boolean ab, boolean pr)	{
		if (reporter == null || title == null || category == null || description == null){
			System.out.println("null values in newReport");
			return;
		}
		
		String insert = "INSERT INTO issue_reports (reporter_user_name, title, category, description, internal_access, alt_browser, pc_restart) "; 
		insert += "VALUES (?,?,?,?,?,?,?)";
		
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, reporter);
			stmt.setString(2, title);
			stmt.setString(3, category);
			stmt.setString(4, description);
			stmt.setBoolean(5, ia);
			stmt.setBoolean(6, ab);
			stmt.setBoolean(7, pr);
			
			stmt.execute();
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
				ctx.close();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/** + updateReport(report_id, newIssueState)
	*	Preconditions: 	report_id and newIssueState cannot be null. 
	*					newIssueState has to exist in {'new','in-progress','completed','resolved','knowledgebase'}.
	*	Postconditions:	Accesses the MySQL Database via a prepared statement and updates the row specified by report_id,
	*					to change its issue_state to newIssueState.
	*/
	public void updateReport(int report_id, String newIssueState) {
		if (newIssueState == null){
			System.out.println("null values in updateReport(report_id, newIssueState)");
			return;
		}
		
		String update = "UPDATE issue_reports SET issue_state = ? WHERE issue_id = ?"; 
		
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(update);
			stmt.setString(1, newIssueState);
			stmt.setInt(2, report_id);
			stmt.execute();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
				ctx.close();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/** + updateReport(report_id, newIssueState)
	*	Preconditions: 	report_id and newIssueState cannot be null. 
	*					newIssueState has to exist in {'new','in-progress','completed','resolved','knowledgebase'}.
	*	Postconditions:	Accesses the MySQL Database via a prepared statement and updates the row specified by report_id,
	*					to change its issue_state to newIssueState.
	*					OVERLOAD: sets column resolution_details in database.
	*/
	public void updateReport(int report_id, String newIssueState, String resolutionDetails) {
		if (newIssueState == null || resolutionDetails == null){
			System.out.println("null values in updateReport(report_id, newIssueState, resolutionDetails)");
			return;
		}
		
		String update = "UPDATE issue_reports SET issue_state = ?, resolution_details = ?, resolved = CURRENT_TIMESTAMP() WHERE issue_id = ?"; 
		
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(update);
			stmt.setString(1, newIssueState);
			stmt.setString(2, resolutionDetails);
			stmt.setInt(3, report_id);
			stmt.execute();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
				ctx.close();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	
	


	/** + retrieveComments(report_id)
	*	Preconditions: report_id cannot be null and has to be a valid report_id.
	*	Postconditions:	Returns a list of beans.Comment objects with issue_comment.issue_id matching report_id.
	*/
	public List<Comment> retrieveComments(String report_id){
		String query = "SELECT * FROM issue_comment WHERE issue_comment.issue_id = ?";
		List<Comment> comments = new LinkedList<>();
		
		// Declare variables for the database access.
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			// Set variables for database access.
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, report_id);
			rs = stmt.executeQuery();

			// Loop over result set and add results to Report objects (JavaBean) and append to list for output.
			while (rs.next())
			{
				Comment temp = new Comment();
				
				temp.setIssueId(rs.getString(2));
				temp.setCommenterUserName(rs.getString(3));
				temp.setUserComment(captialise(rs.getString(4)));
				comments.add(temp);
			}
		}
		catch (NamingException e)		{e.printStackTrace();}
		catch (SQLException e)			{e.printStackTrace();}
		finally
		{
			// Try block in case variables werent created successfully.
			try {
				rs.close();
				stmt.close();
				conn.close();
				ctx.close();
				
				// Finally return the output list.
				return comments;
			}
			catch (NamingException e)	{e.printStackTrace();}
			catch (SQLException e)		{e.printStackTrace();}
			catch (Exception e)			{e.printStackTrace();}
			
			// If all else fails return null.
			return null;
		}
	}
	
	/** + newComment(report_id, user_id, comment)
	*	Preconditions: All parameters need to be initialised and non null.
	*	Postconditions:	Accesses the MySQL Database via a prepared statement that creates a new issue_comment row
	*					containing all sanitised parameters.
	*/
	public void newComment(String report_id, String user_id, String comment){
		if (report_id == null || user_id == null || comment == null){
			System.out.println("null values in newComment");
			return;
		}
		
		String insert = "INSERT INTO issue_comment (issue_id, commenter_user_name, user_comment) "; 
		insert += "VALUES (?,?,?)";
		
		Context ctx = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try
		{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, report_id);
			stmt.setString(2, user_id);
			stmt.setString(3, comment);
			
			
			stmt.execute();
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				conn.close();
				ctx.close();
			}
			catch (NamingException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	/* Sourced from http://www.java67.com/2016/03/how-to-convert-javautildate-to-javasqlTimestamp-JDBC.html*/
	public Timestamp getTimestamp(java.util.Date date){ return date == null ? null : new java.sql.Timestamp(date.getTime()); }
	
	private String captialise(String str){
		String slice = str.substring(0,1);
		return slice.toUpperCase() + str.substring(1);
	}
}