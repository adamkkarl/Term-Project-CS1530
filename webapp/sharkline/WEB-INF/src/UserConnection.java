import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class UserConnection
{
private String businessEmail;
private String investorEmail;
private String date;
private int connectionID;
private int connected;
private int sender;


public UserConnection(){

}

public UserConnection(String businessEmail, String investorEmail)
{
	this.businessEmail = businessEmail;
	this.investorEmail = investorEmail;
	setDate();
	date = getDate();
	System.out.println("date");
}

public String getBusinessEmail()
{
	return businessEmail;
}
public String getInvestorEmail()
{
	return investorEmail;
}
public int getConnectionID()
{
	return connectionID;
}
public boolean setDate(String newDate){
	date = newDate;
	if(newDate == null)
		return false;

	return date.equals(newDate);
}
public boolean setDate(){
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDateTime now = LocalDateTime.now();
	date = dtf.format(now).toString();
	return true;
}
public String getDate(){
	return date;
}

public boolean setBusinessEmail(String email)
{
	businessEmail = email;
	if(email == null)
		return false;
	return businessEmail.equals(email);
}
public boolean setInvestorEmail(String email)
{
	investorEmail = email;
	if(email == null)
		return false;
	return investorEmail.equals(email);
}
public void setConnectionID(int conID){
	connectionID = conID;
}

public int getConnected()
{
	return connected;
}
public boolean setConnected(int con){
	connected = con;
	return connected == con;
}

public int getSender()
{
	return sender;
}
public boolean setSender(int s){
	sender = s;
	return sender == s;
}



}
