import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class login extends HttpServlet
{
	String title;
	String h1;
	String message;
	String output;
	String emailKey = "email";
	String emailValue;
	String passwordKey = "password";
	String passwordValue;
	String jdbcUsername = "root";
	String jdbcPassword = "grenadine@2020";
	String jdbcURL = "jdbc:mysql://localhost/sharklinedb";
	Connection jdbcConnection;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		emailValue = request.getParameter("email");
		passwordValue = request.getParameter("password");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			jdbcConnection.setAutoCommit(false);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int loginValue = login(emailValue, passwordValue);
		
		switch (loginValue)
		{
			case -1:
			title = "Account Not Found";
			h1 = "No Account Was Found";
			message = "The email and/or password you entered was not found. Please try again.";
			break;
			case 0:
			title = "You Are Not Verified";
			h1 = "Pending Approval";
			message = "Please allow us 3-5 business days to approve you.";
			break;
		}
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			if ( loginValue == -1 || loginValue == 0)
			{
		output =
			"<!doctype html>\n" +
			"<head>\n" +
			"<title>" + title + "</title>\n" +
			"</head>\n" +
			"<body>\n" +
			"<h1>" + h1 + "</h1>" +
			"<p>" + message + "</p>\n" +
			"</body>\n" +
			"</html>";
			
			out.print(output);
			}
			
			if ( loginValue == 1 )
			{
				Investor account = findInvestorAccountByEmail(emailValue);
				HttpSession session = request.getSession();
				session.setAttribute("investor_account", account);
				RequestDispatcher rd = request.getRequestDispatcher("mynetwork");
				rd.forward(request, response);
			}
	}
	
	/**
  * login method searches through database and matches email and password
  * to entry in accounts table, returning integer based on result
  *
  * @param email the email we use to search the table for the tuple
  * @param password the password for the account, must be matched
  *
  * @return -1 for account not found (password incorrect, email not found, etc)
  *         0 for account found but not verified, 1 for investor account found,
  *         2 for business account found
  */
  public int login(String email, String password)
  {
    try
    {
      PreparedStatement st =
      jdbcConnection.prepareStatement("SELECT * FROM accounts" +
      " WHERE account_email = ? AND account_password = ?");
      st.setString(1, email);
      st.setString(2, password);

      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return -1;

      if(result.getInt("verification") == 0)
        return 0;

      if(result.getString("type").equals("Investor"))
        return 1;
      else
        return 2;
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return -1;
    }
  }
  
  public Investor findInvestorAccountByEmail(String email)
  {
    try
    {
      Investor returnAccount;
      PreparedStatement st =
      jdbcConnection.prepareStatement("SELECT * FROM investor_accounts WHERE investor_email = ?");

      st.setString(1, email);
      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      returnAccount = new Investor();

      returnAccount.setInvestorEmail(result.getString("investor_email"));
      returnAccount.setInvestorName(result.getString("investor_name"));
      returnAccount.setInvestorDescription(result.getString("investor_description"));
      returnAccount.setInvestorAbstract(result.getString("investor_abstract"));
      returnAccount.setInvestmentRangeInit(result.getInt("investment_range_init"));
      returnAccount.setInvestmentRangeEnd(result.getInt("investment_range_end"));
      returnAccount.setWebsite(result.getString("website"));
      returnAccount.setCeoName(result.getString("name_CEO"));

      st.close();
      return returnAccount;
    }
    catch(SQLException e1)
    {
      while (e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return null;
    }
  }
}