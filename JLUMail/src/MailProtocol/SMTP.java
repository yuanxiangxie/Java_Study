package MailProtocol;

import Encrypt.Base64;
import MAIL.Mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

interface SMTP_INFO
{
	final String SMTP_Server = "smtp.163.com";
	final int SMTP_Port = 25;
	
	final String Email_Passwd = "YXY19950301";
}
public class SMTP implements SMTP_INFO
{
	private Socket smtp_socket = null;
	private BufferedReader bufferedReader = null;
	private PrintWriter printWriter = null;
	private Mail mail;
	
	public SMTP(Mail mail)
	{
		this.mail = mail;
		
		try
		{
			smtp_socket = new Socket(SMTP_Server, SMTP_Port);
			bufferedReader = new BufferedReader( new InputStreamReader(smtp_socket.getInputStream()));
			printWriter = new PrintWriter(smtp_socket.getOutputStream());
			
			String result = bufferedReader.readLine();
			System.out.println("Receive --> " + result);
			
			sendCommand("HELO " + new Mail().getFrom());
			sendCommand("AUTH LOGIN");
			sendCommand( new Base64().Encode(mail.getFrom()));
			sendCommand( new Base64().Encode(Email_Passwd));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean sendCommand(String Command)
	{
		boolean flag = false;
		
		try
		{
			printWriter.print(Command + "\r\n");
			printWriter.flush();
			System.out.println("Send --> " + Command);
			String result = bufferedReader.readLine();
			System.out.println("Receive --> " + result);
			
			if(result.substring(0, 3).equals("250"))
				flag = true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean sendMail()
	{
		try
		{
			sendCommand("MAIL FROM:<" + mail.getFrom() + ">");
			
			for(int i = 0; i < mail.getTo().size(); ++i)
				sendCommand("RCPT TO:<" + mail.getTo().get(i) + ">");
			
			sendCommand("DATA");
			printWriter.print(mail.getMail());
			sendCommand(".");
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void quitSMTP()
	{
		try
		{
			sendCommand("QUIT");
			smtp_socket.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		boolean flag = false;
		
		Mail mail = new Mail();
		mail.setTo("2427723725@qq.com");
		mail.setSubject("This is My Mail");
		mail.setContent("Hello World");
		SMTP sm = new SMTP(mail);
		if(sm.sendMail())
			flag = true;
		sm.quitSMTP();
		
		if(flag)
			System.out.println("\r\nThe process of send mail is end !!!\r\n" );
		else 
			System.out.println("\r\nSomething is error!!!\r\n");
	}
	
}
