package es.deusto.ingenieria.sd.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class process the request of each client as a separated Thread.
 */
public class FacebookService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	private static Map<String, String> users;

	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			FacebookService.users = new HashMap<String, String>();
			this.initializeUsers();
			this.start();
		} catch (IOException e) {
			System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		System.out.println("run");
		// Facebook server
		try {
			// Read request from the client
			String email = this.in.readUTF();
			System.out.println("email : " + email);
			String password = this.in.readUTF();
			System.out.println("password: " + password);
			System.out.println("   - FacebookService - Received data from '" + tcpSocket.getInetAddress().getHostAddress()
					+ ":" + tcpSocket.getPort() + "' -> '" + email + "'");
			
			

			// Send response to the client
			this.out.writeBoolean(checkIfExists(email));
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":"
					+ tcpSocket.getPort() + "' -> '" + checkIfExists(email) + "'");
			
		} catch (EOFException e) {
			System.err.println("   # FacebookService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}

	public static Map<String, String> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, String> users) {
		FacebookService.users = users;
	}

    public boolean checkIfExists(String email)
    {
    	return users.containsKey(email);
    }
    
    public void initializeUsers()   
    {        
    	User u1 = new User("hola", "hola@deusto.es");     
    	users.put(u1.getEmail(), u1.getPassword());    
    	
    	User u2 = new User("joselu", "joselu");     
    	users.put(u2.getEmail(), u2.getPassword());   
    }
    
}