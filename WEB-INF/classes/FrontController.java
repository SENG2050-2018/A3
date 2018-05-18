  /**
	*	@FILE_NAME:			
	*	@DEVELOPERS:		
	*	@BRIEF_DESCRIPTION:	
	*/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.sql.*;
import javax.sql.*;
import java.util.*;


public class FrontController extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String id = request.getParameter("id");
		
		
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			//redirect to login
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ItServices.jsp");
			dispatcher.forward(request, response);
		}
		//if (bean user == null ) {
			//redirect to login
		//}
		//if (valid(bean user) == false){
			//redirect to login
		//}
		
		RequestDispatcher dispatcher;
		if (id == null) {	//At this point the user is verified so if no id is supplied simply just redirect to homepage
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ItServices.jsp");
			dispatcher.forward(request, response);
		}
		else {
			switch (id)
			{
				case "login":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/Login.jsp");
					dispatcher.forward(request, response);
				case "kb_search":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/KnowledgeBase.jsp");
					dispatcher.forward(request, response);
				case "report_issue":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ReportIssue.jsp");
					dispatcher.forward(request, response);
				case "itservices":
				default:
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ItServices.jsp");
					dispatcher.forward(request, response);
			}
		}
		
		/**
		Should be structured something like :
		
		if user is not valid redirect to login
		else 
			if param id == itservices || null
				itservices.jsp
			if param id == resportIssue
				reportissue.jsp
		*/
		
		
		
		
		
		
		
		
		
		
	}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	
}