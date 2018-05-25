import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Logout extends HttpServlet
{
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath() + "/itservices");
	}
	
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
}
