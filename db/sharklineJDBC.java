//Group 13 JDBC Code
//Sharkline Website

import java.util.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
* This file processes all Java Database Connectivity code. Use the main
* method to test some of the queries you write, but otherwise any
* query you write make sure to create a method for it with documentation!
*
* Also, you probably will need to install the MySQL server from the workbench.
* Link is provided here:  https://dev.mysql.com/downloads/installer/
* Youtube for additional help on installation: https://www.youtube.com/watch?v=kEnD_KN7P-k&t=12s
*
* When you download MySQL Workbench and the server, you'll need to run the
* scripts from the SharklineSchema.sql on your local server for this to work
* properly (as well as having some data in the tables)
*
* Also, you need to download the JConnector .jar file for this to work as well.
* That can be found here: https://dev.mysql.com/downloads/connector/j/
* Once that is downloaded, you either needed to set the CLASSPATH in your
* environment variables (windows) to set to that .jar file, or you
* need to include the -cp when running the program (java -cp %FILEPATH_HERE%; sharklineJDBC)
* DONT FORGET THAT SEMI COLON!
*
* If you have any questions, just ask me (Quinn) in the discord or something.
**/
public class sharklineJDBC
{
  public enum Type
  {
    INVESTOR,
    BUSINESS;
  }

  //You'll need to fill this out for your own server MySQL for it
  //To work on your machine
  private static String username;
  private static String password;
  private static String url;
  private static Connection dbcon;

  public static void main(String[] args)
  {
    try
    {
      // FILL THESE OUT !!!
      username = "";
      password = "";
      String url = "";

      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      dbcon = DriverManager.getConnection(url, username, password);
      dbcon.setAutoCommit(false);

      //Test your written JDBC methods bellow here.
      //Make sure to add some test data in the database or the ResultSets won't
      //have anything in them!
      Account test = findAccount("fraudulentEmail@hotmail.com");
      System.out.println(test.account_name);
      if(findAccount("gaghksagkhj") == null)
        System.out.println("Invalid email!");
      if(findAccount("DROP TABLE") == null)
        System.out.println("SQL Error!");
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  //Database Query Methods begin here
  //(try to follow comment style of findAccount method)
  //----------------------------------------------------------------------------

  /**
  * findAccount searches through account table to find matching tuple
  * with given email
  *
  * @param email the email we use to search the table for the tuple
  * @return      Account object with all data associated with account tuple,
  *              EXCEPT PASSWORD, returns null otherwise
  *
  *
  */
  public static Account findAccount(String email)
  {
    try
    {
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM accounts WHERE account_email= ?");
      st.setString(1, email);

      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      //parsing data from account table into relevenat fields into Account object
      //EXCEPT PASSWORD
      Account retrievedAccount = new Account();
      retrievedAccount.account_email = email;
      retrievedAccount.account_name = result.getString("account_name");
      retrievedAccount.img_path = result.getString("img_proof");
      if(result.getString("type").equals("Investor"))
        retrievedAccount.accountType = Type.INVESTOR;
      else
        retrievedAccount.accountType = Type.BUSINESS;

      if(result.getInt("verification") == 0)
        retrievedAccount.isVerified = false;
      else
        retrievedAccount.isVerified = true;

      st.close();
      return retrievedAccount;
    }
    catch(SQLException e1)
    {
      while (e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return null;
    }
  }
  private static class Account //Might make this into a seperate file later, but for
                        //for now ill keep it as a private class
  {
    public String account_email;
    public String account_name;
    public String account_password;
    public boolean isVerified;
    public String img_path;
    public Type accountType;

  }
}
