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
		SharklineJDBC SQLCommands = new SharklineJDBC();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String output =
		"<!doctype html><head><title>Admin Mode</title></head>" +
		"<body><h1>Sharkline Admin Mode</h1>" +
		"<h2>Add User</h2>" +
		"<form method=\"post\" action=\"\">" +
		"Email: <input required type=\"email\" name=\"email\"><br/>" +
		"Name: <input required type=\"text\" name=\"name\"><br/>" +
		"Password: <input required type=\"text\" name=\"password\"><br/>" +
		"Image URL Path: <input required type=\"text\" name=\"imgpath\"><br/>" +
		"Is Verified: <select required name=\"verified\">" +
		"<option value=\"yes\">Yes</option><option value=\"no\">No</option>" +
		"</select><br/>" +
		"Account Type: <select required name=\"accounttype\">" +
		"<option value=\"business\">Business</option><option value=\"investor\">Investor</option>" +
		"</select>" +
		"<input type=\"submit\" value=\"Add User\">" +
		"</form>" +
		"<h2>List of Accounts in DB</h2>" +
		"<h2>Verify User</h2>" +
		"<form method=\"post\" action=\"\">" +
		"Account Email: <input required type=\"email\" name=\"verifyemail\"><br/>" +
		"<input type=\"submit\" value=\"Verify User\">" +
		"</form>" +
		"<h2>Remove User</h2>" +
		"<form method=\"post\" action=\"\">" +
		"Account Email: <input required type=\"email\" name=\"removeemail\"><br/>" +
		"<input type=\"submit\" value=\"Remove User\">" +
		"</form>" +
		"</body></html>";
		
		out.println(output);
	}
}