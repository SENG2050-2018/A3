import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class createReport extends HttpServlet
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
			String id = request.getParameter("userID");
			String title = request.getParameter("title");
			String category = request.getParameter("category");
			String description = request.getParameter("description");
			String internal = request.getParameter("internal");
			String altBrowser = request.getParameter("altBrowser");
			String pcRestart = request.getParameter("pcRestart");
			   

			
			
			String sql = "INSERT INTO issue_reports(reporter_user_name, title, category, description, issue_state, resolved, internal_access, alt_browser, pc_restart) " +
			"VALUES ('"+id+"', '"+title+"','"+category+"' ,'"+description+"', 'new', '2018-05-21 13:30:39', '"+internal+"', '"+altBrowser+"', '"+pcRestart+"')";
			
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
		response.sendRedirect(request.getContextPath() + "/itservices");
	}
	
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
}