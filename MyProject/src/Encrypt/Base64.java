package Encrypt;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Base64 
{
	public String Encode(String Content)
	{
		if(Content == null)
			return null;
		
		String encodeContent = " ";
		try
		{
			byte[] byteArray = Content.getBytes();
			BASE64Encoder base64 = new BASE64Encoder();
			encodeContent = base64.encode(byteArray);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return encodeContent;
	}
	
	public String Decode(String Content)
	{
		if(Content == null)
			return null;
		
		byte[] decodeContent = null;
		
		try
		{
			BASE64Decoder base64 = new BASE64Decoder();
			decodeContent = base64.decodeBuffer(Content);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new String(decodeContent);
	}
	
}