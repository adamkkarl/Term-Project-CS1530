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
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  //Database Query Methods begin here
  //----------------------------------------------------------------------------
  public static Account findAccount(String name, String email) //WIP
  {
    PreparedStatement st = dbcon.prepareStatement("SELECT * FROM ");
  }
  private class Account //Might make this into a seperate file later, but for
                        //for now ill keep it as a private class
  {
    public String account_email;
    public String account_name;
    public String account_password;
    public boolean isVerified;
    public String img_path;
    public enum type
    {
      INVESTOR,
      BUSINESS;
    }
  }
}
