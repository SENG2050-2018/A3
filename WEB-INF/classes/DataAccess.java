

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
}