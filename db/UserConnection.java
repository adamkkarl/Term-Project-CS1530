import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class UserConnection
{
private String businessEmail;
private String investorEmail;
private String date;


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

public void setDate(){
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	LocalDateTime now = LocalDateTime.now();
	date = dtf.format(now).toString();
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







}