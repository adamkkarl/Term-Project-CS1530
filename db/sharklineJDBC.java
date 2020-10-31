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
      username = "root";
      password = "@Junotheroman6";
      String url = "jdbc:mysql://localhost/sharklinedb";

      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      dbcon = DriverManager.getConnection(url, username, password);
      dbcon.setAutoCommit(false);

      //Test your written JDBC methods bellow here.
      //Make sure to add some test data in the database or the ResultSets won't
      //have anything in them!
      Account test = findAccount("fraudulentEmail@hotmail.com");
      System.out.println(test.getName());
      if(findAccount("gaghksagkhj") == null)
        System.out.println("Invalid email!");
      if(findAccount("DROP TABLE") == null)
        System.out.println("SQL Error!");

      if(verifyAccount("Microsoft@ms.com"))
        System.out.println("Microsoft verified!");
      else
        System.out.println("Account already verified or no account found!");

      if(addBusinessAccount(findAccount("Microsoft@ms.com"), Industry.SOFTWARE_TECH))
        System.out.println("Microsoft added to business accounts!");
      else
        System.out.println("Account not added!");
      if(login("googleHQ@gmail.com", "myPassword") == 2)
        System.out.println("Google account login!");
      else
        System.out.println("Google account login fail!");

      Account addTest = new Account("fakeemail@fake.com", "fake",
                              "fakepw", false, "fake/fake;", Type.BUSINESS);
      if(addAccount(addTest))
        System.out.println("test added!");

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
  * login method searches through database and matches email and password
  * to entry in accounts table, returning integer based on result
  *
  * @param email the email we use to search the table for the tuple
  * @param password the password for the account, must be matched
  *
  * @return -1 for account not found (password incorrect, email not found, etc)
  *         0 for account found but not verified, 1 for investor account found,
  *         2 for business account found
  */
  public static int login(String email, String password)
  {
    try
    {
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM accounts" +
      " WHERE account_email = ? AND account_password = ?");
      st.setString(1, email);
      st.setString(2, password);

      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return -1;

      if(result.getInt("verification") == 0)
        return 0;

      if(result.getString("type").equals("Investor"))
        return 1;
      else
        return 2;
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return -1;
    }
  }

  /**
  * addAccount method inserts the created account into the database
  *
  * @param account includes all data necessary to create an account
  *
  * @return true if sucessfully added, false if otherwise
  *
  */
  public static boolean addAccount(Account account)
  {
    try
    {
      boolean isAdded = false;
      PreparedStatement st = dbcon.prepareStatement("INSERT INTO accounts"
      + "VALUES(?, ?, ?, ?, ?, ?)");
      st.setString(1, account.getEmail());
      st.setString(2, account.getPassword());
      st.setString(3, account.getName());

      if(account.getType() == Type.INVESTOR)
        st.setString(4, "Investor");
      else
        st.setString(4, "Business");

      if(account.checkVerified())
        st.setInt(5, 1);
      else
        st.setInt(5, 0);

      st.setString(6, account.getImgPath());

      if(st.executeUpdate() >= 1)
        isAdded = true;

      dbcon.commit();
      st.close();

      return isAdded;
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return false;
    }
  }

  /**
  * findAccount searches through account table to find matching tuple
  * with given email
  *
  * @param email the email we use to search the table for the tuple
  *
  * @return      Account object with all data associated with account tuple,
  *              EXCEPT PASSWORD, returns null otherwise
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
      retrievedAccount.setEmail(email);
      retrievedAccount.setName(result.getString("account_name"));
      retrievedAccount.setImgPath(result.getString("img_proof"));
      if(result.getString("type").equals("Investor"))
        retrievedAccount.setType(Type.INVESTOR);
      else
        retrievedAccount.setType(Type.BUSINESS);

      if(result.getInt("verification") == 1)
        retrievedAccount.verify();

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
  /***
  * verifyAccount searches through the database to update account with given email
  * to set verified attribute accounts table to 1 (or true)
  *
  * @param email the email we use to search the database to update verification
  * @return true if update successful, false if otherwise
  *
  */
  public static boolean verifyAccount(String email)
  {
    try
    {
      boolean isUpdated = false;
      PreparedStatement st =
      dbcon.prepareStatement("UPDATE accounts SET verification = 1 WHERE account_email = ? AND verification = 0");
      st.setString(1, email);
      //int isUpdated returns number of rows affected, although it should only
      //ever affect one row (as email is primary key so we cant have repeats)
      if(st.executeUpdate() >= 1);
        isUpdated = true;

      dbcon.commit();
      st.close();
      return isUpdated;
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return false;
    }
  }
  /**
  * addBusinessAccount adds an account which has been verified into the
  * business_accounts table. To update the other attributes, call
  * updateBusinessAccount()
  *
  * @param account the account which has information about business account
  *                (use findAccount method to get account)
  * @param industry the industry sector the account is in (see enum Industry)
  * @return true if account is verified, the email and name are unique, and
  *         argument given for industry is valid, false if otherwise
  */
  public static boolean addBusinessAccount(Account account, Industry industry)
  {
    try
    {
      boolean isAdded = false;
      if(!account.checkVerified())
        return isAdded;

      PreparedStatement st =
      dbcon.prepareStatement("INSERT INTO business_accounts VALUES" +
                            "(?, ?, NULL, NULL, NULL, NULL, NULL," +
                            "NULL, NULL, NULL, NULL, ?)");
      st.setString(1, account.getEmail());
      st.setString(2, account.getName());
      if(setIndustry(st, industry, 3) == null)
        return isAdded;

      if(st.executeUpdate() >= 1)
        isAdded = true;

      dbcon.commit();
      st.close();
      return isAdded;
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return false;
    }
  }
  public static boolean updateBusinessAccount(Business account)
  {
    return false;
  }
  public static boolean addInvestorAccount(Account account)
  {
    return false;
  }
  public static boolean updatedInvestorAccount(Investor account)
  {
    return false;
  }
  /**
  * setIndustry is a helper method designed to streamline updating industry attribute
  * in business_accounts table, not for use in queries
  *
  * @param st the PreparedStatement update which we will update
  * @param industry the enum Industry (see enum Industry above)
  * @param pos the position in the PreparedStatement we would like to inject to
  *
  * @return the PreparedStatement st with industry set at position pos, null
  *         if otherwise
  */
  private static PreparedStatement setIndustry(PreparedStatement st, Industry industry, int pos)
  {
    try
    {
      switch(industry)
      {
        case INDUSTRIAL:
          st.setString(pos, "Industrial");
          break;
        case HEALTH:
          st.setString(pos, "Health");
          break;
        case SOFTWARE_TECH:
          st.setString(pos, "Software/Tech");
          break;
        case ENTERTAINMENT:
          st.setString(pos, "Entertainment");
          break;
        case FOOD:
          st.setString(pos, "Food");
          break;
        case FINANCE:
          st.setString(pos, "Finance");
          break;
        case MARKETING:
          st.setString(pos, "Marketing");
          break;
        case AUTOMOTIVE:
          st.setString(pos, "Automotive");
          break;
        case EDUCATION:
          st.setString(pos, "Education");
          break;
        case LAW:
          st.setString(pos, "Law");
          break;
        case HOTEL:
          st.setString(pos, "Hotel");
          break;
        case TRAVEL:
          st.setString(pos, "Travel");
          break;
        case ENERGY:
          st.setString(pos, "Energy");
          break;
        case ENVIRONMENT:
          st.setString(pos, "Environment");
          break;
        case TRANSPORTATION:
          st.setString(pos, "Transportation");
          break;
        default:
          st.setString(pos, "Other");
      }
    }
    catch(SQLException e1)
    {
      while(e1 != null)
      {
        System.out.println("Message = " + e1.getMessage());
        System.out.println("SQLErrorCode = " + e1.getErrorCode());
        System.out.println("SQLState = " + e1.getSQLState());

        e1 = e1.getNextException();
      }
      return null;
    }
    return st;
  }
}
