// Author: David Corrales Marco

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Test_changePassword_Helper 
{

	public static void main (String args[]) 
	{
		
		try 
		{
			ArrayList<Account> accounts = new ArrayList<Account>();
			AdminAccount acc1 = new AdminAccount("admin", "password", accounts);//account that password is beng changed for
			
			for (int i = 0; i < 2; i++) 
			{
				ServerSocket serve = new ServerSocket(52431);
				Socket connection = serve.accept();
				System.out.println("accepted connection");
				
				PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
				BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
				
				StoreServer.changePassword(acc1, incoming, outgoing);//calls changePasswordScene for test to communicate with
				serve.close();
				connection.close();
			}	
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
