import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class myconnectionsServlet extends HttpServlet
{
  SharklineJDBC SQLCommands;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");
    SQLCommands = new SharklineJDBC();

  }
  private String getHtml(Account account)
  {
    String output =
    "<!DOCTYPE html>"+
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
    "          <a class=\"nav-link\" href=\"mynetworkServlet\">My Network <span class=\"sr-only\">(current)</span></a>"+
    "        </li>"+
    "        <li class=\"nav-item active\">"+
    "          <a class=\"nav-link\" href=\"myconnectionsServlet\">Connections/Messaging</a>"+
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
    "  <table>";

    if(account.getType() == Type.INVESTOR)
    {
      output +=
"     <label for=\"accepted\">Accepted Connections</label>"+
"     <table name=\"accepted\">"+
"       <tr><th>Logo</th><th>Name</th><th>Date Connected</th><th>Most Recent Message</th></tr>";

      ArrayList<UserConnection> connections = SQLCommands.getConnectionsByEmail(account.getEmail());
      for(int i = 0; i < connections.size(); i++)
      {
        if(connections.get(i).getConnected() == 1)
        {
          Business business = SQLCommands.findBusinessAccountByEmail
                                          (connections.get(i).getBusinessEmail());
          output +=
          "<tr><td><img src=\"" + business.getLogoPath() + "\" height=100px width=200px></td>"+
          "<td>" + business.getBusinessName() + "</td>" +
          "<td>" + connections.get(i).getDate() + "</td>" +
          "<td>Most recent message here</td>"
        }
      }
    }

    return null;
  }
}
