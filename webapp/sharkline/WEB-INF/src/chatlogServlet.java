import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class chatlogServlet extends HttpServlet
{

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    // Set refresh, autoload time as 1 seconds
    response.setIntHeader("Refresh", 1);


  }
}
