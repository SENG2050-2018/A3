

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.io.Serializable;
import beans.*;

public class DataAccess 
{
	public List<Alert> getAllAlerts()
	{
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
	
	public Report getReport(String report_id) //change to prepared statement
	{
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
	
	public User getUser(String user_name) //change to prepared statement
	{
		String query = "SELECT user_name, first_name, surname, email, contact_number FROM users WHERE (users.user_name = '" + user_name + "')";
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
				temp.setUserName(rs.getString(1));
				temp.setFirstName(rs.getString(2));
				temp.setSurname(rs.getString(3));
				temp.setEmail(rs.getString(4));
				temp.setContactNumber(rs.getString(5));
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
	

	public List<Report> getReports(String selector, String userId)
	{
		if ((selector != "knowledgebase") && (selector != "userNotices") && (selector != "all"))		{return null;}
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
					query = "SELECT * FROM issue_reports ORDER BY FIELD(issue_reports.category, 'new', 'in-progress', 'completed', 'resolved')";
					break;
				case "userNotices":
					query = "SELECT * FROM issue_reports WHERE (issue_reports.reporter_user_name = '" + userId + "' AND issue_reports.issue_state='completed')";
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
}