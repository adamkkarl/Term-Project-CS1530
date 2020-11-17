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
	String industry;
	String sizestr;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		SQLCommands = new SharklineJDBC();
		account = (Account)session.getAttribute("account");
		name = account.getName();

		



		if(account.getType() == Type.INVESTOR)
		{

			output = investorHTML();
			investor = SQLCommands.findInvestorAccountByName(name);
			description = request.getParameter("description");
			myAbstract = request.getParameter("abstract");
			investmentInit = Integer.parseInt(request.getParameter("investmentStart"));
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


			
			 response.sendRedirect("/sharkline/viewProfileServlet");
           
			
		}else{

			output = businessHTML();
			business = SQLCommands.findBusinessAccountByName(name);
			description = request.getParameter("description");
			myAbstract = request.getParameter("abstract");
			sizestr = request.getParameter("size");
			year = Integer.parseInt(request.getParameter("year"));
			investmentAsk = Integer.parseInt(request.getParameter("investment"));
			equity = Integer.parseInt(request.getParameter("equity"));
			website = request.getParameter("website");
			ceoName = request.getParameter("ceoName");

			Size size = getSize(sizestr);


			business.setDescription(description);
			business.setBusinessAbstract(myAbstract);
			business.setSize(size);
			business.setYear(year);
			business.setInvestmentAsk(investmentAsk);
			business.setEquityOffer(equity);
			business.setWebsite(website);
			business.setCeoName(ceoName);


			SQLCommands.updateBusinessAccount(business);

			
				response.sendRedirect("/sharkline/viewProfileServlet");
           
			
			
			

		}



		response.setContentType("text/html");
PrintWriter out = response.getWriter();

	out.print(output);

	}
	



	private Industry getIndustry(String industry)
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

	 private Size getSize(String size)
  {
    Size returnSize;
    if(size == null)
      return null;

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

  private String investorHTML()

  {



String output = "<!DOCTYPE html>"+
"<html lang=\"en-us\">"+
"<head>"+
"    <meta charset=\"UTF-8\">"+
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
"    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/setupStyle.css\">"+
"    <title>Update Your Profile</title>"+
"</head>"+
"<body>"+
"    <main>"+
"      <h2>Update Your Profile</h2>"+
"      <form method=\"get\" action=\"/sharkline/updateProfileServlet\">"+
"        "+
""+
"        <label for=\"description\">Add a Description</label><br>"+
"        <input required type = \"text\" required class=\"input_section\" placeholder=\"Description\" name=\"description\"><br><br>"+
""+
"        <label for=\"abstract\">Add an Abstract</label><br>"+
"        <input required type=\"text\" class=\"input_section\" placeholder=\"Abstract\" name=\"abstract\"><br><br>"+
""+
"        <label for=\"investmentStart\">Minimum Investment($)</label><br>"+
"        <input required type=\"number\" class=\"input_section\" placeholder=\"0\" name =\"investmentStart\"><br><br>"+
""+
"        <label for=\"investmentEnd\">Maximum Investment($)</label><br>"+
"        <input required type=\"number\" class=\"input_section\" placeholder=\"0\" name=\"investmentEnd\"><br><br>"+
""+
"        <label for=\"website\">Website URL</label><br>"+
"        <input required type=\"url\" class=\"input_section\" placeholder=\"www.mywebsite.com\" name=\"website\"><br><br>"+
""+
"        <label for=\"ceoName\">CEO Name</label><br>"+
"        <input required type=\"text\" class=\"input_section\" placeholder=\"John Doe\" name=\"ceoName\"><br><br>"+
""+
"        <input type=\"submit\" class=\"complete_setup\" value=\"Submit Changes\" >"+
"    </form>"+
"    </main>"+
"</body>"+
"</html>";
	


return output;
	

  }

  private String businessHTML()
  {


String output = "<!DOCTYPE html>"+
"<html lang=\"en-us\">"+
"<head>"+
"    <meta charset=\"UTF-8\">"+
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
"    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/setupStyle.css\">"+
"    <title>Update Business Profile</title>"+
"</head>"+
"<body>"+
"    <main>"+
"      <h2>Update Your Profile</h2>"+
"      <form method=\"get\" action=\"/sharkline/updateProfileServlet\">"+
"        "+
""+
"        <label for=\"description\">Add a Description</label><br>"+
"        <input required type = \"text\" required class=\"input_section\" placeholder=\"Description\" name=\"description\"><br><br>"+
""+
"        <label for=\"abstract\">Add an Abstract</label><br>"+
"        <input required type=\"text\" class=\"input_section\" placeholder=\"Abstract\" name=\"abstract\"><br><br>"+
""+
"        <label for=\"size\">Company Size</label><br>"+
"        <select name = \"size\" id = \"size\">"+
"          <option value=\"\">Select a Company Size</option>"+
"            <option value = \"1-10\">1-10</option>"+
"            <option value = \"11-30\">11-30</option>"+
"            <option value = \"31-50\">31-50</option>"+
"            <option value = \"51-100\">51-100</option>"+
"            <option value = \"200+\">200+</option>"+
""+
"        </select>"+
"        <br><br>"+
""+
"        <label for=\"year\">Year Established</label><br>"+
"        <select name = \"year\" id = \"year\">"+
"            <option value=\"\">Select a Year</option>"+
"            <option value =\"2020\">2020</option>"+
"            <option value =\"2019\">2019</option>"+
"            <option value=\"2018\">2018</option>"+
"            <option value=\"2017\">2017</option>"+
"            <option value=\"2016\">2016</option>"+
"            <option value=\"2015\">2015</option>"+
"            <option value=\"2014\">2014</option>"+
"            <option value=\"2013\">2013</option>"+
"            <option value=\"2012\">2012</option>"+
"            <option value=\"2011\">2011</option>"+
"            <option value=\"2010\">2010</option>"+
"            <option value=\"2009\">2009</option>"+
"            <option value=\"2008\">2008</option>"+
"            <option value=\"2007\">2007</option>"+
"            <option value=\"2006\">2006</option>"+
"            <option value=\"2005\">2005</option>"+
"            <option value=\"2004\">2004</option>"+
"            <option value=\"2003\">2003</option>"+
"            <option value=\"2002\">2002</option>"+
"            <option value=\"2001\">2001</option>"+
"            <option value=\"2000\">2000</option>"+
"            <option value=\"1999\">1999</option>"+
"            <option value=\"1998\">1998</option>"+
"            <option value=\"1997\">1997</option>"+
"            <option value=\"1996\">1996</option>"+
"            <option value=\"1995\">1995</option>"+
"            <option value=\"1994\">1994</option>"+
"            <option value=\"1993\">1993</option>"+
"            <option value=\"1992\">1992</option>"+
"            <option value=\"1991\">1991</option>"+
"            <option value=\"1990\">1990</option>"+
"            <option value=\"1989\">1989</option>"+
"            <option value=\"1988\">1988</option>"+
"            <option value=\"1987\">1987</option>"+
"            <option value=\"1986\">1986</option>"+
"            <option value=\"1985\">1985</option>"+
"            <option value=\"1984\">1984</option>"+
"            <option value=\"1983\">1983</option>"+
"            <option value=\"1982\">1982</option>"+
"            <option value=\"1981\">1981</option>"+
"            <option value=\"1980\">1980</option>"+
"            <option value=\"1979\">1979</option>"+
"            <option value=\"1978\">1978</option>"+
"            <option value=\"1977\">1977</option>"+
"            <option value=\"1976\">1976</option>"+
"            <option value=\"1975\">1975</option>"+
"            <option value=\"1974\">1974</option>"+
"            <option value=\"1973\">1973</option>"+
"            <option value=\"1972\">1972</option>"+
"            <option value=\"1971\">1971</option>"+
"            <option value=\"1970\">1970</option>"+
"            <option value=\"1969\">1969</option>"+
"            <option value=\"1968\">1968</option>"+
"            <option value=\"1967\">1967</option>"+
"            <option value=\"1966\">1966</option>"+
"            <option value=\"1965\">1965</option>"+
"            <option value=\"1964\">1964</option>"+
"            <option value=\"1963\">1963</option>"+
"            <option value=\"1962\">1962</option>"+
"            <option value=\"1961\">1961</option>"+
"            <option value=\"1960\">1960</option>"+
"            <option value=\"1959\">1959</option>"+
"            <option value=\"1958\">1958</option>"+
"            <option value=\"1957\">1957</option>"+
"            <option value=\"1956\">1956</option>"+
"            <option value=\"1955\">1955</option>"+
"            <option value=\"1954\">1954</option>"+
"            <option value=\"1953\">1953</option>"+
"            <option value=\"1952\">1952</option>"+
"            <option value=\"1951\">1951</option>"+
"            <option value=\"1950\">1950</option>"+
"            <option value=\"1949\">1949</option>"+
"            <option value=\"1948\">1948</option>"+
"            <option value=\"1947\">1947</option>"+
"            <option value=\"1946\">1946</option>"+
"            <option value=\"1945\">1945</option>"+
"            <option value=\"1944\">1944</option>"+
"            <option value=\"1943\">1943</option>"+
"            <option value=\"1942\">1942</option>"+
"            <option value=\"1941\">1941</option>"+
"            <option value=\"1940\">1940</option>"+
"            <option value=\"1939\">1939</option>"+
"            <option value=\"1938\">1938</option>"+
"            <option value=\"1937\">1937</option>"+
"            <option value=\"1936\">1936</option>"+
"            <option value=\"1935\">1935</option>"+
"            <option value=\"1934\">1934</option>"+
"            <option value=\"1933\">1933</option>"+
"            <option value=\"1932\">1932</option>"+
"            <option value=\"1931\">1931</option>"+
"            <option value=\"1930\">1930</option>"+
"        </select>"+
"        <br><br>"+
""+
""+
"        <label for=\"investment\">Seeking Investment Of</label><br>"+
"        <input required type=\"number\" class=\"input_section\" placeholder=\"0\" name=\"investment\"><br><br>"+
""+
"        <label for=\"equity\">Equity Offer</label><br>"+
""+
"        <div class=\"slidecontainer\">"+
"          <input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" id=\"equity\" name =\"equity\">"+
"          <p>Value: <span id=\"demo\"></span>%</p>"+
"        </div>"+
""+
"        <label for=\"website\">Website</label><br>"+
"        <input required type=\"url\" class=\"input_section\" placeholder=\"www.mywebsite.com\" name=\"website\"><br><br>"+
""+
""+
"        <label for=\"ceoName\">CEO Name</label><br>"+
"        <input required type=\"text\" class=\"input_section\" placeholder=\"John Doe\" name=\"ceoName\"><br><br>"+
""+
"        <input type=\"submit\" class=\"complete_setup\" value=\"Submit Changes\">"+
"    </form>"+
"    </main>"+
"  <script>"+
"    var slider = document.getElementById(\"equity\");"+
"    var output = document.getElementById(\"demo\");"+
"    output.innerHTML = slider.value;"+
""+
"    slider.oninput = function() {"+
"      output.innerHTML = this.value;"+
"    }"+
"  </script>"+
"</body>"+
"</html>";
	
	return output;



  }

	
}
