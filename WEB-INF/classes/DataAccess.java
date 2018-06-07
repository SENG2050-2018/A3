

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.io.Serializable;
import beans.*;

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
				temp.setTitle(rs.getString(3));
				temp.setDescription(rs.getString(4));
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
	
	public void removeAlert(/*alert_id*/) {}
	
	
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
					temp.setFirstName(rs.getString(2));
					temp.setSurname(rs.getString(3));
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
				temp.setFirstName(rs.getString(3));
				temp.setSurname(rs.getString(4));
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
					query = "SELECT * FROM issue_reports ORDER BY FIELD(issue_reports.issue_state, 'new', 'in-progress', 'completed', 'resolved')";
					break;
				case "userNotices":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "' AND issue_reports.issue_state='completed')";
					break;
				case "usersCurrent":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "') ";
					break;
				case "usersPrevious":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "' AND issue_reports.issue_state='resolved')";
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
					temp.setTitle(rs.getString(3));
					temp.setState(rs.getString(4));
					temp.setCategory(rs.getString(5));
					temp.setDescription(rs.getString(6));
					temp.setReported(rs.getString(7));
					temp.setResolved(rs.getString(8));
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
	public Report getReports(String report_id){//change to prepared statement
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
				temp.setTitle(rs.getString(3));
				temp.setCategory(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setReported(rs.getString(7));
				temp.setResolved(rs.getString(8));
				temp.setResolution(rs.getString(9));
				temp.setInternalAccess(rs.getString(10));
				temp.setAltBrowser(rs.getString(11));
				temp.setPcRestart(rs.getString(12));
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
	
	
	// TESTING search bar
	/**
	public Report getSearch(String report_title){//change to prepared statement
		
		String query = "SELECT * FROM issue_reports WHERE CONTAINS(issue_reports.title, "+ report_title +")";
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
				temp.setTitle(rs.getString(3));
				temp.setCategory(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setReported(rs.getString(7));
				temp.setResolved(rs.getString(8));
				temp.setResolution(rs.getString(9));
				temp.setInternalAccess(rs.getString(10));
				temp.setAltBrowser(rs.getString(11));
				temp.setPcRestart(rs.getString(12));
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
	*/
	
	
	public void newReport(String reporter, String title, String category, String description, boolean ia, boolean ab, boolean pr)	{
		String insert = "INSERT INTO issue_reports (reporter_user_name, title, category, description, internal_access, alt_browser, pc_restart) "; //, internal_access, alt_browser, pc_restart
		insert += "VALUES (?,?,?,?,?,?,?)"; //,'?','?','?'
		
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
	
	public void updateReport(/*report_id, newIssueState*/) {}
	
	
	
}