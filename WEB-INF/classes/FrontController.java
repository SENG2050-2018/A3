  /**
	*	@FILE_NAME:			FrontController.java
	*	@DEVELOPERS:		Brad Turner, Dean Morton	
	*	@BRIEF_DESCRIPTION:	Front controller, provides pathing to various jsps and relative data
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
		
		//isStaff should be set for all pages so header knows when to display staff pages to the client.
		session.setAttribute("isStaff", String.valueOf(!request.isUserInRole("public_user")));

		//isAdmin should be set for all pages so header knows when to display administrator pages to the client.
		session.setAttribute("isAdmin", String.valueOf(request.isUserInRole("system_admin")));
		
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
					// Code block to guard against reloading the webpage from an expired session.
					if (request.getParameter("issue_id") == null || request.getParameter("src") == null)
					{
						session.setAttribute("alerts", DA.getAlerts());
						dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
						dispatcher.forward(request, response);
					}
					if (request.getParameter("comment") != null){
						
						// Adds the new comment to the database.
						DA.newComment(request.getParameter("issue_id"), request.getUserPrincipal().getName(), request.getParameter("comment"));
					}
					String src = request.getParameter("src");
					String issue_id = request.getParameter("issue_id");
					
					// Code block to update the issue_state (progress the workflow) of the issue_report.
					if (request.getParameter("issue_id") != null && request.getParameter("flag") != null){
						Integer reportID = Integer.valueOf(request.getParameter("issue_id"));
						String flag = request.getParameter("flag");
						String resolutionDetails = request.getParameter("resolutionDetails");
						
						if (!flag.equals("")){
							if (flag.equals("completed") && request.getParameter("resolutionDetails") != null){
								DA.updateReport(reportID, flag, resolutionDetails);
							}
							else {
								DA.updateReport(reportID, flag);
							}
						}
					}
					
					
					session.setAttribute("src", src);
					session.setAttribute("report", DA.getReports(issue_id));
					session.setAttribute("comments", DA.retrieveComments(issue_id));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBaseIssue.jsp");
					dispatcher.forward(request, response);
				
				
				case "report_issue":
					String sent = request.getParameter("sent");
					if (sent != null) {
						if (request.getParameter("title") != null && request.getParameter("category") != null && request.getParameter("description") != null && request.getParameter("internal") != null && request.getParameter("altBrowser") != null && request.getParameter("pcRestart") != null) {
							String title = request.getParameter("title");
							String category = request.getParameter("category");
							String description = request.getParameter("description");
							boolean internal = (String) request.getParameter("internal") == "1";
							boolean altBrowser = (String) request.getParameter("altBrowser") == "1";
							boolean pcRestart = (String) request.getParameter("pcRestart") == "1";
							
							// Create new issue report.
							DA.newReport(request.getUserPrincipal().getName(), title, category, description, internal, altBrowser, pcRestart);
							
							//could set some session variable to let pop up a notification saying thank you for your feedback
							session.setAttribute("received", sent);
							dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ReportIssue.jsp");
							dispatcher.forward(request, response);
						}
						else {
							dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ReportIssue.jsp");
							dispatcher.forward(request, response);
						}
					} 
					else {
						dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ReportIssue.jsp");
						dispatcher.forward(request, response);
					}
				
				
				case "remove_alert":
					if (request.isUserInRole("system_admin")){
						if (request.getParameter("alert_id") != null)
						DA.removeAlert(request.getParameter("alert_id"));
					}
					session.setAttribute("alerts", DA.getAlerts());
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
					dispatcher.forward(request, response);
					
					
				case "issue_base":
					if (!request.isUserInRole("public_user")){
						session.setAttribute("reports", DA.getReports("all", null));
						dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/admin/IssueBase.jsp");
						dispatcher.forward(request, response);
					}
					// For non qualified users -- show knowledge base.
					session.setAttribute("reports", DA.getReports("knowledgebase", null));
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/KnowledgeBase.jsp");
					dispatcher.forward(request, response);
					
					
				case "create_alert":
					if (!request.isUserInRole("public_user")){
						
						// If null value detected, redirect to home page.
						sent = request.getParameter("sent");
						if (sent != null) {
							if (request.getParameter("startDate") == null || request.getParameter("startTime") == null || request.getParameter("endDate") == null || request.getParameter("endTime") == null || request.getParameter("title") == null || request.getParameter("description") == null) {
								dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/admin/CreateAlert.jsp");
								dispatcher.forward(request, response);
							}
							else {
								if (request.getParameter("startDate").equals("") || request.getParameter("startTime").equals("") || request.getParameter("endDate").equals("") || request.getParameter("endTime").equals("") || request.getParameter("title").equals("") || request.getParameter("description").equals("")) {}
								else {
									String start_datetime = request.getParameter("startDate") + " " + request.getParameter("startTime") + ":00";
									String end_datetime = request.getParameter("endDate") + " " + request.getParameter("endTime") + ":00";
								
									DA.newAlert(request.getUserPrincipal().getName(),request.getParameter("title"),request.getParameter("description"), start_datetime, end_datetime);
									session.setAttribute("received", sent);
								}
							}
						}
						dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/admin/CreateAlert.jsp");
						dispatcher.forward(request, response);
					}
					
					
				case "itservices":
				default:
					session.setAttribute("alerts", DA.getAlerts());
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/public/ItServices.jsp");
					dispatcher.forward(request, response);
			}
		}
	}
	
	
}