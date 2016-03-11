package Information;

import Encrypt.MD5;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Parameter
{
	final String From = "yuanxiangboy@163.com";
	final String Passwd  = "YXY19950301";
	
	final String Subject = "A Letter From VPN";
	final String Content = "Please do not reply to this email.  It is generated automatically, and replies will not reach a human reader. Your code is: ";
	
	final String regex = "^[\\w-_]+@([\\w-_]+\\.)+\\w+$";
	final Pattern pattern = Pattern.compile(regex);
	
	final String EncodeMAC = new MacAddress().getMacAddress();
}

public class Mail implements Parameter
{
	private String To;
	
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
	
	public String getMail()
	{
		return "From:" + From + "\r\n" + "To:" + "\r\n" + "Subject:" + Subject + "\r\n\r\n" + Content + EncodeMAC + "\r\n";
	}
	
	public String getFrom()
	{
		return From;
	}
	
	public String getTo()
	{
		return To;
	}
	
	public String geEncodeMAC()
	{
		return EncodeMAC;
	}
}

class MacAddress
{
	public String getMacAddress()
	{
		String result = "";
		
		try
		{
			InetAddress inet = InetAddress.getLocalHost();
			byte[] macAddress = NetworkInterface.getByInetAddress(inet).getHardwareAddress();
			
			char[] encodeArray = new char[ macAddress.length * 2];
			
			char[] HexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
					'b', 'c', 'd', 'e', 'f'};
			
			int i = 0;
			
			for(byte b : macAddress)
			{
				encodeArray[i++] = HexArray[ b>>>4 & 0xf ];
				encodeArray[i++] = HexArray[ b & 0xf ];
			}
			
			result = new String(encodeArray);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new MD5().Encode(result);
	}
}