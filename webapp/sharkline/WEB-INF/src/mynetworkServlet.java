import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class mynetworkServlet extends HttpServlet
{
	String output;
	String name;
	SharklineJDBC SQLCommands;
	Account account;

	@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	HttpSession session = request.getSession();
	SQLCommands = new SharklineJDBC();
	account = (Account)session.getAttribute("account");
	name = account.getName();

	response.setContentType("text/html");
PrintWriter out = response.getWriter();

output = printMyNetwork(name, account);

if(account.getType() == Type.INVESTOR)
{
	String searchValueByBusinessIndustry = request.getParameter("searchbusinessindustry");
	String searchValueByBusinessName = request.getParameter("searchbusinessname");

	if (searchValueByBusinessIndustry == null && searchValueByBusinessName == null )
	{
		output += "";
	}
	else if ( searchValueByBusinessIndustry.equals("") && searchValueByBusinessName.equals("") )
	{
		output += "<p>Please use one of the search fields above to find a business.</p>";
	}
	else if ( !searchValueByBusinessIndustry.equals("") && !searchValueByBusinessName.equals("") )
	{
		output += "<p>Please use only one field to search.</p>";
	}
	else if ( !searchValueByBusinessIndustry.equals("") && searchValueByBusinessName.equals("") )
	{
		Industry industry = getIndustry(searchValueByBusinessIndustry);
		ArrayList<Business> businesses = SQLCommands.findBusinessesByIndustry(industry);

		if ( businesses == null )
		{
			output += printNoSearchResults();
		}
		else
		{
			output +=
			"<h2>Search Results</h2>" +
			"<table class=\"center\">" +
			"<tr><th>Logo</th><th>Business Name</th><th>Abstract</th><th>Website</th><th>View Profile</th></tr>";
			for ( int i = 0; i < businesses.size(); i++ )
			{
				output += "<tr><form action=\"viewProfileServlet\" method=\"get\"><td><img src=\"" +
				businesses.get(i).getLogoPath() + "\" width=200px height=100px></td><td>" +
				businesses.get(i).getBusinessName() + "</td><td>" +
				businesses.get(i).getBusinessAbstract() + "</td><td>" + businesses.get(i).getWebsite() + "</td><td>" +
				"<input type=\"submit\" value=\"View Profile\"</td></form></tr>";
			}
			output += "</table>";
		}
	}

else if ( !searchValueByBusinessName.equals("") && searchValueByBusinessIndustry.equals("") )
{
	ArrayList<Business> businesses = SQLCommands.findBusinessesByLikeName(searchValueByBusinessName);

	if ( businesses == null )
		{
			output += printNoSearchResults();
		}
			else
		{
			output +=
			"<h2>Search Results</h2>" +
			"<table class=\"center\">" +
			"<tr><th>Logo</th><th>Business Name</th><th>Abstract</th><th>Website</th><th>View Profile</th></tr>";
			for ( int i = 0; i < businesses.size(); i++ )
			{
				output += "<tr><form action=\"viewProfileServlet\" method=\"get\"><td><img src=\"" +
				businesses.get(i).getLogoPath() + "\" width=200px height=100px></td><td>" +
				businesses.get(i).getBusinessName() + "</td><td>" +
				businesses.get(i).getBusinessAbstract() + "</td><td>" + businesses.get(i).getWebsite() + "</td><td>" +
				"<input type=\"submit\" value=\"View Profile\"</td></form></tr>";
			}
			output += "</table>";
		}
}
} // End of business search if block

/*if(account.getType() == Type.BUSINESS)
{
	if(searchValue != null && isNumeric(searchValue))
	{
		searchValue = searchValue.trim();
		int ask = Integer.parseInt(searchValue);
		ArrayList<Investor> investors = SQLCommands.findInvestorsByAsk(ask);
		if(investors == null)
		{
			output += printNoSearchResults();
		}
		else
		{
			output +=
			"<h2>Search Results</h2>" +
			"<table class=\"center\">" +
			"<tr><th>Investor Name</th><th>Abstract</th><th>Investment Range</th><th>Website</th></tr>";
			for(int i = 0; i < investors.size(); i++)
			{
				output +=
				"<tr><td>" + investors.get(i).getInvestorName() + "</td>" +
				"<td>" + investors.get(i).getInvestorAbstract() + "</td>" +
				"<td>" + investors.get(i).getInvestmentRangeInit() + " - " + investors.get(i).getInvestmentRangeEnd() + "</td>" +
				"<td>" + investors.get(i).getWebsite() + "</td></tr>";
			}
			output += "</table>";
		}
	}
}*/

output +=
"</body>" +
"</html>";
out.print(output);
}

  /**
  * setIndustry is a helper method designed to streamline updating industry attribute
  * in business_accounts table, not for use in queries
  *
  * @param st the PreparedStatement update which we will update
  * @param industry the enum Industry (see enum Industry above)
  * @param pos the position in the PreparedStatement we would like to inject to
  *
  * @return the PreparedStatement st with industry set at position pos, null
  *         if otherwise
  */
  private static PreparedStatement setIndustry(PreparedStatement st, Industry industry, int pos)
  {
    try
    {
      switch(industry)
      {
        case INDUSTRIAL:
          st.setString(pos, "Industrial");
          break;
        case HEALTH:
          st.setString(pos, "Health");
          break;
        case SOFTWARE_TECH:
          st.setString(pos, "Software/Tech");
          break;
        case ENTERTAINMENT:
          st.setString(pos, "Entertainment");
          break;
        case FOOD:
          st.setString(pos, "Food");
          break;
        case RETAIL:
          st.setString(pos, "Retail");
          break;
        case FINANCE:
          st.setString(pos, "Finance");
          break;
        case MARKETING:
          st.setString(pos, "Marketing");
          break;
        case SALES:
          st.setString(pos, "Sales");
          break;
        case AUTOMOTIVE:
          st.setString(pos, "Automotive");
          break;
        case EDUCATION:
          st.setString(pos, "Education");
          break;
        case LAW:
          st.setString(pos, "Law");
          break;
        case HOTEL:
          st.setString(pos, "Hotel");
          break;
        case TRAVEL:
          st.setString(pos, "Travel");
          break;
        case ENERGY:
          st.setString(pos, "Energy");
          break;
        case ENVIRONMENT:
          st.setString(pos, "Environment");
          break;
        case TRANSPORTATION:
          st.setString(pos, "Transportation");
          break;
        default:
          st.setString(pos, "Other");
      }
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
      return null;
    }
    return st;
  }
  private static PreparedStatement setSize(PreparedStatement st, Size size, int pos)
  {
    try
    {
      switch(size)
      {
        case ONE_TO_TEN:
          st.setString(pos, "1-10");
          break;
        case ELEVEN_TO_THIRTY:
          st.setString(pos, "11-30");
          break;
        case THIRTYONE_TO_FIFTY:
          st.setString(pos, "31-50");
          break;
        case FIFTYONE_TO_ONEHUNDRED:
          st.setString(pos, "51-100");
          break;
        case ONEHUNDREDANDONE_TO_TWOHUNDRED:
          st.setString(pos, "101-200");
          break;
        case TWOHUNDREDPLUS:
          st.setString(pos, "200+");
          break;
      }
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
      return null;
    }
    return st;
  }
  private static Size getSize(String size)
  {
    Size returnSize;
if ( size == null ) { return null; }
    if(size.equals("1-10"))
      returnSize = Size.ONE_TO_TEN;
    else if(size.equals("11-30"))
      returnSize = Size.ELEVEN_TO_THIRTY;
    else if(size.equals("31-50"))
      returnSize = Size.THIRTYONE_TO_FIFTY;
    else if(size.equals("51-100"))
      returnSize = Size.FIFTYONE_TO_ONEHUNDRED;
    else if(size.equals("101-200"))
      returnSize = Size.ONEHUNDREDANDONE_TO_TWOHUNDRED;
    else if(size.equals("200+"))
      returnSize = Size.TWOHUNDREDPLUS;
    else
      returnSize = null;

    return returnSize;
  }
  private static Industry getIndustry(String industry)
  {
    Industry returnIndustry;

    if(industry.equals("Industrial"))
      returnIndustry = Industry.INDUSTRIAL;
    else if(industry.equals("Health"))
      returnIndustry = Industry.HEALTH;
    else if(industry.equals("Software/Tech"))
      returnIndustry = Industry.SOFTWARE_TECH;
    else if(industry.equals("Entertainment"))
      returnIndustry = Industry.ENTERTAINMENT;
    else if(industry.equals("Food"))
      returnIndustry = Industry.FOOD;
    else if(industry.equals("Retail"))
      returnIndustry = Industry.RETAIL;
    else if(industry.equals("Finance"))
      returnIndustry = Industry.FINANCE;
    else if(industry.equals("Marketing"))
      returnIndustry = Industry.MARKETING;
    else if(industry.equals("Sales"))
      returnIndustry = Industry.SALES;
    else if(industry.equals("Automotive"))
      returnIndustry = Industry.AUTOMOTIVE;
    else if(industry.equals("Education"))
      returnIndustry = Industry.EDUCATION;
    else if(industry.equals("Law"))
      returnIndustry = Industry.LAW;
    else if(industry.equals("Hotel"))
      returnIndustry = Industry.HOTEL;
    else if(industry.equals("Travel"))
      returnIndustry = Industry.TRAVEL;
    else if(industry.equals("Energy"))
      returnIndustry = Industry.ENERGY;
    else if(industry.equals("Environment"))
      returnIndustry = Industry.ENVIRONMENT;
    else if(industry.equals("Transportation"))
      returnIndustry = Industry.TRANSPORTATION;
    else
      returnIndustry = Industry.OTHER;

    return returnIndustry;
  }
	private boolean isNumeric(String number)
	{
		for(char c : number.toCharArray())
		{
			if(!Character.isDigit(c))
				return false;
		}
		return true;
	}

	private static String printMyNetwork(String name, Account account)
	{
		String output =
		"<!DOCTYPE html>" +
"<html lang=\"en\">" +
"<head>" +
    "<meta charset=\"UTF-8\">" +
    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
    "<title>SharkLine - " + name + "</title>" +
	"<!-- TODO: need to add filter button and suggestions header -->" +
    "<script>suggestion();</script>" +
"<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>" +
"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>" +
    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">" +
    "<script src=\"main.js\"></script>" +
    "<link rel=\"stylesheet\" href=\"./css/style.css\">" +
"</head>" +
"<body>" +
  "<div class=\"sidenav navbar-dark bg-dark\">" +
    "<a href=\"#\">Connections</a>" +
    "<a href=\"#\">Events</a>" +
    "<a href=\"#\">Groups</a>" +
  "</div>" +
"<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">" +
  "<img src=\"./images/logo.png\" class=\"center logo\" alt=\"...\" width=\"160\" height=\"80\">" +
  "<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">" +
    "<span class=\"navbar-toggler-icon\"></span>" +
  "</button>" +
  "<div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">" +
    "<ul class=\"navbar-nav\">" +
      "<li class=\"nav-item active\">" +
        "<a class=\"nav-link\" href=\"#\">My Network <span class=\"sr-only\">(current)</span></a>" +
      "</li>" +
      "<li class=\"nav-item active\">" +
        "<a class=\"nav-link\" href=\"#\">Messaging</a>" +
      "</li>" +
      "<li class=\"nav-item dropdown active\">" +
        "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownMenuLink\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
          "Profile" +
        "</a>" +
        "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">" +
          "<a class=\"dropdown-item\" href=\"#\">Edit Profile</a>" +
          "<a class=\"dropdown-item\" href=\"#\">Settings</a>" +
          "<a class=\"dropdown-item\" href=\"logoutServlet\">Logout</a>" +
        "</div>" +
      "</li>" +
    "</ul>" +
  "</div>" +
"</nav>" +
"<form class=\"form-inline center\" method=\"post\" action=\"mynetworkServlet\">";
 if ( account.getType() == Type.INVESTOR)
 {
	 output +=
    "<input class=\"form-control search\" type=\"text\" id=\"searchbusinessname\" name=\"searchbusinessname\" placeholder=\"Business Name\" aria-label=\"Business Name\">" +
    "<select class=\"form-control dropdown\" name=\"searchbusinessindustry\" aria-label=\"Industry Name\">"+
		"          <option value=\"\">Select an industry</option>"+
		"          <option value=\"Industrial\">Industrial</option>"+
		"          <option value=\"Health\">Health</option>"+
		"          <option value=\"Software/Tech\">Software/Tech</option>"+
		"          <option value=\"Entertainment\">Entertainment</option>"+
		"          <option value=\"Food\">Food</option>"+
		"          <option value=\"Finance\">Finance</option>"+
		"          <option value=\"Retail\">Retail</option>"+
		"          <option value=\"Marketing\">Marketing</option>"+
		"          <option value=\"Sales\">Sales</option>"+
		"          <option value=\"Automotive\">Automotive</option>"+
		"          <option value=\"Education\">Education</option>"+
		"          <option value=\"Law\">Law</option>"+
		"          <option value=\"Hotel\">Hotel</option>"+
		"          <option value=\"Travel\">Travel</option>"+
		"          <option value=\"Energy\">Energy</option>"+
		"          <option value=\"Environment\">Environment</option>"+
		"          <option value=\"Transportation\">Transportation</option>"+
		"          <option value=\"Other\">Other</option>"+
		"      </select>"+
    "<button class=\"btn\" type=\"submit\">Search</button>";
 }
 output += "</form>";

return output;
	}

private static String printNoSearchResults()
{
	String output =
	"<h2>No Search Results Found</h2>" +
	"<p[>No results were returned. Try a different search.</p>";

	return output;
}
}
