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

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
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
	private LoginController loginController;
	private long token; 
	private static JFrame frame;
	private int j ;
	private int i ;
	private String challengedetail;
	private String sessiondetail;
	private List<String> dsChallengeDTO_s = new ArrayList<>();
	private List<ChallengeDTO> dsChallengeDTOs = new ArrayList<>();
	private List<String> dsSessionDTO_s = new ArrayList<>();
	private List<SessionDTO>  dsSessionDTO = new ArrayList<>();
	private JTextArea challengeDetails;
	private JTextArea SessionDetails;
	
	public AppWindow(Controller controller, LoginController loginController, long token )
	{
		this.controller = controller;
		this.loginController = loginController;
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
		
		try {
			dsChallengeDTOs =((this.controller.getServiceLoc().getService())).getChallenges();
			for (ChallengeDTO challenge: dsChallengeDTOs ) {
				dsChallengeDTO_s.add(challenge.getName());
				
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		challengedetail = new String(); 
		
		// Username label constructor
		JLabel label = new JLabel("Challenges");
		label.setBounds(100, 8, 70, 20);
		frame.add(label);		
		JComboBox challenges = new JComboBox(dsChallengeDTO_s.toArray()) ; //controller.getChallenges());
		frame.add(challenges);
		j= challenges.getSelectedIndex();
		challengedetail =   "Name :           " + dsChallengeDTOs.get(j).getName() + '\n' +
      		"Sport :         " + dsChallengeDTOs.get(j).getSport() + '\n' + 
      		"Start_date : " + dsChallengeDTOs.get(j).getStartDate()+ '\n' + 
      		"EndDate : " + dsChallengeDTOs.get(j).getEndDate()+'\n'+
      		"Target :    " + dsChallengeDTOs.get(j).getTarget();
		if(dsChallengeDTOs.get(j).getDistanceorTime() == true)
        {
        	challengedetail += " km";
        }
        else {
        	challengedetail += " min";
        }
		challengeDetails = new JTextArea(challengedetail);
		frame.add(challengeDetails);
		
		
		challenges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("cambiando" + challenges.getSelectedIndex());
				j = challenges.getSelectedIndex();
				challengedetail =   "Name :           " + dsChallengeDTOs.get(j).getName() + '\n' +
              		"Sport :         " + dsChallengeDTOs.get(j).getSport() + '\n' + 
              		"Start_date : " + dsChallengeDTOs.get(j).getStartDate()+ '\n' + 
              		"EndDate : " + dsChallengeDTOs.get(j).getEndDate()+'\n'+
              		"Target :    " + dsChallengeDTOs.get(j).getTarget();
				System.out.println(challengedetail);
              if(dsChallengeDTOs.get(j).getDistanceorTime() == true)
              {
              	challengedetail += " km";
              }
              else {
              	challengedetail += " min";
              }
				 challengeDetails.setText(challengedetail);
			}
		});

			
		// Username label constructor
		JLabel label2 = new JLabel("Sessions");
		label2.setBounds(100, 38, 70, 20);
		frame.add(label2);
		JComboBox sessions = new JComboBox(dsSessionDTO_s.toArray()) ; //controller.getChallenges());
		sessiondetail = new String(); i = sessions.getSelectedIndex();
		sessiondetail = "Title :           " + dsSessionDTO.get(i).getTitle() + '\n' +
        		"Sport :         " + dsSessionDTO.get(i).getSport() + '\n' + 
        		"Start_date : " + dsSessionDTO.get(i).getStartDate()+ '\n' + 
        		"Start_time : " + dsSessionDTO.get(i).getStartTime()+'\n'+
        		"Duration :    " + dsSessionDTO.get(i).getDuration();
		frame.add(sessions);
		SessionDetails = new JTextArea(sessiondetail);
		sessions.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                i = sessions.getSelectedIndex();
                sessiondetail = "Title :           " + dsSessionDTO.get(i).getTitle() + '\n' +
                		"Sport :         " + dsSessionDTO.get(i).getSport() + '\n' + 
                		"Start_date : " + dsSessionDTO.get(i).getStartDate()+ '\n' + 
                		"Start_time : " + dsSessionDTO.get(i).getStartTime()+'\n'+
                		"Duration :    " + dsSessionDTO.get(i).getDuration();
                SessionDetails.setText(sessiondetail);
            }
        });
		
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
				LoginDialog login = new LoginDialog(loginController, controller);
			}
		});
		frame.add(new Label(""));
		frame.add(logoutButton);
		frame.pack();
	}

	
	public void updateChallengeDetails(ChallengeDTO c, JLabel l)
	{
		
		l.setText(c.toString());
		
	}
	
}
