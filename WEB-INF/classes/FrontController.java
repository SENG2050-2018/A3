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
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		DataAccess DA = new DataAccess();
		String id = request.getParameter("id");
		
		

		//user should be set for all pages.
		session.setAttribute("user", DA.getUsers(request.getUserPrincipal().getName()));
		
		//isStaff should be set for all pages so header knows when to display administrator pages to the client.
		session.setAttribute("isStaff", String.valueOf(!request.isUserInRole("public_user")));
		
		//notices should be set for all pages so header can display relevant notices to the client.
		List<Report> reports = DA.getReports("userNotices", request.getUserPrincipal().getName());
		session.setAttribute("notices", reports);
		
		if (id == null) {	//At this point the user is verified so if no id is supplied simply just redirect to homepage
			session.setAttribute("alerts", DA.getAlerts());
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
			dispatcher.forward(request, response);
		}
		else {
			switch (id)
			{
				case "profile":
					session.setAttribute("current", DA.getReports("usersCurrent", request.getUserPrincipal().getName()));
					session.setAttribute("previous", DA.getReports("usersPrevious", request.getUserPrincipal().getName()));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/Profile.jsp");
					dispatcher.forward(request, response);
				case "kb_search":
					session.setAttribute("reports", DA.getReports("knowledgebase", null));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBase.jsp");
					dispatcher.forward(request, response);
				case "issue":
					String src = request.getParameter("src");
					String issue_id = request.getParameter("issue_id");
					
					session.setAttribute("src", src);
					session.setAttribute("report", DA.getReports(issue_id));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBaseIssue.jsp");
					dispatcher.forward(request, response);
				case "report_issue":
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ReportIssue.jsp");
					dispatcher.forward(request, response);
				case "issue_base":
					if (!request.isUserInRole("public_user")){
						session.setAttribute("reports", DA.getReports("all", null));
						dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/admin/IssueBase.jsp");
						dispatcher.forward(request, response);
					}
				case "itservices":
				default:
					session.setAttribute("alerts", DA.getAlerts());
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