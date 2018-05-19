

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.io.Serializable;
import beans.Report;

public class DataAccess 
{
	public List<Report> getAllIssueReports()
	{
		String query = "SELECT * FROM V_KnowledgeBase";
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
			System.out.println("query executed");
			while (rs.next())
			{
				Report rep = new Report();
				rep.setTitle(rs.getString(1));
				rep.setDescription(rs.getString(4));
				rep.setReporter(rs.getString(2));
				reports.add(rep);
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
}