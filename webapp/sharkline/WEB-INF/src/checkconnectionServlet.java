import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class checkconnectionServlet extends HttpServlet
{
  SharklineJDBC SQLCommands;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");
    SQLCommands = new SharklineJDBC();

    int connID = Integer.parseInt(request.getParameter("connectionid"));
    String check = request.getParameter("check");

    UserConnection userConn = SQLCommands.findConnection(connID);
    if(check.equalsIgnoreCase("accept"))
    {
      SQLCommands.updateConnected(userConn);
    }
    else if(check.equalsIgnoreCase("reject"))
    {
      SQLCommands.removeConnection(userConn);
    }

    String output = getHtml();
    PrintWriter out = response.getWriter();
    out.print(output);
  }
  private String getHtml()
  {
    String output =
      "<!DOCTYPE html>"+
      "<html lang=\"en-us\">"+
      "<head>"+
      "    <meta charset=\"UTF-8\">"+
      "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
      "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/setupStyle.css\">"+
      "</head>"+
      "<body>"+
      "<h3>Connection updated!</h3>"+
      "<a href=myconnectionsServlet>Return to connections</a>"+
      "</body>"+
      "</html>";

      return output;
  }
}
