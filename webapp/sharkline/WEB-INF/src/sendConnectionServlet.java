import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class sendConnectionServlet extends HttpServlet
{

public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
HttpSession session = request.getSession(true);
Account account = (Account)session.getAttribute("account");
String investor_email = request.getParameter("investor_email");
String business_email = request.getParameter("business_email");

response.setContentType("text/html");
PrintWriter out = response.getWriter();

String output =
"<doctype html>><head><title>Connection Status</title></head>" +
"<body><h1>Outcome</h1>" +
"<p>Sent connection request successfully!</p>" +
"</body></html>";

out.println(output);
}
}