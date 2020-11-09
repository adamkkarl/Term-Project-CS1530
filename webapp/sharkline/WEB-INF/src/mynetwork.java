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
	Investor account;
	
	@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	HttpSession session = request.getSession();
	account = (Investor)session.getAttribute("investor_account");
	name = account.getInvestorName();
	response.setContentType("text/html");
PrintWriter out = response.getWriter();

output =
"<!DOCTYPE html>" +
"<html lang=\"en\">" +
"<head>" +
    "<meta charset=\"UTF-8\">" +
    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
    "<title>SharkLine - " + name + "</title>" +
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
          "<a class=\"dropdown-item\" href=\"#\">Logout</a>" +
        "</div>" +
      "</li>" +
    "</ul>" +
  "</div>" +
"</nav>" +
"<form class=\"form-inline center\">" +
    "<input class=\"form-control search\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">" +
    "<button class=\"btn\" type=\"submit\">Search</button>" +
"</form>" +
"<!-- TODO: need to add filter button and suggestions header -->" +
    "<script>suggestion();</script>" +
"<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>" +
"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>" +
"</body>" +
"</html>";
out.print(output);
}
}