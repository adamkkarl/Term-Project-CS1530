import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ChatLog
{
	private String businessEmail;
	private String investorEmail;
	private String dateTime;
	private int sender;
	private String message;
	

	public ChatLog()
	{

	}

	public String getBusinessEmail()
	{
		return businessEmail;
	}
	public String getInvestorEmail()
	{
		return investorEmail;
	}
	public String getDateTime()
	{
		return dateTime;
	}
	public int getSender()
	{
		return sender;
	}
	public String getMessage()
	{
		return message;
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

	public void setDateTime()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dateTime = dtf.format(now).toString();
	}
	public boolean setSender(Account account)
	{
		
		if(Type.INVESTOR == account.getType())
		{
			sender = 1;
		}else if(Type.BUSINESS == account.getType())
		{
			sender = 0;
		}else
		{
			return false;
		}
		return true;
	}
	public boolean setMessage(String m)
	{
		message = m;
		if(m == null || m == "")
			return false;
		return message.equals(m);
		
	}








}