package MAIL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail 
{
	private String From;
	private String To;
	private String Subject;
	private String Content;
	private final String regex = "^[\\w-_]+@([\\w-_]+\\.)+\\w+$";
	private final Pattern pattern = Pattern.compile(regex);
	
	public boolean setFrom(String From)
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
	
	public boolean setTo(String To)
	{
		Matcher matcher = pattern.matcher(To);
		if(matcher.matches())
		{
			this.To = To;
			return true;
		}
		else
			return false;
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
	
	public String getTo()
	{
		return To;
	}
	
	public String getSubject()
	{
		return Subject;
	}
	
	public String getContent()
	{
		return Content;
	}
	
	public static void main(String[] args)
	{
		Mail M = new Mail();
		System.out.println(M.setFrom("yuanxiangboy@@163.com"));
	}
}
