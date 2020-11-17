import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class viewProfileServlet extends HttpServlet
{
String output;
	String name;
	String email;
	String image;
	String logo;
	Industry industry;
	String description;
	String myAbstract;
	Size size;
	int year;
	int investmentInit;
	int investmentEnd;
	int investmentAsk;
	int equity;
	String website;
	String ceoName;

	String jdbcUsername = "root";
	String jdbcPassword = "root";
	String jdbcURL = "jdbc:mysql://localhost/sharklinedb";
	Connection jdbcConnection;
	SharklineJDBC SQLCommands;
	Account account;
	Investor investor;
	Business business;



@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		SQLCommands = new SharklineJDBC();
		account = (Account)session.getAttribute("account");
		name = account.getName();
		email = account.getEmail();
		String parameterName = request.getParameter("name");
		String parameterType = request.getParameter("type");

		if(account.getType() == Type.INVESTOR && parameterName.equals(name))
		{
			investor = SQLCommands.findInvestorAccountByName(name);
			image = investor.getImage();
			description = investor.getInvestorDescription();
			myAbstract = investor.getInvestorAbstract();
			investmentInit = investor.getInvestmentRangeInit();
			investmentEnd = investor.getInvestmentRangeEnd();
			website = investor.getWebsite();
			ceoName = investor.getCeoName();
			output = printInvestorProfile(name, image, description, myAbstract,investmentInit, investmentEnd, website, ceoName );

		}
		else if(account.getType() == Type.BUSINESS && parameterName.equals(name))
		{
			business = SQLCommands.findBusinessAccountByName(name);
			logo = business.getLogoPath();
			industry = business.getBusinessIndustry();
			description = business.getDescription();
			myAbstract = business.getBusinessAbstract();
			size = business.getSize();
			year = business.getYear();
			investmentAsk = business.getInvestmentAsk();
			equity = business.getEquityOffer();
			website = business.getWebsite();
			ceoName = business.getCeoName();
			output = printBusinessProfile(name, logo, industry,  description,myAbstract, size, year, investmentAsk, equity, website, ceoName);
		}


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		out.print(output);

	}

	private static String printInvestorProfile(String name, String image, String description, String myAbstract,
												int investmentInit, int investmentEnd, String website, String ceoName )
	{

String output = "<!DOCTYPE html>"+
"<html>"+
"<head>"+
"  <meta charset=\"UTF-8\">"+
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
"  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"+
"  <script src=\"https://kit.fontawesome.com/18f40713d3.js\" crossorigin=\"anonymous\"></script>"+
"  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/profileStyle.css\">"+
"  <title>Profile</title>"+
"</head>"+
"<body>"+
"  <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">"+
"    <img src=\"./images/logo.png\" class=\"center logo\" alt=\"...\" width=\"160\" height=\"80\">"+
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"+
"      <span class=\"navbar-toggler-icon\"></span>"+
"    </button>"+
""+
"    <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">"+
"      <ul class=\"navbar-nav\">"+
"        <li class=\"nav-item active\">"+
"          <a class=\"nav-link\" href=\"#\">My Network <span class=\"sr-only\">(current)</span></a>"+
"        </li>"+
"        <li class=\"nav-item active\">"+
"          <a class=\"nav-link\" href=\"./webapp/messaging.html\">Messaging</a>"+
"        </li>"+
"        <li class=\"nav-item dropdown active\">"+
"          <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownMenuLink\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
"            Profile"+
"          </a>"+
"          <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">"+
"            <a class=\"dropdown-item\" href=\"#\">Edit Profile</a>"+
"            <a class=\"dropdown-item\" href=\"#\">Settings</a>"+
"            <a class=\"dropdown-item\" href=\"#\">Logout</a>"+
"          </div>"+
"        </li>"+
"      </ul>"+
"    </div>"+
"  </nav>"+
""+
"  <div class=\"profile\">"+
"    <img class=\"banner center\" src=\"https://cdn.cjr.org/wp-content/uploads/2019/07/AdobeStock_100000042-e1563305717660-686x371.jpeg\"/>"+
"    <img class=\"profilePic\" src=\"" + image + "\" alt=\"John\"/>"+
"    <div class=\"info\">"+
"      <h5 class=\"name\">"+ name + "</h5>"+
"      <h5 class=\"website\">" + website + "</h5><br>"+
"      <h5 class=\"seeking\">Seeking a $"+investmentInit + " - $" + investmentEnd + "</h5>"+
"    </div>"+
"    <div class=\"about\">"+
"      <h5 class=\"looking\">Description:</h5>"+
"      <p>" + description + "</p>"+
"    </div>"+
"    <div class=\"previous\">"+
"      <h5>Abstraction</h5>"+
"      <p>" + myAbstract + "</p>"+
"    </div>"+
"    <script>"+
"      function update()"+
"      {"+
"        var url = window.location.href;"+
"        url = url.replace(/\\/[^\\/]*$/, '/UpdateInvestorProfile.html');"+
"        location.replace(url)"+
"      "+
"      }"+
"    </script>"+
"    <button type =\"button\" class=\"update center\" onclick=\"update();\"> Update My Profile</button>"+
"  </div>"+
"  <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>"+
"  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>"+
"</body>"+
"</html>";



return output;

	}

	private static String printBusinessProfile(String name, String logo, Industry industry, String description, String myAbstract, Size size, int year,
												int investmentAsk, int equity, String website, String ceoName)
	{
		String output = "<!DOCTYPE html>"+
"<html>"+
"<head>"+
"  <meta charset=\"UTF-8\">"+
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
"  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"+
"  <script src=\"https://kit.fontawesome.com/18f40713d3.js\" crossorigin=\"anonymous\"></script>"+
"  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/profileStyle.css\">"+
"  <title>Profile</title>"+
"</head>"+
"<body>"+
"  <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">"+
"    <img src=\"./images/logo.png\" class=\"center logo\" alt=\"...\" width=\"160\" height=\"80\">"+
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"+
"      <span class=\"navbar-toggler-icon\"></span>"+
"    </button>"+
""+
"    <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">"+
"      <ul class=\"navbar-nav\">"+
"        <li class=\"nav-item active\">"+
"          <a class=\"nav-link\" href=\"#\">My Network <span class=\"sr-only\">(current)</span></a>"+
"        </li>"+
"        <li class=\"nav-item active\">"+
"          <a class=\"nav-link\" href=\"./webapp/messaging.html\">Messaging</a>"+
"        </li>"+
"        <li class=\"nav-item dropdown active\">"+
"          <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownMenuLink\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
"            Profile"+
"          </a>"+
"          <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">"+
"            <a class=\"dropdown-item\" href=\"#\">Edit Profile</a>"+
"            <a class=\"dropdown-item\" href=\"#\">Settings</a>"+
"            <a class=\"dropdown-item\" href=\"#\">Logout</a>"+
"          </div>"+
"        </li>"+
"      </ul>"+
"    </div>"+
"  </nav>"+
""+
"  <div class=\"profile\">"+
"    <img class=\"banner center\" src=\"https://cdn.cjr.org/wp-content/uploads/2019/07/AdobeStock_100000042-e1563305717660-686x371.jpeg\"/>"+
"    <img class=\"profilePic\" src=\"" + logo + "\" alt=\"Profie Picture\"/>"+
"    <div class=\"info\">"+
"      <h4 class=\"name\"><b>" + name + "</b></h4>"+
"      <h5 class=\"ceo\">CEO: " + ceoName + "</h5>"+
"      <h5 class=\"size\">"+size + "Employees</h5>"+
"      <h5 class=\"established\">Established" + year + "</h5>"+
"      <h5 class=\"website\">" + website + "</h5><br>"+
"      <h5 class=\"seeking\">Seeking: $" + investmentAsk + "K</h5>"+
"      <h5 class=\"equity\">For " + equity + "% equity</h5>"+
"    </div>"+
"    <div class=\"about\">"+
"      <h5 class=\"looking\">Description:</h5>"+
"      <p>" + description + "</p>"+
"    </div>"+
"    <div class=\"previous\">"+
"      <h5>Abstraction</h5>"+
"      <p>" + myAbstract + "</p>"+
"    </div>"+
"    <script>"+
"      function update()"+
"      {"+
"        var url = window.location.href;"+
"        url = url.replace(/\\/[^\\/]*$/, '/UpdateBusinessProfile.html');"+
"        location.replace(url)"+
"      "+
"      }"+
"    </script>"+
"    <button type =\"button\" class=\"update center\" onclick=\"update();\"> Update My Profile</button>"+
"  </div>"+
"  <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>"+
"  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>"+
"</body>"+
"</html>";



	return output;

	}






}
