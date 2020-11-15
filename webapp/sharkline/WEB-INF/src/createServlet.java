import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class createServlet extends HttpServlet
{
  SharklineJDBC SQLCommands;

    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String output = defaultHtml();
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    SQLCommands = new SharklineJDBC();
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirm = request.getParameter("confirm");
    String type = request.getParameter("type");
    String img = request.getParameter("proof");

    boolean attemptCreation = true;

    if(!password.equals(confirm))
    {
      output += "<h4>Passwords did not match</h4>";
      attemptCreation = false;
    }
    if(SQLCommands.findAccount(email) != null)
    {
      output +="<h4>Account already uses email</h>";
      attemptCreation = false;
    }
    if(SQLCommands.findAccountByName(name) != null)
    {
      output += "<h4>Account already uses name</h4>";
      attemptCreation = false;
    }
    if(attemptCreation)
    {
      Type accType;
      if(type.equalsIgnoreCase("business"))
        accType = Type.BUSINESS;
      else
        accType = Type.INVESTOR;

      Account createAccount = new Account(email, name, password, false, img, accType);
      if(SQLCommands.addAccount(createAccount))
      {
        RequestDispatcher rd = request.getRequestDispatcher("/createsuccess.html");
        rd.forward(request, response);
      }
      else
      {
        throw new RuntimeException("Account not added into database");
      }
    }
    output += "</body>" +
              "</html>";

    out.print(output);
  }
  private String defaultHtml()
  {
    String myvar = "<!DOCTYPE html>"+
    "<html lang=\"en-us\">"+
    "<head>"+
    "    <meta charset=\"UTF-8\">"+
    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/setupStyle.css\">"+
    "    <title>Sign-Up</title>"+
    "</head>"+
    "<body>"+
    "    <main>"+
    "      <h2>Account Sign-up</h2>"+
    "      <form method=\"post\" action=\"createServlet\">"+
    "        <label for=\"type\">Type of Account</label><br>"+
    "        <label for=\"investor\"><input required type=\"radio\" name=\"type\" value=\"investor\">Investor</label><br>"+
    "        <label for=\"business\"><input required type=\"radio\" name=\"type\" value=\"business\">Business</label><br><br>"+
    ""+
    "        <label for=\"name\">Enter name of Business/Investment firm</label><br>"+
    "        <input required class=\"input_section\" placeholder=\"Name\" name=\"name\"><br><br>"+
    "        <label for=\"email\">Enter Email Address</label><br>"+
    "        <input required type=\"email\" class=\"input_section\" placeholder=\"Email\" name=\"email\"><br><br>"+
    "        <label for=\"password\">Enter Password</label><br>"+
    "        <input required type=\"password\" class=\"input_section\" placeholder=\"Password\" name =\"password\"><br><br>"+
    "        <label for=\"confirm\">Confirm Password</label><br>"+
    "        <input required type=\"password\" class=\"input_section\" placeholder=\"Confirm Password\" name=\"confirm\"><br><br>"+
    ""+
    "        <label for=\"proof\">Imgur link to verification</label><br>"+
    "        <input required type=\"url\" class=\"input_section\" placeholder=\"Imgur link\" name=\"proof\"><br><br>"+
    ""+
    "        <input type=\"submit\" value=\"Register\">"+
    "    </form>"+
    "    </main>";

    return myvar;
  }
}
