package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.AbstractSpinnerModel;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;


public class LoginDialog extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginController controller;	
	private Controller controllerApp;
//	private String email = "thomas.e2001@gmail.com";
//	private String password = "$!9PhNz,";

	private static JPasswordField Password;
	private static JTextField username;
	private static JTextField email_s;
	private static JTextField name;
	private static JFrame frame;
	private static JComboBox<String> typeofAccount;
	private static JSpinner spinner2;
	private static JSpinner tf7;
	private static JSpinner tf8;
	private static JSpinner tf9;
	private static JSpinner tf10;
	
	public LoginDialog(LoginController controller, Controller controller2) {
		this.controller = controller;
		this.controllerApp = controller2;
		//VentanaRegister();
		VentanaLogin();
	}
	
	public boolean login() {		
		
		String email = username.getText();
		String password = String.valueOf(Password.getPassword());
		
		System.out.println(" - Login into the server: '" + email + "' - '" + password + "' ...");
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		System.out.println("\t* Password hash: " + sha1);		
		boolean result = this.controller.login(email, sha1);
		System.out.println("\t* Login result: " + result);
		System.out.println("\t* Token: " + this.controller.getToken());
		if(controller.login(email, password))
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Incorrect password.");
			return false;
		}
	}
	
	public void register()
	{		
		String email = email_s.getText();
		
		String accountString = (String)typeofAccount.getSelectedItem();
		if(accountString.compareTo("Google") == 0) 
		{			
			float weight =  Float.parseFloat(tf7.getValue().toString());
			//System.out.println(weight);
			
			float height=  Float.parseFloat(tf8.getValue().toString());
			int heartmax=  Integer.parseInt(tf9.getValue().toString());
			int heartrest= Integer.parseInt(tf10.getValue().toString());
			
			
			
			System.out.println(" - Register into the GOOGLE server: '" + email);
			Date bitDate = (Date) spinner2.getValue();
			LocalDate birthDate = convertToLocalDateViaInstant(bitDate);
			System.out.println(email);
			boolean bool = controller.Register(TypeOfAccount.GOOGLE, email, name.getText(), birthDate, weight,height,heartmax,heartrest);
			if(bool == false)
				JOptionPane.showMessageDialog(frame, "Email not in Google. Account not registered");
			else
				JOptionPane.showMessageDialog(frame, "Registration complete.");
		}
		else { 
			//  FACEBOOK
				float weight =  Float.parseFloat(tf7.getValue().toString());
				//System.out.println(weight);
				
				float height=  Float.parseFloat(tf8.getValue().toString());
				int heartmax=  Integer.parseInt(tf9.getValue().toString());
				int heartrest= Integer.parseInt(tf10.getValue().toString());
				
				
				
				System.out.println(" - Register into the Facebook server: '" + email);
				Date bitDate = (Date) spinner2.getValue();
				LocalDate birthDate = convertToLocalDateViaInstant(bitDate);
				System.out.println(email);
				boolean bool = controller.Register(TypeOfAccount.FACEBOOK, email, name.getText(), birthDate, weight,height,heartmax,heartrest);
				if(bool == false)
					JOptionPane.showMessageDialog(frame, "Email not in Facebook. Account not registered");
				else
					JOptionPane.showMessageDialog(frame, "Registration complete.");
			}
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
				System.out.println("click");
				CheckLogin();
			}
		});
		panel.add(button);
		
		JButton button2 = new JButton("Register");
		button2.setVisible(true);
		button2.setBounds(100, 110, 90, 25);
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.BLACK);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				VentanaRegister();
			}
		});
		panel.add(button2);
		
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
		 
		JLabel l1 = new JLabel("Registration Form:");  
		//l1.setForeground(Color.blue);   
		l1.setBounds(100, 30, 400, 30);  
		l1.setVisible(true);
		frame.add(l1);  
		
        JLabel l2 = new JLabel("TypeOfAccount");
        l2.setBounds(80, 70, 200, 30);  
        frame.add(l2);   
        typeofAccount = new JComboBox<>(); typeofAccount.addItem("Google"); typeofAccount.addItem("Facebook");
        typeofAccount.setBounds(300, 70, 200, 30);  
        frame.add(typeofAccount);  
        
        JLabel l3 = new JLabel("Email:");  
        l3.setBounds(80, 110, 200, 30);  
        frame.add(l3);  
        email_s = new JTextField();  
        email_s.setBounds(300, 110, 200, 30);  
        frame.add(email_s);   
        
        JLabel l6 = new JLabel("Name:");  
        l6.setBounds(80, 150, 200, 30);  
        frame.add(l6);  
        name = new JTextField();  
        name.setBounds(300, 150, 200, 30);  
        frame.add(name);  
        
        JLabel l7 = new JLabel("Birthday:");  
        l7.setBounds(80, 190, 200, 30);  
        frame.add(l7);  
        JTextField tf6 = new JTextField();  
        tf6.setBounds(300, 190, 200, 30);          
        Date today = new Date();
		spinner2 = new JSpinner(new SpinnerDateModel(today , null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner2, "dd/MM/yy");
	    spinner2.setEditor(editor);
	    spinner2.setBounds(300, 190, 200, 30);
        frame.add(spinner2);
	    
        JLabel l8 = new JLabel("Weight(kg):");   
        l8.setBounds(80, 230, 200, 30);  
        frame.add(l8);  SpinnerNumberModel numberModel = new SpinnerNumberModel(50, 0, 300, 1);
        tf7 = new JSpinner(numberModel);  
        tf7.setBounds(220, 230, 100, 30);  
        frame.add(tf7);  
        
        JLabel l9 = new JLabel("Height(m):");   
        l9.setBounds(350, 230, 200, 30);  
        frame.add(l9);  SpinnerNumberModel numberModel2 = new SpinnerNumberModel(1.74f, 0.0f, 2.50f, 0.01f);
        tf8 = new JSpinner(numberModel2);  
        tf8.setBounds(490, 230, 100, 30);  
        frame.add(tf8);  
        
        JLabel l10 = new JLabel("Max Heart Rate:");   
        l10.setBounds(80, 270, 200, 30);  
        frame.add(l10);  SpinnerNumberModel numberModel3 = new SpinnerNumberModel(185, 0, 200 ,1);
        tf9 = new JSpinner(numberModel3);  
        tf9.setBounds(220, 270, 100, 30);  
        frame.add(tf9);  
        
        JLabel l11 = new JLabel("Heart Rate at Rest:");   
        l11.setBounds(350, 270, 200, 30);  
        frame.add(l11);  SpinnerNumberModel numberModel4 = new SpinnerNumberModel(70, 0, 200, 1);
        tf10 = new JSpinner(numberModel4);  
        tf10.setBounds(490, 270, 100, 30);  
        frame.add(tf10);  
        
        JButton btn1 = new JButton("Submit");  
        btn1.setBounds(360, 320, 100, 30);  
        frame.add(btn1);  
        
        JButton btn3 = new JButton("Log in");  
        btn3.setBounds(100, 320, 100, 30);  
        frame.add(btn3);  
        
        btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				register();
			}
		}); 
        
        btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				VentanaLogin();
			}
		});  
        frame.validate();
        frame.repaint();
	}
	
	void CheckLogin()
	{
		if(login())
		{
			
			frame.dispose();
			
			AppWindow appWindow = new AppWindow(controllerApp, controller, this.controller.getToken());
			
		}
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}