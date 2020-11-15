import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class updateProfileServlet extends HttpServlet
{
	String output;
	String name;
	String jdbcUsername = "root";
	String jdbcPassword = "root";
	String jdbcURL = "jdbc:mysql://localhost/sharklinedb";
	Connection jdbcConnection;
	SharklineJDBC SQLCommands;
	Account account;

	@Override
	public void doPost(HttpServlet request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		SQLCommands = new SharklineJDBC();
		account = (Account)session.getAttribute("account");
		name = account.getName();

		String description = request.getParameter("description");
		String userAbstract = request.getParameter("abstract");
		int investmentInit = request.getParameter("investmentInit");
		int investmentEnd = request.getParameter("investmentEnd");
		String website = request.getParameter("website");
		String ceoName = request.getParameter("ceoName");



		if(account.getType() == Type.INVESTOR)
		{

		}

	}

	private String investorHTML()
	{

		

	}
}