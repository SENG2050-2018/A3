import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.io.Serializable;

public class DataAccess extends HttpServlet
{
	

	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
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
			rs = stmt.executeQuery("SELECT userName FROM Person");
			while (rs.next()) {
				System.out.println(rs.getString(1)); //gets the first column's rows.
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
}