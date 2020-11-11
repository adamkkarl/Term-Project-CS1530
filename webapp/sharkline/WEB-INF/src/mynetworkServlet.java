import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class mynetwork extends HttpServlet
{
	String output;
	String name;
	String jdbcUsername = "root";
	String jdbcPassword = "grenadine@2020";
	String jdbcURL = "jdbc:mysql://localhost/sharklinedb";
	Connection jdbcConnection;
	Investor account;
	
	@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	HttpSession session = request.getSession();
	account = (Investor)session.getAttribute("investor_account");
	name = account.getInvestorName();
	String searchValue = request.getParameter("searchval");
	
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
		
	response.setContentType("text/html");
PrintWriter out = response.getWriter();

output =
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
          "<a class=\"dropdown-item\" href=\"logout\">Logout</a>" +
        "</div>" +
      "</li>" +
    "</ul>" +
  "</div>" +
"</nav>" +
"<form class=\"form-inline center\" method=\"post\" action=\"mynetwork\">" +
    "<input class=\"form-control search\" type=\"text\" id=\"searchval\" name=\"searchval\" placeholder=\"Search\" aria-label=\"Search\">" +
    "<button class=\"btn\" type=\"submit\">Search</button>" +
"</form>";

if ( searchValue != null )
{
	Industry industry = getIndustry(searchValue);
	ArrayList<Business> businesses = findBusinessesByIndustry(industry);
	
	if ( businesses == null )
	{
		output += "<h2>0 Search Results Found</h2>";
	}
	if ( businesses.size() >= 1 )
	{
		output +=
		"<h2>Search Results</h2>" +
		"<table class=\"center\">" +
		"<tr><th>Business Name</th><th>Description</th><th>Abstract</th><th>Logo</th><th>Website</th></tr>";
	for ( int i = 0; i < businesses.size(); i++ )
	{
		output +=
		"<tr><td>" + businesses.get(i).getBusinessName() + "</td><td>" + businesses.get(i).getDescription() + "</td><td>" + businesses.get(i).getBusinessAbstract() + "</td><td>" + businesses.get(i).getLogoPath() + "</td><td>" + businesses.get(i).getWebsite() + "</td></tr>";
	}
	output += "</table>";
	}
}

output +=
"</body>" +
"</html>";
out.print(output);
}

public ArrayList<Business> findBusinessesByIndustry(Industry industry)
  {
    try
    {
      ArrayList businesses = new ArrayList<Business>();
      PreparedStatement st =
      jdbcConnection.prepareStatement("SELECT * FROM business_accounts WHERE industry = ?");
      setIndustry(st, industry, 1);

      ResultSet result = st.executeQuery();
      while(result.next())
      {
        Business account = new Business();

        account.setBusinessEmail(result.getString("business_email"));
        account.setBusinessName(result.getString("business_name"));
        account.setBusinessAbstract(result.getString("business_abstract"));
        account.setDescription(result.getString("business_description"));
        account.setLogoPath(result.getString("logo"));
        account.setSize(getSize(result.getString("size")));
        account.setYear(result.getInt("established"));
        account.setInvestmentAsk(result.getInt("investment_ask"));
        account.setEquityOffer(result.getInt("equity_offer"));
        account.setWebsite(result.getString("website"));
        account.setCeoName(result.getString("name_CEO"));
        account.setBusinessIndustry(getIndustry(result.getString("industry")));

        businesses.add(account);
      }
      if(businesses.size() == 0)
        return null;

      st.close();
      return businesses;
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
}