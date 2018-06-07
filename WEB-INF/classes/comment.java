import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class comment extends HttpServlet
{
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
	
		Connection conn = null;
		Statement stmt = null;

		Context ctx = null;
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/SENG2050_2018");
			conn = ds.getConnection();
			stmt = conn.createStatement();
			String issue_id = request.getParameter("issue_id");
			String user_id = request.getParameter("user_id");
			String comment = request.getParameter("comment");

			   
			
			String sql = "INSERT INTO issue_comment(issue_id, commenter_user_name, user_comment) " +
			"VALUES ('"+issue_id+"', '"+user_id+"','"+comment+"')";
			
      stmt.executeUpdate(sql);
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
		}}
		response.sendRedirect(request.getContextPath() + "/itservices?id=issue");
	}
	
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
}