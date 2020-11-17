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
	String email;
	String image;
	String logo;
	Industry industry;
	Size size;
	int year;
	Account account;
	String description;
	String myAbstract;
	int investmentInit;
	int investmentEnd;
	int investmentAsk;
	int equity;
	String website;
	String ceoName;
	Investor investor;
	Business business;

	String jdbcUsername = "root";
	String jdbcPassword = "root";
	String jdbcURL = "jdbc:mysql://localhost/sharklinedb";
	Connection jdbcConnection;
	SharklineJDBC SQLCommands;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		SQLCommands = new SharklineJDBC();
		account = (Account)session.getAttribute("account");
		name = account.getName();

		



		if(account.getType() == Type.INVESTOR)
		{
			
			investor = SQLCommands.findInvestorAccountByName(name);
			description = request.getParameter("description");
			myAbstract = request.getParameter("abstract");
			investmentInit = Integer.parseInt(request.getParameter("investmentInit"));
			investmentEnd = Integer.parseInt(request.getParameter("investmentEnd"));
			website = request.getParameter("website");
			ceoName = request.getParameter("ceoName");

			investor.setInvestorDescription(description);
			investor.setInvestorAbstract(myAbstract);
			investor.setInvestmentRangeInit(investmentInit);
			investor.setInvestmentRangeEnd(investmentEnd);
			investor.setWebsite(website);
			investor.setCeoName(ceoName);

			

			SQLCommands.updateInvestorAccount(investor);
		}else{

			
			business = SQLCommands.findBusinessAccountByName(name);
			description = request.getParameter("description");
			myAbstract = request.getParameter("abstract");
			size = Size.valueOf(request.getParameter("size"));
			year = Integer.parseInt(request.getParameter("year"));
			investmentAsk = Integer.parseInt(request.getParameter("investment"));
			equity = Integer.parseInt(request.getParameter("equity"));
			website = request.getParameter("website");
			ceoName = request.getParameter("ceoName");

			business.setDescription(description);
			business.setBusinessAbstract(myAbstract);
			business.setSize(size);
			business.setYear(year);
			business.setInvestmentAsk(investmentAsk);
			business.setEquityOffer(equity);
			business.setWebsite(website);
			business.setCeoName(ceoName);


			SQLCommands.updateBusinessAccount(business);
			
			

		}



		response.setContentType("text/html");
PrintWriter out = response.getWriter();

	out.print(output);

	}

	
}
