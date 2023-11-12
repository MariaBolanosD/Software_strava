package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;


public class LoginDialog extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginController controller;	
	private Controller controllerApp;
	private String email = "thomas.e2001@gmail.com";
	private String password = "$!9PhNz,";

	private static JPasswordField Password;
	private static JTextField username;
	private static JFrame frame;
	
	public LoginDialog(LoginController controller, Controller controller2) {
		this.controller = controller;
		this.controllerApp = controller2;
		VentanaLogin();
	}
	
	public boolean login() {		
		System.out.println(" - Login into the server: '" + username.getText() + "' - '" + Password.getPassword().toString() + "' ...");
//		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
//		System.out.println("\t* Password hash: " + sha1);		
//		boolean result = this.controller.login(email, sha1);
//		System.out.println("\t* Login result: " + result);
//		System.out.println("\t* Token: " + this.controller.getToken());

		return true;
	}
	
	public void logout() {
		System.out.println(" - Logout from the server...");		
		this.controller.logout();
		System.out.println("\t* Logout success!");		

	}
	
//	
	
	///////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		LoginDialog loginDialog = new LoginDialog(null, null);
	}
	
	public void VentanaLogin()
	{		
		
		JPanel panel = new JPanel(new GridLayout(4, 1));
		
		frame = new JFrame();
		frame.setTitle("LOGIN PAGE");
		frame.add(panel);
		frame.setSize(425, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		// Username label constructor
		JLabel label = new JLabel("Email");
		label.setBounds(100, 8, 70, 20);
		panel.add(label);
		
		// Username TextField constructor
		 username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		panel.add(username);
		
		// Password Label constructor
		JLabel password1 = new JLabel("Password");
		password1.setBounds(100, 55, 70, 20);
		panel.add(password1);

		// Password TextField
		 Password = new JPasswordField();
		Password.setBounds(100, 75, 193, 28);
		panel.add(Password);
		
		// Button constructor
		JButton button = new JButton("Login");
		button.setVisible(true);
		button.setBounds(100, 110, 90, 25);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckLogin();
			}
		});
		panel.add(button);
		
	}

	void CheckLogin()
	{
		if(login())
		{
			
			frame.dispose();
			
			AppWindow appWindow = new AppWindow(controllerApp);
			
			
		}
	}
}