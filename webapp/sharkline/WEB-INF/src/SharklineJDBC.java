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
//Test
public class SharklineJDBC
{
  //You'll need to fill this out for your own server MySQL for it
  //To work on your machine

  private Connection dbcon;

  public SharklineJDBC()
  {
    try
    {
      String username = "root";
      String password = "root";
      String url = "jdbc:mysql://localhost/sharklinedb";

      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      dbcon = DriverManager.getConnection(url, username, password);
      dbcon.setAutoCommit(false);
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
  *         0 for account found but not verified, 1 for investor account found AND verified,
  *         2 for business account found AND verified
  */
  public int login(String email, String password)
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
  public boolean addAccount(Account account)
  {
    try
    {
      boolean isAdded = false;
      PreparedStatement st = dbcon.prepareStatement("INSERT INTO accounts"
      + " VALUES(?, ?, ?, ?, ?, ?)");
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
  public Account findAccount(String email)
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

  public Account findAccountByName(String name)
  {
    try
    {
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM accounts WHERE account_name= ?");
      st.setString(1, name);

      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      //parsing data from account table into relevenat fields into Account object
      //EXCEPT PASSWORD
      Account retrievedAccount = new Account();
      retrievedAccount.setEmail(result.getString("account_email"));
      retrievedAccount.setName(name);
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
  public boolean verifyAccount(String email)
  {
    try
    {
      boolean isUpdated = false;
      PreparedStatement st =
      dbcon.prepareStatement("UPDATE accounts SET verification = 1 WHERE account_email = ? AND verification = 0");
      st.setString(1, email);
      //int isUpdated returns number of rows affected, although it should only
      //ever affect one row (as email is primary key so we cant have repeats)
      if(st.executeUpdate() >= 1)
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
  public boolean addBusinessAccount(Account account, Industry industry)
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

   /**
  * updateBusinessAccount updates an account(with secondary attributes) which has been verified into the
  * business_accounts table.
  *
  * @param account the account which has information about business account
  *                (use findAccount method to get account)
  * @return true if account is verified, the email and name are unique, and
  *        false if otherwise
  */
  public boolean updateBusinessAccount(Business account)
  {
    try
    {
      boolean isAdded = false;

      PreparedStatement st =
      dbcon.prepareStatement("INSERT INTO business_accounts VALUES" +
                            "(?, ?, ?, ?, ?, ?, ?," +
                            "?, ?, ?, ?, ?)");


      st.setString(1,account.getBusinessEmail());
      st.setString(2, account.getBusinessName());
      st.setString(3, account.getDescription());
      st.setString(4, account.getBusinessAbstract());
      st.setString(5, account.getLogoPath());
      setSize(st, account.getSize(), 6);
      st.setInt(7, account.getYear());
      st.setInt(8, account.getInvestmentAsk());
      st.setInt(9, account.getEquityOffer());
      st.setString(10, account.getWebsite());
      st.setString(11, account.getCeoName());
      setIndustry(st, account.getBusinessIndustry(), 12);

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
  * addInvestorAccount adds an account which has been verified into the
  * business_accounts table. To update the other attributes, call
  * updateInvestorAccount()
  *
  * @param account the account which has information about investor account
  *                (use findAccount method to get account)
  * @return true if account is verified, the email and name are unique, and
  *         argument given for industry is valid, false if otherwise
*/
  public boolean addInvestorAccount(Account account)
  {
    try
    {
      boolean isAdded = false;
      if(!account.checkVerified())
        return isAdded;

      PreparedStatement st =
      dbcon.prepareStatement("INSERT INTO investor_accounts VALUES" +
                            "(?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL)");
      st.setString(1, account.getEmail());
      st.setString(2, account.getName());


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
  * updateInvestorAccount updates an account(with secondary attributes) which has been verified into the
  * investor_accounts table.
  *
  * @param account the account which has information about investor account
  *                (use findAccount method to get account)
  * @return true if account is verified, the email and name are unique, and
  *        false if otherwise
  */
  public boolean updateInvestorAccount(Investor account)
  {
    try
    {
       boolean isAdded = false;
       PreparedStatement st =
       dbcon.prepareStatement("UPDATE investor_accounts SET"+
                            " investor_email = ?, investor_name = ?, investor_description = ?,"+
                            " investor_abstract = ?, image = ?, investment_range_init = ?, investment_range_end = ?,"+
                            " website = ?, name_CEO = ? WHERE investor_email = ?");


      st.setString(1,account.getInvestorEmail());
      st.setString(2, account.getInvestorName());
      st.setString(3, account.getInvestorDescription());
      st.setString(4, account.getInvestorAbstract());
      st.setString(5, account.getImage());
      st.setInt(6, account.getInvestmentRangeInit());
      st.setInt(7, account.getInvestmentRangeEnd());
      st.setString(8, account.getWebsite());
      st.setString(9, account.getCeoName());
      st.setString(10, account.getInvestorEmail());

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
  * findInvestorAccountByName queries investor_accounts for an exact match
  * to investor_name
  *
  * @param String name the name of the investor
  *
  * @return null if no such investor account exists, otherwise return the investor object
  */
  public Investor findInvestorAccountByName(String name)
  {
    try
    {
      Investor returnAccount;
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM investor_accounts WHERE investor_name = ?");

      st.setString(1, name);
      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      returnAccount = new Investor();

      returnAccount.setInvestorEmail(result.getString("investor_email"));
      returnAccount.setInvestorName(result.getString("investor_name"));
      returnAccount.setInvestorDescription(result.getString("investor_description"));
      returnAccount.setInvestorAbstract(result.getString("investor_abstract"));
      returnAccount.setInvestmentRangeInit(result.getInt("investment_range_init"));
      returnAccount.setInvestmentRangeEnd(result.getInt("investment_range_end"));
      returnAccount.setWebsite(result.getString("website"));
      returnAccount.setCeoName(result.getString("name_CEO"));
      returnAccount.setImage(result.getString("image"));

      st.close();
      return returnAccount;
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

  public String getNameFromEmail(String email)
  {
    try
    {
      ArrayList<Integer> connectionIDs = new ArrayList<Integer>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM accounts WHERE account_email = ?");
      st.setString(1, email);
      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next())
      {
        String name = result.getString("account_name");
        st.close();
        return name;
      }
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
    return null;
  }

  /**
  * findInvestorAccountByEmail queries investor_accounts for an exact match
  * to investor_email
  *
  * @param String name the email to search
  *
  * @return null if no such investor account exists, otherwise return the investor object
  */
  public Investor findInvestorAccountByEmail(String email)
  {
    try
    {
      Investor returnAccount;
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM investor_accounts WHERE investor_email = ?");

      st.setString(1, email);
      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      returnAccount = new Investor();

      returnAccount.setInvestorEmail(result.getString("investor_email"));
      returnAccount.setInvestorName(result.getString("investor_name"));
      returnAccount.setInvestorDescription(result.getString("investor_description"));
      returnAccount.setInvestorAbstract(result.getString("investor_abstract"));
      returnAccount.setInvestmentRangeInit(result.getInt("investment_range_init"));
      returnAccount.setInvestmentRangeEnd(result.getInt("investment_range_end"));
      returnAccount.setWebsite(result.getString("website"));
      returnAccount.setCeoName(result.getString("name_CEO"));
      returnAccount.setImage(result.getString("image"));

      st.close();
      return returnAccount;
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

  /**
  * findInvestorsByAsk queries investor_accounts for any investor whos stated
  * investing range is within 10% of the asking price
  *
  * @param int ask, the investment amount
  *
  * @return null if no such investor account exists, otherwise return
  * an arraylist of all investor objects
  */
  public ArrayList<Investor> findInvestorsByAsk(int ask)
  {
    try
    {
      if(ask < 1000)
        return null;

      ArrayList<Investor> investors = new ArrayList<Investor>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM investor_accounts WHERE investment_range_init <= ?"
                           + " AND investment_range_end >= ?");
      st.setInt(1, (int) (ask * .9));
      st.setInt(2, (int) (ask * 1.1));

      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next())
      {
        Investor account = new Investor();

        account.setInvestorEmail(result.getString("investor_email"));
        account.setInvestorName(result.getString("investor_name"));
        account.setInvestorDescription(result.getString("investor_description"));
        account.setInvestorAbstract(result.getString("investor_abstract"));
        account.setInvestmentRangeInit(result.getInt("investment_range_init"));
        account.setInvestmentRangeEnd(result.getInt("investment_range_end"));
        account.setWebsite(result.getString("website"));
        account.setCeoName(result.getString("name_CEO"));
        account.setImage(result.getString("image"));

        investors.add(account);
      }
      st.close();
      if(investors.size() == 0)
        return null;

      return investors;
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

   /**
  * findInvestorsByLikeName queries investors_accounts for any investors with a
  * name that contains the given substring
  *
  * @param String name, the substring to search for
  *
  * @return null if no such investor account exists, otherwise return an array list
  * containing each matching investor
  */
  public ArrayList<Investor> findInvestorsByLikeName(String name)
  {
    try
    {
      ArrayList<Investor> investors = new ArrayList<Investor>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM investor_accounts WHERE investor_name LIKE ?");
      st.setString(1, "%" + name + "%");

      ResultSet result = st.executeQuery();
      while(result.next())
      {
        Investor account = new Investor();

        account.setInvestorEmail(result.getString("investor_email"));
        account.setInvestorName(result.getString("investor_name"));
        account.setInvestorAbstract(result.getString("investor_abstract"));
        account.setInvestorDescription(result.getString("investor_description"));
        account.setImage(result.getString("image"));
        account.setInvestmentRangeInit(result.getInt("investment_range_init"));
        account.setInvestmentRangeEnd(result.getInt("investment_range_end"));
        account.setWebsite(result.getString("website"));
        account.setCeoName(result.getString("name_CEO"));


        investors.add(account);
      }
      if(investors.size() == 0)
        return null;

      st.close();
      return investors;
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

  /**
  * findBusinessAccountByName searches by name of Business to locate
  * entry in database. Must be exact match.
  *
  * @param name the string of the name associated to the business
  *             account we are trying to find
  * @return Business account object if an account is found, null
  *         if otherwise
  *
  */
  public Business findBusinessAccountByName(String name)
  {
    try
    {
      Business returnAccount;
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM business_accounts WHERE business_name = ?");

      st.setString(1, name);
      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      returnAccount = new Business();

      returnAccount.setBusinessEmail(result.getString("business_email"));
      returnAccount.setBusinessName(result.getString("business_name"));
      returnAccount.setBusinessAbstract(result.getString("business_abstract"));
      returnAccount.setDescription(result.getString("business_description"));
      returnAccount.setLogoPath(result.getString("logo"));
      returnAccount.setSize(getSize(result.getString("size")));
      returnAccount.setYear(result.getInt("established"));
      returnAccount.setInvestmentAsk(result.getInt("investment_ask"));
      returnAccount.setEquityOffer(result.getInt("equity_offer"));
      returnAccount.setWebsite(result.getString("website"));
      returnAccount.setCeoName(result.getString("name_CEO"));
      returnAccount.setBusinessIndustry(getIndustry(result.getString("industry")));

      st.close();
      return returnAccount;
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

  /**
  * findBusinessAccountByEmail queries business_accounts for an exact email match
  *
  * @param String email, the email to search for
  *
  * @return null if no such investor account exists, otherwise return
  * the business object
  */
  public Business findBusinessAccountByEmail(String email)
  {
    try
    {
      Business returnAccount;
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM business_accounts WHERE business_email = ?");

      st.setString(1, email);
      ResultSet result = st.executeQuery();
      if(!(result.next()))
        return null;

      returnAccount = new Business();

      returnAccount.setBusinessEmail(result.getString("business_email"));
      returnAccount.setBusinessName(result.getString("business_name"));
      returnAccount.setBusinessAbstract(result.getString("business_abstract"));
      returnAccount.setDescription(result.getString("business_description"));
      returnAccount.setLogoPath(result.getString("logo"));
      returnAccount.setSize(getSize(result.getString("size")));
      returnAccount.setYear(result.getInt("established"));
      returnAccount.setInvestmentAsk(result.getInt("investment_ask"));
      returnAccount.setEquityOffer(result.getInt("equity_offer"));
      returnAccount.setWebsite(result.getString("website"));
      returnAccount.setCeoName(result.getString("name_CEO"));
      returnAccount.setBusinessIndustry(getIndustry(result.getString("industry")));

      st.close();
      return returnAccount;
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

  /**
  * findBusinessesByIndustry queries business_accounts for any businesses in
  * the given field
  *
  * @param Industry industry, the industry to search
  *
  * @return null if no such investor account exists, otherwise return an array list
  * containing each matching business
  */
  public ArrayList<Business> findBusinessesByIndustry(Industry industry)
  {
    try
    {
      ArrayList<Business> businesses = new ArrayList<Business>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM business_accounts WHERE industry = ?");
      st = setIndustry(st, industry, 1);

      ResultSet result = st.executeQuery();
      while(result.next())
      {
        Business account = new Business();

        account.setBusinessEmail(result.getString("business_email"));
        account.setBusinessName(result.getString("business_name"));
        account.setBusinessAbstract(result.getString("business_abstract"));
        account.setDescription(result.getString("business_description"));
        account.setLogoPath(result.getString("logo"));
        account.setSize(getSize(result.getString("size")));
        account.setYear(result.getInt("established"));
        account.setInvestmentAsk(result.getInt("investment_ask"));
        account.setEquityOffer(result.getInt("equity_offer"));
        account.setWebsite(result.getString("website"));
        account.setCeoName(result.getString("name_CEO"));
        account.setBusinessIndustry(getIndustry(result.getString("industry")));

        businesses.add(account);
      }
      if(businesses.size() == 0)
        return null;

      st.close();
      return businesses;
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

  /**
  * findBusinessesByLikeName queries business_accounts for any businesses with a
  * name that contains the given substring
  *
  * @param String name, the substring to search for
  *
  * @return null if no such investor account exists, otherwise return an array list
  * containing each matching business
  */
  public ArrayList<Business> findBusinessesByLikeName(String name)
  {
    try
    {
      ArrayList<Business> businesses = new ArrayList<Business>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM business_accounts WHERE business_name LIKE ?");
      st.setString(1, "%" + name + "%");

      ResultSet result = st.executeQuery();
      while(result.next())
      {
        Business account = new Business();

        account.setBusinessEmail(result.getString("business_email"));
        account.setBusinessName(result.getString("business_name"));
        account.setBusinessAbstract(result.getString("business_abstract"));
        account.setDescription(result.getString("business_description"));
        account.setLogoPath(result.getString("logo"));
        account.setSize(getSize(result.getString("size")));
        account.setYear(result.getInt("established"));
        account.setInvestmentAsk(result.getInt("investment_ask"));
        account.setEquityOffer(result.getInt("equity_offer"));
        account.setWebsite(result.getString("website"));
        account.setCeoName(result.getString("name_CEO"));
        account.setBusinessIndustry(getIndustry(result.getString("industry")));

        businesses.add(account);
      }
      if(businesses.size() == 0)
        return null;

      st.close();
      return businesses;
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

 /**
  * addConnection adds both business and investor emails along with the date connection was made as an
  * entry in the database
  *
  * @param userCon a UserConnection object
  *
  * @return true object if a connection is added, null
  *         if otherwise
  *
  */
  public boolean addConnection(UserConnection userCon)
  {
    try
    {
       boolean isAdded = false;
       PreparedStatement st =
       dbcon.prepareStatement("INSERT INTO account_connections VALUES" +
                            " (?, ?, ?, ?, ?, ?)");


      st.setString(1, userCon.getBusinessEmail());
      st.setString(2, userCon.getInvestorEmail());
      st.setInt(3, userCon.getConnectionID());
      st.setString(4, userCon.getDate());
      st.setInt(5, userCon.getConnected());
      st.setInt(6, userCon.getSender());


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
  * removeConnection retrieves the connection id with business and investor emails and
  *  removes the corresponding row in the database
  *
  * @param userCon a UserConnection object
  *
  * @return true object if a connection is found and removed, null
  *         if otherwise
  *
  */
  public boolean removeConnection(UserConnection userCon)
  {
    try
    {
       boolean isRemoved = false;
       PreparedStatement pr = dbcon.prepareStatement("SELECT * FROM account_connections WHERE business_email = ? AND investor_email = ?");
       pr.setString(1, userCon.getBusinessEmail());
       pr.setString(2, userCon.getInvestorEmail());

       ResultSet result = pr.executeQuery();
       if(!(result.next()))
        return false;

       int connectionID = result.getInt("connection_id");
       pr.close();
       userCon.setConnectionID(connectionID);

       PreparedStatement st =
       dbcon.prepareStatement("DELETE FROM account_connections WHERE connection_ID = ?");
       st.setInt(1, connectionID);


      if(st.executeUpdate() >= 1)
        isRemoved = true;

      dbcon.commit();
      st.close();
      return isRemoved;

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
  * storeMessageInfo adds connection id, date & time, determines sender, and stores message contents in db
  *
  * @param connection_id, sender, message
  *
  * @return true if connection is found and information is stored, false
  *         if otherwise
  */
  public boolean storeMessage(int connection_id, int sender, String message)
  {
    try
    {
      boolean isAdded = false;
      PreparedStatement st = dbcon.prepareStatement("INSERT INTO chat_log"
      + " VALUES(?, ?, ?, ?)");

      st.setInt(1, connection_id);

      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy'T'HH:mm:ss");
  		LocalDateTime now = LocalDateTime.now();
  		String datetime_sent = dtf.format(now).toString();
      st.setString(2, datetime_sent);

      st.setInt(3, sender);
      st.setString(4, message);

      if(st.executeUpdate() >= 1)
        isAdded = true;

      dbcon.commit();
      st.close();

      return isAdded;


    } catch(SQLException e1)
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

  public ArrayList<UserConnection> getConnectionsByEmail(String email)
  {
    try
    {
      ArrayList<UserConnection> connections = new ArrayList<UserConnection>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM account_connections WHERE business_email = ? OR investor_email = ?");

      st.setString(1, email);
      st.setString(2, email);

      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next())
      {
        String businessEmail = result.getString("business_email");
        String investorEmail = result.getString("investor_email");
        String date = result.getString("date_connected");
        int id = result.getInt("connection_id");
        int connected = result.getInt("connected");
        int sender = result.getInt("sender");

        UserConnection userConnection = new UserConnection(businessEmail,
                    investorEmail, date, id, connected, sender);

        connections.add(userConnection);
      }

      st.close();
      return connections;
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
  /**
  * getConnectionIDsByEmail queries account_connections for an exact email match and
  * returns all connection_ids for that email
  *
  * @param String email, the email to search for
  *
  * @return null if no connections exists, otherwise return a list of connection_ids
  */
  public ArrayList<Integer> getConnectionIDsByEmail(String email)
  {
    try
    {
      ArrayList<Integer> connectionIDs = new ArrayList<Integer>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM account_connections WHERE business_email = ? OR investor_email = ?");

      st.setString(1, email);
      st.setString(2, email);
      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next())
      {
        int id = result.getInt("connection_id");
        connectionIDs.add(id);
      }

      st.close();
      return connectionIDs;
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

  /**
  * getChatLogByConnectionID queries chat_log for any messages for a certain connection_id
  * THE MOST RECENT MESSAGE IS LISTED FIRST
  *
  * @param connection_id, the connection to search for
  * @param numMessages, the number of messages to find
  *
  * @return null if no chats exist, otherwise return a list of chat_logs
  * the business object
  */
  public ArrayList<ChatLog> getChatLogByConnectionID(int connection_id)
  {
    try
    {
      ArrayList<ChatLog> chat_logs = new ArrayList<ChatLog>();
      PreparedStatement st =
      dbcon.prepareStatement("SELECT * FROM chat_log WHERE connection_id = ? ORDER BY datetime_sent ASC");

      st.setInt(1, connection_id);

      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next())
      {
        ChatLog c = new ChatLog(0, null, 0, null);
        c.setConnectionID(result.getInt("connection_id"));
        c.setDateTimeToString(result.getString("datetime_sent"));
        c.setSender(result.getInt("sender"));
        c.setMessage(result.getString("message"));

        chat_logs.add(c);
      }

      st.close();
      return chat_logs;
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

  /**
  * getChatLogByConnectionID queries chat_log for any messages for a certain email
  * THE MOST RECENT MESSAGE IS LISTED
  *
  * @param connection_id, the connection to search for
  *
  * @return null if no chats exist, otherwise return a ~-separated list of investor name, business name, and message
  */
  public ArrayList<String> getConversationPreviews(String email)
  {
    try
    {
      boolean isBusiness = false;

      ArrayList<String> info = new ArrayList<String>();

      PreparedStatement st =
      dbcon.prepareStatement("SELECT bus_name.account_name AS business_name, inv_name.account_name AS investor_name, tmp.message AS message " +
        "FROM account_connections a " +
        "JOIN (" +
        "	SELECT c.connection_id AS connection_id, c.message AS message " +
        "	FROM chat_log c " +
        "	LEFT JOIN chat_log d " +
        "	ON c.connection_id = d.connection_id AND c.datetime_sent < d.datetime_sent " +
        "	WHERE d.datetime_sent IS NULL" +
        ") tmp " +
        "ON a.connection_id = tmp.connection_id " +
        "JOIN accounts inv_name " +
        "ON a.investor_email = inv_name.account_email " +
        "JOIN accounts bus_name " +
        "ON a.business_email = bus_name.account_email " +
        "WHERE a.business_email = ? OR a.investor_email = ?");
      st.setString(1, email);
      st.setString(2, email);
      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next()) {
        String investor_business_message = "~".join(result.getString("investor_name"), result.getString("business_name"), result.getString("message"));
        info.add(investor_business_message);
      }

      st.close();
      return info;
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

  /**
  * getMostRecentMessage queries chat_log for any messages for a certain email
  * THE MOST RECENT MESSAGE IS LISTED for each commection
  *
  * @param connection_id, the connection to search for
  *
  * @return null if no chats exist, otherwise return a ~-separated list of investor name, business name, and message
  */
  public ArrayList<String> getMostRecentMessages(String inv_email, String bus_email)
  {
    try
    {
      boolean isBusiness = false;

      ArrayList<String> info = new ArrayList<String>();

      PreparedStatement st =
      dbcon.prepareStatement("SELECT bus_name.account_name AS business_name, inv_name.account_name AS investor_name, tmp.sender as sender, tmp.message AS message " +
        "FROM account_connections a " +
        "JOIN (" +
        "	SELECT c.connection_id AS connection_id, c.sender AS sender, c.message AS message " +
        "	FROM chat_log c " +
        "	LEFT JOIN chat_log d " +
        "	ON c.connection_id = d.connection_id AND c.datetime_sent < d.datetime_sent " +
        "	WHERE d.datetime_sent IS NULL" +
        ") tmp " +
        "ON a.connection_id = tmp.connection_id " +
        "JOIN accounts inv_name " +
        "ON a.investor_email = inv_name.account_email " +
        "JOIN accounts bus_name " +
        "ON a.business_email = bus_name.account_email " +
        "WHERE (a.business_email = ? OR a.investor_email = ?) " +
        "AND a.connected = 1");
      st.setString(1, bus_email);
      st.setString(2, inv_email);
      ResultSet result = st.executeQuery();

      if(!(result.next()))
        return null;

      while(result.next()) {
        String inv_nm = result.getString("investor_name");
        String bus_nm = result.getString("business_name");
        int sender = result.getInt("investor_name");
        String msg = result.getString("message");
        String s = "";
        if (sender == 1) {
          //sent by investor
          s = ": ".join(inv_nm, msg);
        } else {
          //sent by business
          s = ": ".join(bus_nm, msg);
        }
        info.add(s);
      }

      st.close();
      return info;
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
  private PreparedStatement setIndustry(PreparedStatement st, Industry industry, int pos)
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
        case RETAIL:
          st.setString(pos, "Retail");
          break;
        case FINANCE:
          st.setString(pos, "Finance");
          break;
        case MARKETING:
          st.setString(pos, "Marketing");
          break;
        case SALES:
          st.setString(pos, "Sales");
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

  /**
  * setSize is a helper method designed to streamline updating size attribute
  * in business_accounts table, not for use in queries
  *
  * @param st the PreparedStatement update which we will update
  * @param size the enum Size (see enum Size above)
  * @param pos the position in the PreparedStatement we would like to inject to
  *
  * @return the PreparedStatement st with size set at position pos, null
  *         if otherwise
  */
  private PreparedStatement setSize(PreparedStatement st, Size size, int pos)
  {
    try
    {
      switch(size)
      {
        case ONE_TO_TEN:
          st.setString(pos, "1-10");
          break;
        case ELEVEN_TO_THIRTY:
          st.setString(pos, "11-30");
          break;
        case THIRTYONE_TO_FIFTY:
          st.setString(pos, "31-50");
          break;
        case FIFTYONE_TO_ONEHUNDRED:
          st.setString(pos, "51-100");
          break;
        case ONEHUNDREDANDONE_TO_TWOHUNDRED:
          st.setString(pos, "101-200");
          break;
        case TWOHUNDREDPLUS:
          st.setString(pos, "200+");
          break;
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

  /**
  * getSize is a helper method designed to streamline retrieving the size attribute
  * from the business_accounts table, not for use in queries
  *
  * @param size the string that maps to an enum
  *
  * @return the enum Size
  */
  private Size getSize(String size)
  {
    Size returnSize;
    if(size == null)
      return null;

    if(size.equals("1-10"))
      returnSize = Size.ONE_TO_TEN;
    else if(size.equals("11-30"))
      returnSize = Size.ELEVEN_TO_THIRTY;
    else if(size.equals("31-50"))
      returnSize = Size.THIRTYONE_TO_FIFTY;
    else if(size.equals("51-100"))
      returnSize = Size.FIFTYONE_TO_ONEHUNDRED;
    else if(size.equals("101-200"))
      returnSize = Size.ONEHUNDREDANDONE_TO_TWOHUNDRED;
    else if(size.equals("200+"))
      returnSize = Size.TWOHUNDREDPLUS;
    else
      returnSize = null;

    return returnSize;
  }

  /**
  * getIndustry is a helper method designed to streamline retrieving the industry attribute
  * from the business_accounts table, not for use in queries
  *
  * @param industry the string that maps to an enum
  *
  * @return the enum Industry
  */
  private Industry getIndustry(String industry)
  {
    Industry returnIndustry;

    if(industry.equals("Industrial"))
      returnIndustry = Industry.INDUSTRIAL;
    else if(industry.equals("Health"))
      returnIndustry = Industry.HEALTH;
    else if(industry.equals("Software/Tech"))
      returnIndustry = Industry.SOFTWARE_TECH;
    else if(industry.equals("Entertainment"))
      returnIndustry = Industry.ENTERTAINMENT;
    else if(industry.equals("Food"))
      returnIndustry = Industry.FOOD;
    else if(industry.equals("Retail"))
      returnIndustry = Industry.RETAIL;
    else if(industry.equals("Finance"))
      returnIndustry = Industry.FINANCE;
    else if(industry.equals("Marketing"))
      returnIndustry = Industry.MARKETING;
    else if(industry.equals("Sales"))
      returnIndustry = Industry.SALES;
    else if(industry.equals("Automotive"))
      returnIndustry = Industry.AUTOMOTIVE;
    else if(industry.equals("Education"))
      returnIndustry = Industry.EDUCATION;
    else if(industry.equals("Law"))
      returnIndustry = Industry.LAW;
    else if(industry.equals("Hotel"))
      returnIndustry = Industry.HOTEL;
    else if(industry.equals("Travel"))
      returnIndustry = Industry.TRAVEL;
    else if(industry.equals("Energy"))
      returnIndustry = Industry.ENERGY;
    else if(industry.equals("Environment"))
      returnIndustry = Industry.ENVIRONMENT;
    else if(industry.equals("Transportation"))
      returnIndustry = Industry.TRANSPORTATION;
    else
      returnIndustry = Industry.OTHER;

    return returnIndustry;
  }
}
