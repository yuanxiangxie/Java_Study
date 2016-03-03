package MyPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import MyEncrypt.MD5;

interface Parameter
{
	final String uims_CheckURL = "http://uims.jlu.edu.cn/ntms/j_spring_security_check";
	final String uims_SnatchURL = "";	// I do not know the web page at now
	final String uims_ViweSource = "http://uims.jlu.edu.cn/ntms/service/res.do";
	final String uims_ViweMailBox = "http://uims.jlu.edu.cn/ntms/siteMessages/get-message-in-box.do";
	final String uims_StudentInfo = "http://uims.jlu.edu.cn/ntms/action/getCurrentUserInfo.do";
	
	final String Proxy_Addr = "127.0.0.1";
	final int Proxy_Port = 59999;
}

public class Uims implements Parameter
{
	private MD5 M;
	private String UserName;
	private String Password;
	private String Param;
	private String Cookies;
	
	public Uims()
	{
		M = new MD5();
	}
	public Uims(String UserName, String Password)
	{
		M = new MD5();
		this.UserName = UserName;
		this.Password =	M.md5("UIMS" + UserName + Password); 
	}
	
	public void setInfo(String UserName, String Password)
	{
		this.UserName = UserName;
		this.Password = M.md5("UIMS" + UserName + Password);
	}
	public boolean loginUims()
	{
		boolean isLogin = false;
		Param = "j_username=" + UserName + "&j_password=" + Password;
		
		ProxySelector.setDefault( new ProxySelector()
		{

			public void connectFailed(URI uri, SocketAddress sa, IOException ioe)
			{
				System.out.println("无法连接到指定的代理服务器!");
			}
			
			public List<Proxy> select(URI uri)
			{
				List<Proxy> result = new ArrayList<>();
				result.add( new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Proxy_Addr, Proxy_Port)));
				return result;
			}

		});
		try
		{
			URL checkURL = new URL(uims_CheckURL);
			//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Proxy_Addr, Proxy_Port));
			HttpURLConnection httpCheckURL = (HttpURLConnection)checkURL.openConnection();
			httpCheckURL.setDoOutput(true);
			httpCheckURL.setDoInput(true);
			httpCheckURL.setInstanceFollowRedirects(false);
			httpCheckURL.setUseCaches(false);
			httpCheckURL.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpCheckURL.setRequestProperty("Connection", "keep-alive");
			httpCheckURL.setRequestProperty("Content-Length", Integer.toString(Param.length()));
			
			PrintWriter out = new PrintWriter(httpCheckURL.getOutputStream());
			out.print(Param);
			out.flush();
			out.close();
			
			Cookies = httpCheckURL.getHeaderField("Set-Cookie");
			
			URL infoURL = new URL(uims_StudentInfo);
			HttpURLConnection httpInfoURL = (HttpURLConnection) infoURL.openConnection();
			if(Cookies != null)
			{
				httpInfoURL.setRequestProperty("Cookie", Cookies);
			}
			httpInfoURL.connect();
			
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(httpInfoURL.getInputStream()));
			String buffer = "";
			String line;
			while((line = bufferedReader.readLine()) != null)
			{
				buffer += line;
			}
			
			if(buffer.contains("userId"))
			{
				isLogin = true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return isLogin;
		}
	}
	
	public static void main(String[] args)
	{
		Uims U = new Uims("53130215", "950301");
		System.out.println(U.loginUims());
	}
}