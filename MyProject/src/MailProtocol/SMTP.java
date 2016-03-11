package MailProtocol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Encrypt.Base64;
import Information.Mail;

interface Parameter
{
	final String Server = "smtp.163.com";
	final int Port = 25;
	
	final String UserName = new Mail().getFrom();
	final String Passwd = "YXY19950301";
	
}
public class SMTP implements Parameter
{
	private Socket socket = null;
	private BufferedReader bufferedReader = null;
	private PrintWriter printWriter = null;
	
	public SMTP()
	{
		try
		{
			socket = new Socket(Server, Port);
			
			bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream());
			
			String result = bufferedReader.readLine();
			System.out.println(result);
			
			sendCommand("HELO " + UserName);
			sendCommand("AUTH LOGIN");
			sendCommand( new Base64().Encode(UserName));
			sendCommand( new Base64().Encode(Passwd));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public boolean sendMail(Mail mail)
	{
		boolean flag = false;
		
		try
		{
			sendCommand("MAIL FROM:<" + mail.getFrom() + ">");
			sendCommand("RCPT TO:<" + mail.getTo() + ">");
			sendCommand("DATA");
			printWriter.print(mail.getMail());
			if(sendCommand("."))
				flag = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}
	
	
	public boolean quitSMTP()
	{
		try
		{
			sendCommand("QUIT");
			System.out.println("SMTP has been closed");
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	private boolean sendCommand(String Command)
	{
		boolean flag = false;
		
		try
		{
			printWriter.print(Command + "\r\n");
			printWriter.flush();
			
			String result = bufferedReader.readLine();
			System.out.println(result);
			
			if(result.substring(0, 3).equals("250"))
				flag = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
}