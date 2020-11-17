import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class adminServlet extends HttpServlet
	{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String verifyUserByEmail = request.getParameter("verifyemail");
		String removeUserByEmail = request.getParameter("removeemail");
		String accountEmail = request.getParameter("accountemail");
		String accountName = request.getParameter("accountname");
		String accountPassword = request.getParameter("accountpassword");
		String accountImagePath = request.getParameter("accountimgpath");
		String accountVerified = request.getParameter("accountverified");
		String accountType= request.getParameter("accounttype");

		SharklineJDBC SQLCommands = new SharklineJDBC();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String output =
		"<!doctype html><head><title>Admin Mode</title></head>" +
		"<body><h1>Sharkline Admin Mode</h1>";

		if ( accountEmail != null )
		{
			Account newAccount = new Account();

			newAccount.setEmail(accountEmail);
			newAccount.setName(accountName);
			newAccount.setPassword(accountPassword);
			newAccount.setImgPath(accountImagePath);
			if ( accountVerified != null && accountVerified.equals("yes") )
			{
				newAccount.setVerified(true);
			}
			else
			{
				newAccount.setVerified(false);
			}
			if ( accountType != null && accountType.equals("business") )
			{
				newAccount.setType(Type.BUSINESS);
			}
			else
			{
				newAccount.setType(Type.INVESTOR);
			}
			if ( SQLCommands.addAccount(newAccount) == true )
			{
				output += "Account added successfully";
			}
			else
			{
				output += "Account not added";
			}
		}
		else if ( verifyUserByEmail != null )
		{
			if ( SQLCommands.verifyAccount(verifyUserByEmail) == true )
			{
				output += "Account has been verified";
			}
			else
			{
				output += "Account has already been verified";
			}
		}
		else if ( removeUserByEmail != null )
		{
			if ( SQLCommands.removeAccount(removeUserByEmail) == true )
			{
				output += "Account was successfully removed";
			}
			else
			{
				output += "Account was not removed";
			}
		}

		ArrayList<Account> allAccounts = SQLCommands.findAllAccounts();

		output +=
		"<h2>Add User</h2>" +
		"<form method=\"post\" action=\"adminmode\">" +
		"Email: <input required type=\"email\" name=\"accountemail\"><br/>" +
		"Name: <input required type=\"text\" name=\"accountname\"><br/>" +
		"Password: <input required type=\"text\" name=\"accountpassword\"><br/>" +
		"Image URL Path: <input required type=\"text\" name=\"accountimgpath\"><br/>" +
		"Is Verified: <select required name=\"accountverified\">" +
		"<option value=\"yes\">Yes</option><option value=\"no\">No</option>" +
		"</select><br/>" +
		"Account Type: <select required name=\"accounttype\">" +
		"<option value=\"business\">Business</option><option value=\"investor\">Investor</option>" +
		"</select>" +
		"<input type=\"submit\" value=\"Add User\">" +
		"</form>" +
		"<h2>List of Accounts in DB</h2>" +
		"<table>" +
		"<tr><th>Name</th><th>Email</th>" +
		"<th>Password</th><th>Verified</th>" +
		"<th>Image URL Path</th><th>Type</th></tr>";
		if ( allAccounts != null )
		{
		for (int i = 0; i < allAccounts.size(); i++ )
		{
			output +=
			"<tr><td> " + allAccounts.get(i).getName() + "</td><td>" + allAccounts.get(i).getEmail() + "</td>" +
			"<td>" + allAccounts.get(i).getPassword() + "</td><td>" + allAccounts.get(i).checkVerified() + "</td>" +
			"<td>" + allAccounts.get(i).getImgPath() + "</td><td>" + allAccounts.get(i).getType() + "</td></tr>";
		}
		}
		output +=
		"</table>" +
		"<h2>Verify User</h2>" +
		"<form method=\"post\" action=\"adminmode\">" +
		"Account Email: <input required type=\"email\" name=\"verifyemail\"><br/>" +
		"<input type=\"submit\" value=\"Verify User\">" +
		"</form>" +
		"<h2>Remove User</h2>" +
		"<form method=\"post\" action=\"adminmode\">" +
		"Account Email: <input required type=\"email\" name=\"removeemail\"><br/>" +
		"<input type=\"submit\" value=\"Remove User\">" +
		"</form>" +
		"<a href=\"/index.html\">Return to login</a>"
		"</body></html>";

		out.println(output);
	}
}
