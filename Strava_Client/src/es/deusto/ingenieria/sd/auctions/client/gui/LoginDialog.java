package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		VentanaRegister();
		//VentanaLogin();
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

	public void VentanaMain()
	{
		
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
		
		frame.validate();
	    frame.repaint();
		
	}

	public void VentanaRegister()
	{
		//JPanel panel = new JPanel(null);
		
		frame = new JFrame();
		frame.setTitle("LOGIN PAGE");
		//frame.add(panel);
		//panel.setSize(700,700);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setTitle("Registration Form in Java");  
		//frame.pack();
		 
		JLabel l1 = new JLabel("Registration Form in Windows Form:");  
		//l1.setForeground(Color.blue);   
		l1.setBounds(100, 30, 400, 30);  
		l1.setVisible(true);
		frame.add(l1);  
		
        JLabel l2 = new JLabel("Name:");
        l2.setBounds(80, 70, 200, 30);  
        frame.add(l2);   
        JTextField tf1 = new JTextField();  
        tf1.setBounds(300, 70, 200, 30);  
        frame.add(tf1);  
        
        JLabel l3 = new JLabel("Email:");  
        l3.setBounds(80, 110, 200, 30);  
        frame.add(l3);  
        JTextField tf2 = new JTextField();  
        tf2.setBounds(300, 110, 200, 30);  
        frame.add(tf2);  
        
        JLabel l4 = new JLabel("Create Passowrd:");  
        l4.setBounds(80, 150, 200, 30);  
        frame.add(l4);  
        JPasswordField p1 = new JPasswordField();  
        p1.setBounds(300, 150, 200, 30);  
        frame.add(p1);  

        JLabel l5 = new JLabel("Confirm Password:");  
        l5.setBounds(80, 190, 200, 30);  
        frame.add(l5);  
        JPasswordField p2 = new JPasswordField();  
        p2.setBounds(300, 190, 200, 30);  
        frame.add(p2);  
        
        JLabel l6 = new JLabel("Name:");  
        l6.setBounds(80, 230, 200, 30);  
        frame.add(l6);  
        JTextField tf5 = new JTextField();  
        tf5.setBounds(300, 230, 200, 30);  
        frame.add(tf5);  
        
        JLabel l7 = new JLabel("Birthday:");  
        l7.setBounds(80, 270, 200, 30);  
        //frame.add(l7);  
        JTextField tf6 = new JTextField();  
        tf6.setBounds(300, 270, 200, 30);  
        //frame.add(tf6);  
        
        DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
        JFormattedTextField today = new JFormattedTextField(dateFormat);
        today.setName("Birthday");
        today.setColumns(10);
        today.setEditable(true);
        JLabel todayLabel = new JLabel("Birtday:");
        todayLabel.setLabelFor(today);
        todayLabel.setBounds(80, 270, 200, 30); 
        today.setValue(new Date());
        today.setBounds(300, 270, 200, 30);  
        frame.add(todayLabel);
        frame.add(today);
        
        JLabel l8 = new JLabel("Weight(kg):");   
        l8.setBounds(80, 310, 200, 30);  
        frame.add(l8);  
        JTextField tf7 = new JTextField();  
        tf7.setBounds(300, 310, 200, 30);  
        frame.add(tf7);  
        
        JLabel l9 = new JLabel("Height(m):");   
        l9.setBounds(80, 350, 200, 30);  
        frame.add(l9);  
        JTextField tf8 = new JTextField();  
        tf8.setBounds(300, 350, 200, 30);  
        frame.add(tf8);  
        
        frame.validate();
        frame.repaint();
        
        JButton btn1 = new JButton("Submit");  
        JButton btn2 = new JButton("Clear");  
        
        btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
							}
		});
        
        btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});  
        btn1.setBounds(50, 390, 100, 30);  
        btn2.setBounds(170, 390, 100, 30);  
        frame.add(btn1);  
        frame.add(btn2);
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