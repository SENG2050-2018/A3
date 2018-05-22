

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
	
	public List<Report> getKbReports()
	{
		String query = "SELECT * FROM issue_reports WHERE (issue_reports.issue_state = 'knowledgebase')";
		List<Report> reports = new LinkedList<>();
		
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
				Report temp = new Report();
				
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
				
				reports.add(temp);
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
				return reports;
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
}