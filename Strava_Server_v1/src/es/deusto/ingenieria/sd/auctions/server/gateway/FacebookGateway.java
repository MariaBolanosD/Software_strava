package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

public class FacebookGateway implements IGateway{

	private String serverIP;
	private int serverPort;
	private boolean data;
	
	public boolean getUserByEmail(String email)
	{
		//args[0] = Server IP
		serverIP = "127.0.0.1";
		//args[1] = Server socket port
		serverPort = 8090;
		System.out.println("server port " + serverPort + " serverip: "+ serverIP);
		
		try (Socket tcpSocket = new Socket(serverIP, serverPort);
				 //Streams to send and receive information are created from the Socket
			     DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				 DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
				
				//Send request (a String) to the server
				out.writeUTF(email);
				out.writeUTF("nada");
				System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + email + "'");
				
				//Read response (a String) from the server
				data = in.readBoolean();			
				System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
			} catch (UnknownHostException e) {
				System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
			} catch (EOFException e) {
				System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
			}
		return data;
	}
	public boolean verifyPassword(String email, String password)
	{
		//args[0] = Server IP
		//serverIP = "127.0.0.1";
		//args[1] = Server socket port
		//serverPort = 8090;
		System.out.println("server port " + serverPort + " serverip: "+ serverIP);
		try (Socket tcpSocket = new Socket(serverIP, serverPort);
				 //Streams to send and receive information are created from the Socket
			     DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				 DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){
				
				//Send request (a String) to the server
				out.writeUTF(email);
				out.writeUTF(password);
				System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + email + "'");
				
				//Read response (a String) from the server
				data = in.readBoolean();			
				System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
			} catch (UnknownHostException e) {
				System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
			} catch (EOFException e) {
				System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
			}
		return data;
	}
	
	public boolean getResponse()
	{
		return data;
	}
	public void setFacebookPortIp(String port, String ip)
	{
		System.out.println("facebook inside" + port + ip);
		this.serverIP = ip;
		this.serverPort = Integer.parseInt(port);
	}
}
