import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class loginServlet extends HttpServlet
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
	SharklineJDBC SQLCommands;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		emailValue = request.getParameter("email");
		passwordValue = request.getParameter("password");
		
		if ( emailValue.equals("feartheshark@sharkline.net") && passwordValue.equals("adminmode") )
		{
			RequestDispatcher rd = request.getRequestDispatcher("adminmode");
			rd.forward(request, response);
		}
		SQLCommands = new SharklineJDBC();

		int loginValue = SQLCommands.login(emailValue, passwordValue);

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

			RequestDispatcher rd;

			if ( loginValue == 1 )
			{
				Account account = SQLCommands.findAccount(emailValue);
				HttpSession session = request.getSession();
				session.setAttribute("account", account);

				if(SQLCommands.findInvestorAccountByEmail(emailValue) == null)
				{
					rd = request.getRequestDispatcher("/firsttimesetupinvestor.html");
				}
				else
				{
					rd = request.getRequestDispatcher("mynetworkServlet");
				}

				rd.forward(request, response);
			}
			else if ( loginValue == 2 )
			{
				Account account = SQLCommands.findAccount(emailValue);
				HttpSession session = request.getSession();
				session.setAttribute("account", account);

				if(SQLCommands.findBusinessAccountByEmail(emailValue) == null)
				{
					rd = request.getRequestDispatcher("/firsttimesetupbusiness.html");
				}
				else
				{
					rd = request.getRequestDispatcher("mynetworkServlet");
				}

				rd.forward(request, response);
			}
	}
}
