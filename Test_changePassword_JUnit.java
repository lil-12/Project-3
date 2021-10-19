// Author: David Corrales Marco

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_changePassword_JUnit {
	
	static Socket connection;
	static PrintWriter outgoing;
	static BufferedReader incoming;

	//connect to the server a new time for each test
	@BeforeEach
	void before()
	{
		try 
		{
			connection = new Socket("localhost",52431);
			System.out.println("Listening on port: "+52431);
			outgoing = new PrintWriter(connection.getOutputStream());
			incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	//tests that change password works by making sure that the correct type of account is returned as a message when the appropriate strings are sent over
	@Test
	void testAdmin() 
	{
		outgoing.println("password");
		outgoing.println("new");
		outgoing.flush();
		String str="";
		try 
		{
			str = incoming.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		assert(str.equals("ADMIN"));
	
	}
	
	//makes sure that an error message is returned when the wrong current password is entered
	@Test
	void testAdminWrong() {
		outgoing.println("aaaaaa");
		outgoing.println("new");
		outgoing.flush();
		String str="";
		try 
		{
			str = incoming.readLine();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(str.equals("Error: wrong password."));
	}
}
