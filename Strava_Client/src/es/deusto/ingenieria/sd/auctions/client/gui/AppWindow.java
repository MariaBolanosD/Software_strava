package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;

public class AppWindow extends JFrame{

	/** TONTO EL QUE LO LEA
	 * 
	 * Detalles a la derecha del combo box
	 * 
	 * Login correcto que pase usuario/token a app window
	 * para poder sacar challenges del usuario
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private long token; 
	private static JFrame frame;
	
	public AppWindow(Controller controller,long token )
	{
		this.controller = controller;
		this.token = token;
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
	
	public List<SessionDTO> getSession(long token)
	{
		System.out.println(" - Getting sessions of the actual user ");
		
		List<SessionDTO> sessions = this.controller.getSessions(token);
		
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
		frame.setSize(425, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(4,3));
		frame.setLocationRelativeTo(null);
		
		
	/////////START - CHECKBOX TEST////////////
		List<String> dsChallengeDTO_s = new ArrayList<>();
		List<ChallengeDTO> dsChallengeDTOs = new ArrayList<>();
		try {
			dsChallengeDTOs =((this.controller.getServiceLoc().getService())).getChallenges();
			for (ChallengeDTO challenge: dsChallengeDTOs ) {
				dsChallengeDTO_s.add(challenge.getName());
				
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<String> dsSessionDTO_s = new ArrayList<>();
		List<SessionDTO>  dsSessionDTO = new ArrayList<>();
		try {
			dsSessionDTO =((this.controller.getServiceLoc().getService())).getSessions(token);
			for (SessionDTO session: dsSessionDTO ) {
				dsSessionDTO_s.add(session.getTitle());
				
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
				try {
					controller.getServiceLoc().getService().logout(token);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					System.out.println("error login out");
					e.printStackTrace();
				}
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
