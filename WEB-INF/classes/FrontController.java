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
import beans.*;

public class FrontController extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		DataAccess DA = new DataAccess();
		String id = request.getParameter("id");
		
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		//user should be set for all pages
		session.setAttribute("user", DA.getUser(request.getUserPrincipal().getName()));
		String isStaff = String.valueOf(!request.isUserInRole("public_user"));
		session.setAttribute("isStaff", isStaff);
		System.out.println(session.getAttribute("isStaff"));
		
		if (id == null) {	//At this point the user is verified so if no id is supplied simply just redirect to homepage
			session.setAttribute("alerts", DA.getAllAlerts());
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
			dispatcher.forward(request, response);
		}
		else {
			switch (id)
			{
				case "profile":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/Profile.jsp");
					dispatcher.forward(request, response);
				case "kb_search":
					session.setAttribute("reports", DA.getKbReports());
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBase.jsp");
					dispatcher.forward(request, response);
				case "kb_issue":
					String kb_id = request.getParameter("kb_id");
					session.setAttribute("report", DA.getReport(kb_id));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBaseIssue.jsp");
					dispatcher.forward(request, response);
				case "report_issue":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ReportIssue.jsp");
					dispatcher.forward(request, response);
				case "itservices":
				default:
					session.setAttribute("alerts", DA.getAllAlerts());
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
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
	
	
}