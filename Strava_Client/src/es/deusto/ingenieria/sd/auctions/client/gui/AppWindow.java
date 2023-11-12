package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.UserDTO;

public class AppWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	private static JFrame frame;
	private static JPasswordField Password;
	private static JTextField username;
	
	public AppWindow(Controller controller )
	{
		this.controller = controller;
		Ventana();
	}
	
	// check 
	public List<ChallengeDTO> getChallenges()
	{
		System.out.println(" -Getting all  the challenges ...");
		
		List<ChallengeDTO> challenges = this.controller.getChallenges();
		
		for (ChallengeDTO challenge : challenges) {
			System.out.println("\t* " + challenge.getName());
		}
		
		return challenges;
	}
	
	public List<SessionDTO> getSession(UserDTO user)
	{
		System.out.println(" - Getting sessions of the user '" + user + "' ...");
		
		List<SessionDTO> sessions = this.controller.getSessions(user);
		
		for(SessionDTO session : sessions)
		{
			session.toString();
		}
		
		return sessions;
		
	}
	
	public void Ventana()
	{		
		
		
		frame = new JFrame();
		frame.setTitle("APP PAGE");
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(4,3));
		
		
	/////////START - CHECKBOX TEST////////////
		List<String> dsChallengeDTO_s = new ArrayList<>();
		
		List<String> dsSessionDTO_s = new ArrayList<>();
		ChallengeDTO cycling1 = new ChallengeDTO();
		cycling1.setName("Challenge1");
		cycling1.setStartDate(LocalDate.now());
		cycling1.setEndDate(LocalDate.now());
		ChallengeDTO cycling2 = new ChallengeDTO();
		cycling2.setName("Challenge2");
		dsChallengeDTO_s.add(cycling1.getName());
		dsChallengeDTO_s.add(cycling2.getName());
		
		SessionDTO session1 = new SessionDTO();
		SessionDTO session2 = new SessionDTO();
		session1.setTitle("Session 1");
		session2.setTitle("Session 2");
		dsSessionDTO_s.add(session1.getTitle());
		dsSessionDTO_s.add(session2.getTitle());
		/////////END - CHECKBOX TEST////////////
		
		// Username label constructor
		JLabel label = new JLabel("Challenges");
		label.setBounds(100, 8, 70, 20);
		frame.add(label);		
		JComboBox challenges = new JComboBox(dsChallengeDTO_s.toArray()) ; //controller.getChallenges());
		frame.add(challenges);
		challenges.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //updateChallengeDetails(, label);
            }
        });
		
		JLabel challengeDetails = new JLabel("Challenge Details [NOT IMPLEMENTED]");
		frame.add(challengeDetails);
		
		
			
		// Username label constructor
		JLabel label2 = new JLabel("Sessions");
		label2.setBounds(100, 38, 70, 20);
		frame.add(label2);
		JComboBox sessions = new JComboBox(dsSessionDTO_s.toArray()) ; //controller.getChallenges());
		frame.add(sessions);
		
		JLabel SessionDetails = new JLabel("Session Details [NOT IMPLEMENTED]");
		frame.add(SessionDetails);
		JButton chalengeButton = new JButton("Create Challenge");
		chalengeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.add(chalengeButton);
		
		JButton sessionButton = new JButton("Create Session");
		sessionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.add(sessionButton);
		
		JButton apuntarButton = new JButton("Apuntarse Challenge");
		apuntarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.add(apuntarButton
				);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		frame.add(new Label(""));
		frame.add(logoutButton);
		
	}
	
	
	
	public void updateChallengeDetails(ChallengeDTO c, JLabel l)
	{
		
		l.setText(c.toString());
		
	}
	
}
