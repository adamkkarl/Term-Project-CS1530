import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class chatlogServlet extends HttpServlet
{

  SharklineJDBC SQLCommands;
  Account account;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    account = (Account)session.getAttribute("account");
    String email = account.getEmail();

    SQLCommands = new SharklineJDBC();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();


    // Set refresh, autoload time as 1 seconds
    response.setIntHeader("Refresh", 1);

    ArrayList<String> previews = SQLCommands.getConversationPreviews(email);
    ArrayList<String> contact = new ArrayList<String>();
    ArrayList<String> lastMessage = new ArrayList<String>();

    for (String p : previews) {
      String a[]= p.split("~");
      String inv_name = a[0];
      String bus_name = a[1];
      String message = a[2];

      //TODO HERE
      if(account.getType() == Type.INVESTOR)
      {
        contact.add(bus_name);
      } else {
        contact.add(inv_name);
      }
      lastMessage.add(message);
    }

    //TODO
    //at this point, contact is an array of contact names, and message is an array of messages
    //contact[i] corresponds to message[i]


    int cid = 1; //TODO figure out which chat log we're looking at currently
    ArrayList<ChatLog> allMessages = SQLCommands.getChatLogByConnectionID(cid);





    //from messaging.html, doesn't look like it's done yet
    String output = "<!DOCTYPE html>"+
    "<html lang=/\"en/\">"+
    "<head>"+
    "    <meta charset=/\"UTF-8/\">"+
    "    <meta name=/\"viewport/\" content=/\"width=device-width, initial-scale=1.0/\">"+
    "    <title>SharkLine</title>"+
    "    <link rel=/\"stylesheet/\" href=/\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css/\" integrity=/\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk/\" crossorigin=/\"anonymous/\">"+
    "    <link rel=/\"stylesheet/\" href=/\"./css/msgStyle.css/\">"+
    "</head>"+
    "<body>"+
    "    <nav class=/\"navbar navbar-expand-lg navbar-dark bg-dark/\">"+
    "      <img src=/\"./images/logo.png/\" class=/\"center logo/\" alt=/\".../\" width=/\"160/\" height=/\"80/\">"+
    "      <button class=/\"navbar-toggler/\" type=/\"button/\" data-toggle=/\"collapse/\" data-target=/\"#navbarSupportedContent/\" aria-controls=/\"navbarSupportedContent/\" aria-expanded=/\"false/\" aria-label=/\"Toggle navigation/\">"+
    "        <span class=/\"navbar-toggler-icon/\"></span>"+
    "      </button>"+
    "    "+
    "      <div class=/\"collapse navbar-collapse/\" id=/\"navbarNavDropdown/\">"+
    "        <ul class=/\"navbar-nav/\">"+
    "          <li class=/\"nav-item active/\">"+
    "            <a class=/\"nav-link/\" href=/\"./network.html/\">My Network <span class=/\"sr-only/\">"+
    "          </li>"+
    "          <li class=/\"nav-item active/\">"+
    "            <a class=/\"nav-link/\" href=/\"#/\">Messaging</a>(current)</span></a>"+
    "          </li>"+
    "          <li class=/\"nav-item dropdown active/\">"+
    "            <a class=/\"nav-link dropdown-toggle/\" href=/\"#/\" id=/\"navbarDropdownMenuLink/\" role=/\"button/\" data-toggle=/\"dropdown/\" aria-haspopup=/\"true/\" aria-expanded=/\"false/\">"+
    "              Profile"+
    "            </a>"+
    "            <div class=/\"dropdown-menu/\" aria-labelledby=/\"navbarDropdownMenuLink/\">"+
    "              <a class=/\"dropdown-item/\" href=/\"#/\">Edit Profile</a>"+
    "              <a class=/\"dropdown-item/\" href=/\"#/\">Settings</a>"+
    "              <a class=/\"dropdown-item/\" href=/\"#/\">Logout</a>"+
    "            </div>"+
    "          </li>"+
    "        </ul>"+
    "      </div>"+
    "    </nav>"+
    "    <div id=/\"chat-container/\">"+
    "        <div id=/\"search-container/\">"+
    "            <input type=/\"text/\" placeholder=/\"Search/\"/>	"+
    "        </div>"+
    "        <div id=/\"conversation-list/\">"+
    "            <div class=/\"conversation/\">"+
    "                <img src=/\"https://icon-library.com/images/free-profile-icon/free-profile-icon-25.jpg/\" alt=/\".../\"/>"+
    "                <div class=/\"title-text/\">"+
    "                    John Doe"+
    "                </div>"+
    "                <div class=/\"created-date/\">"+
    "                    Nov 8"+
    "                </div>"+
    "                <div class=/\"conversation-message/\">"+
    "                    Pretty good! Just got a promotion at work and I'm getting married next week!"+
    "                </div>"+
    "            </div>"+
    "            <div class=/\"conversation/\">"+
    "                <img src=/\"https://icon-library.com/images/free-profile-icon/free-profile-icon-25.jpg/\" alt=/\".../\"/>"+
    "                <div class=/\"title-text/\">"+
    "                    Jane Doe"+
    "                </div>"+
    "                <div class=/\"created-date/\">"+
    "                    2 days ago"+
    "                </div>"+
    "                <div class=/\"conversation-message/\">"+
    "                    Hi!"+
    "                </div>"+
    "            </div>"+
    "        </div>"+
    "        <div id=/\"new-message-container/\">"+
    "            <a href=/\"#/\">+</a>"+
    "        </div>"+
    "        <div id=/\"chat-title/\">"+
    "            <span>John Doe</span>"+
    "            <img src=/\"../webapp/css/delete.svg/\" alt=/\"Delete Conversation/\"/>"+
    "        </div>"+
    "        <div id=/\"chat-message-list/\">"+
    "            <div class=/\"message-row you-message/\">"+
    "                <div class=/\"message-text/\">Hey!</div>"+
    "                <div class=/\"message-time/\">Nov 8</div>"+
    "            </div>"+
    "            <div class=/\"message-row other-message/\">"+
    "                <div class=/\"message-text/\">Hello!</div>"+
    "                <div class=/\"message-time/\">Nov 8</div>"+
    "            </div>"+
    "            <div class=/\"message-row you-message/\">"+
    "                <div class=/\"message-text/\">How have you been?</div>"+
    "                <div class=/\"message-time/\">Nov 8</div>"+
    "            </div>"+
    "            <div class=/\"message-row other-message/\">"+
    "                <div class=/\"message-text/\">Pretty good! Just got a promotion at work and I'm getting married next week!</div>"+
    "                <div class=/\"message-time/\">Nov 8</div>"+
    "            </div>"+
    "        </div>"+
    "        <div id=/\"chat-form/\">"+
    "            <img src=/\"../webapp/css/paperclip.svg/\" alt=/\"Add Attachment/\"/>"+
    "            <input type=/\"text/\" placeholder=/\"type a message/\" />"+
    "        </div>"+
    "    </div>"+
    "    <script src=/\"https://code.jquery.com/jquery-3.5.1.slim.min.js/\" integrity=/\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj/\" crossorigin=/\"anonymous/\"></script>"+
    "    <script src=/\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js/\" integrity=/\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx/\" crossorigin=/\"anonymous/\"></script>    "+
    "</body>"+
    "</html>";

    out.print(output);


  }
}
