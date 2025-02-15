import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class logoutServlet extends HttpServlet
{

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
  HttpSession session = request.getSession(true);
  session.invalidate();
  RequestDispatcher rd = request.getRequestDispatcher("index.html");
  rd.forward(request, response);
  }
}
