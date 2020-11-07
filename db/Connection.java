public class Connection
{
private String businessEmail;
private String investorEmail;
private String connectionID;



public Connection(){

}

public Connection(String businessEmail, String investorEmail,
	String connectionID)
{
	this.businessEmail = businessEmail;
	this.investorEmail = investorEmail;
	this.connectionID = connectionID;
}

public String getBusinessEmail()
{
	return businessEmail;
}
public String getInvestorEmail()
{
	return investorEmail;
}
/*public String getConnectionID()
{
	return connection_id;
}
*/

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
/*public boolean setConnectionID()
{
	
}

*/




}