package MAIL;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Mail 
{
	private String From = "yuanxiangboy@163.com";
	
	private Vector<String> To;
	private String Subject = "";
	private String Content = "";
	
	private final String regex = "^[\\w-_]+@([\\w-_]+\\.)+\\w+$";
	private final Pattern pattern = Pattern.compile(regex);
	
	public Mail()
	{
		To = new Vector<String>();
	}
	
	
	private boolean setFrom(String From)
	{
		Matcher matcher = pattern.matcher(From);
		if(matcher.matches())
		{
			this.From = From;
			return true;
		}
		else
			return false;
	}
	
	
	public Vector<String> setTo(String To)
	{
		Vector<String> to = new Vector<String>();
		String[] to_address = To.split(";");
		for(int i = 0; i < to_address.length; ++i)
		{
			Matcher matcher = pattern.matcher(to_address[i]);
			if(matcher.matches())
				this.To.addElement(to_address[i]);
			else
				to.addElement(to_address[i]);
		}
		
		return to;
	}
	
	
	public void setSubject(String Subject)
	{
		this.Subject = Subject;
	}
	
	public void setContent(String Content)
	{
		this.Content = Content;
	}
	
	
	public String getFrom()
	{
		return From;
	}
	
	
	public Vector<String> getTo()
	{
		return To;
	}
	
	
	private String getSubject()
	{
		return Subject;
	}
	
	
	private String getContent()
	{
		return Content;
	}
	
	public String getMail()
	{
		String ToWho = "";
		for(int i = 0; i < getTo().size(); ++i)
			ToWho += getTo().get(i) + ";";
			
		return "From:" + getFrom() + "\r\n" + "To:" + ToWho + "\r\n" + 
				"Subject:" + getSubject() + "\r\n\r\n" + getContent() + "\r\n";
	}
}
