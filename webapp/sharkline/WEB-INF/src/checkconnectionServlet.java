import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class checkconnectionServlet extends HttpServlet
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Account account = (Account)session.getAttribute("account");
    SharklineJDBC SQLCommands = new SharklineJDBC();

    int connID = Integer.parseInt(request.getParameter("connectionid"));
    String check = request.getParameter("check");


  }
}
