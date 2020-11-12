import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class firstTimeSetupServlet extends HttpServlet
{
  SharklineJDBC SQLCommands;
  String output;

  @Overide
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    SQLCommands = new SharklineJDBC();
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");

    if(account.getType() == Type.BUSINESS)
    {
      output = "<!DOCTYPE html>" +
      "<html lang=\"en-us\">" +
      "<head>" +
        "<meta charset=\"UTF-8\">" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
      "</head>" +
      "<body>" +
        "<h2>Please complete your first time setup for your account</h2>" +
      "</body>" +
      "</html>";
    }
  }
}
