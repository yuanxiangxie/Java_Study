package Encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 
{
	public String Encode(String Content)
	{
		if(Content == null)
			return null;
		
		byte[] encodeArray = null;
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] byteArray = Content.getBytes();
			messageDigest.update(byteArray);
			encodeArray = messageDigest.digest();
			
		}catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return ByteToString(encodeArray);
	}
	
	private String ByteToString(byte[] encodeArray)
	{
		char[] HexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		char[] encodeString = new char[encodeArray.length * 2];
		
		int index = 0;
		
		for(byte b : encodeArray)
		{
			encodeString[index++] = HexArray[b>>>4 & 0xf];
			encodeString[index++] = HexArray[b & 0xf];
		}
		
		return new String(encodeString);
	}

}