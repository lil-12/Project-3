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

	@BeforeEach
	void before() {
		try {
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
	
	@Test
	void testAdmin() {
		outgoing.println("password");
		outgoing.println("new");
		outgoing.flush();
		String str="";
		try {
			str = incoming.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(str.equals("ADMIN"));
	
	}
	
	@Test
	void testAdminWrong() {
		outgoing.println("aaaaaa");
		outgoing.println("new");
		outgoing.flush();
		String str="";
		try {
			str = incoming.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(str.equals("Error: wrong password."));
	
	}

}
