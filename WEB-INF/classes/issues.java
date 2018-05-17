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
import javax.naming.InitialContext;

public class ReportIssueController extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/ReportIssue.jsp");
		dispatcher.forward(request, response);
		
		DataSource datasource = (DataSource)new InitialContext().lookup("java:/comp/env/SENG2050_2018");
			public static List<Issue> getAllIssues(){
					String query = "SELECT * FROM issues";
					List<Issue> issues = new LinkedList<>();
				try(Connection connection = Config.getConnection(); //step 1
					Statement statement = connection.createStatement(); //step 2
					ResultSet result = statement.executeQuery(query);){ //step 3 and 4
				while(result.next()){ //step 5
					Issue issue = new Issue();
					// you should be validating the following,
					// this is just an example
					issue.setName(result.getString(1));
					6
					issue.setYear(result.getDate(2).getYear());
					issue.setUrl(result.getString(3));
					issue.add(issue);
					}
					}
				catch(SQLException e){
					System.err.println(e.getMessage());
					System.err.println(e.getStackTrace());
					}
				return issues;
				}

				}
	}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException, SQLException
	{

		
			
	
}