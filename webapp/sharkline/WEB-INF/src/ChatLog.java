import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ChatLog
{
	private int connection_id;
	private String datetime_sent;
	private int sender;
	private String message;


	public ChatLog(int connection_id, String datetime_sent, int sender, String message)
	{
		this.connection_id = connection_id;
		setDateTimeToNow();
		this.sender = sender;
		this.message = message;
	}

	public int getConnectionID()
	{
		return connection_id;
	}

	public boolean setConnectionID(int cid)
	{
		connection_id = cid;
		return connection_id == cid;
	}

	public String getDateTime()
	{
		return datetime_sent;
	}

	public boolean setDateTimeToNow()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		datetime_sent = dtf.format(now).toString();
		if(datetime_sent == null){
			return false;
		}
		return datetime_sent.equals(dtf.format(now).toString());
	}

	public boolean setDateTimeToString(String dt)
	{
		datetime_sent = dt;
		if(datetime_sent == null){
			return false;
		}
		return datetime_sent.equals(dt);
	}

	public int getSender()
	{
		return sender;
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

	public boolean setSender(int s)
	{
		sender = s;
		return sender == s;
	}

	public String getMessage()
	{
		return message;
	}

	public boolean setMessage(String m)
	{
		message = m;
		if(m == null || m == "")
			return false;
		return message.equals(m);
	}
}
