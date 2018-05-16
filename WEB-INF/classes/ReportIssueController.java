  /**
	*	@FILE_NAME:			
	*	@DEVELOPERS:		
	*	@BRIEF_DESCRIPTION:	
	*/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ReportIssueController extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ReportIssue.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		/**
			
		*/
	}
	
}