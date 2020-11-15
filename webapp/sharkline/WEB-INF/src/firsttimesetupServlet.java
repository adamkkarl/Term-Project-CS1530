import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class firsttimesetupServlet extends HttpServlet
{
  SharklineJDBC SQLCommands;
  String output;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    SQLCommands = new SharklineJDBC();
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    if(account.getType() == Type.BUSINESS)
    {
      output = businessHtml();

      String industryStr = request.getParameter("industry_select");
      String sizeStr = request.getParameter("size");
      String website = request.getParameter("website");
      String ceo = request.getParameter("ceo_name");
      int investment = Integer.parseInt(request.getParameter("investment_ask"));
      int equity = Integer.parseInt(request.getParameter("equity_percent"));
      int year = Integer.parseInt(request.getParameter("established"));
      String abs = request.getParameter("abstract");
      String description = request.getParameter("description");
      String logo = request.getParameter("logo");
      Business newBusiness = new Business(account.getEmail(), account.getName(),
                            description, abs, logo, getSize(sizeStr), year,
                            investment, equity, website, ceo, getIndustry(industryStr));

      if(SQLCommands.addBusinessAccount(account, getIndustry(industryStr)))
      {
          if(SQLCommands.updateBusinessAccount(newBusiness))
          {
            RequestDispatcher rd = request.getRequestDispatcher("mynetworkServlet");
            rd.forward(request, response);
          }
      }
      output += "</body></head>";
      out.print(output);
    }
  }
  public String businessHtml()
  {
    String myvar = "<!DOCTYPE html>"+
                    "<html lang=\"en-us\">"+
                    "<head>"+
                    "    <meta charset=\"UTF-8\">"+
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                    "    <link rel=\"stylesheet\" href=\"./css/setupStyle.css\">"+
                    "</head>"+
                    "<body>"+
                    "  <h2>Please complete your first time setup for your account</h2>"+
                    "  <label for=\"industry_select\">Select Industry</label><br>"+
                    "  <form method=\"post\" action=\"firstTimeSetupServlet\">"+
                    "      <select required id =\"industry_select\" name=\"industry_select\">"+
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
                    "      </select><br><br>"+
                    "      <label for=\"size\">Number of Employees</label><br>"+
                    "      <select id=\"size\" name = \"size\">"+
                    "          <option value=\"\">Select a company size</option>"+
                    "          <option value=\"1-10\">1-10</option>"+
                    "          <option value=\"11-30\">11-30</option>"+
                    "          <option value=\"31-50\">31-50</option>"+
                    "          <option value=\"51-100\">51-100</option>"+
                    "          <option value=\"101-200\">101-200</option>"+
                    "          <option value=\"200+\">200+</option>"+
                    "      </select><br><br>"+
                    "      <label for=\"website\">Website URL</label><br>"+
                    "      <input class=\"input_section\" placeholder=\"Website\" name=\"website\"><br><br>"+
                    "      <label for=\"ceo_name\">CEO/President Name</label><br>"+
                    "      <input class=\"input_section\" placeholder=\"CEO Name\" name=\"ceo_name\"><br><br>"+
                    "      <label for=\"investment_ask\">Investment</label><br>"+
                    "      <input type=\"number\" class=\"input_section\" placeholder=\"Investment Ask\" min=0 name=\"investment_ask\"><br><br>"+
                    "      <label for=\"established\">Established Year</label><br>"+
                    "      <input type=\"number\" class=\"input_section\" placeholder=\"Established\" max=2020 min=0 name=\"established\"><br><br>"+
                    "      <label for=\"equity_percent\">Percent of Equity offered</label><br>"+
                    "      <input type=\"number\" class=\"input_section\" placeholder=\"Percent\" max=100 min=0 name=\"equity_percent\"><br>"+
                    "      <label for=\"abtract\">Profile abstract</label><br>"+
                    "      <textarea name=\"abstract\" rows=\"3\" cols=\"25\"></textarea><br><br>"+
                    "      <label for=\"description\">Profile Description</label><br>"+
                    "      <textarea name=\"description\" rows=\"5\" cols=\"60\"></textarea><br><br>"+
                    "      <label for=\"Logo\">Imgur link to logo</label><br>"+
                    "      <input required type=\"url\" class=\"input_section\" placeholder=\"Imgur link\" name=\"logo\"><br><br>"+
                    "      <input type=\"submit\" class=\"complete_setup\" value=\"Complete Setup\">"+
                    "  </form>";
      return myvar;
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
}
