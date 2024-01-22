package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;

public class AppWindow extends JFrame {

	/**
	 * TONTO EL QUE LO LEA
	 * 
	 * Detalles a la derecha del combo box
	 * 
	 * Login correcto que pase usuario/token a app window para poder sacar
	 * challenges del usuario
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private LoginController loginController;
	private long token;
	private static JFrame frame;
	private int j;
	private int i;
	private JComboBox sessions = new JComboBox();
	private JComboBox challenges = new JComboBox();
	private JComboBox challengesAcc = new JComboBox();
	private String challengedetail;
	private String challengedetailAcc;
	private String sessiondetail;
	private List<String> dsChallengeDTO_s = new ArrayList<>();
	private List<ChallengeDTO> dsChallengeDTOs = new ArrayList<>();
	private List<String> dsSessionDTO_s = new ArrayList<>();
	private List<SessionDTO> dsSessionDTO = new ArrayList<>();
	private List<String> dsChalUserDTO_s = new ArrayList<>();
	private List<ChallengeDTO> dsChalUserDTO = new ArrayList<>();
	private JTextArea SessionDetails;
	private JTextArea challengeDetailsAcc;

	public AppWindow(Controller controller, LoginController loginController, long token) {
		this.controller = controller;
		this.loginController = loginController;
		this.token = token;
		Ventana(this);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleWindowClosing();
			}
		});
	}

	// check
	public List<ChallengeDTO> getChallenges() {
		System.out.println(" -Getting all  the challenges ...");

		List<ChallengeDTO> challenges = this.controller.getChallenges();

		for (ChallengeDTO challenge : challenges) {
			System.out.println("\t* " + challenge.getName());
		}

		return challenges;
	}

	public List<SessionDTO> getSession(long token) {
		System.out.println(" - Getting sessions of the actual user ");

		List<SessionDTO> sessions = this.controller.getSessions(token);

		for (SessionDTO session : sessions) {
			session.toString();
		}

		return sessions;

	}

	public void getAll() {
		System.out.println("GET_ALL");
		try {
			dsSessionDTO = ((this.controller.getServiceLoc().getService())).getSessions(token);
			if (dsSessionDTO != null) {
				if (!dsSessionDTO.isEmpty()) {
					System.out.println("dsSessionDTO: "+ dsSessionDTO.size());
					for (SessionDTO session : dsSessionDTO) {
						dsSessionDTO_s.add(session.getTitle());
					}
					sessions = new JComboBox(dsSessionDTO.toArray());
				}
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		try {
			dsChalUserDTO = ((this.controller.getServiceLoc().getService())).getAcceptedChallenges(token);
			if (dsChalUserDTO != null) {
				if (!dsChalUserDTO.isEmpty()) {
					System.out.println("dsChalUserDTO: "+ dsChalUserDTO.size());
					System.out.println(dsChalUserDTO);
					for (ChallengeDTO chal : dsChalUserDTO) {
						dsChalUserDTO_s.add(chal.getName());
					}
				}
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
				
		
		frame.repaint();
		frame.validate();
	}

	public void Ventana(AppWindow appWind) {

		
		frame = new JFrame();
		frame.setTitle("APP PAGE");
		frame.setSize(425, 220);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(4, 3));
		frame.setLocationRelativeTo(null);

		///////// START - CHECKBOX TEST////////////

		getAll();

		///////// END - CHECKBOX TEST////////////
		challengedetail = new String();


		// Username label constructor
		JLabel label3 = new JLabel("ChallengesAccepted");
		label3.setBounds(100, 68, 70, 20);
		frame.add(label3);
		challengedetailAcc = new String();

		if (dsChalUserDTO == null || dsChalUserDTO.isEmpty()) {
		    // Handle the case when dsChallengeDTO_s is null or empty
		    challengesAcc = new JComboBox();
		    // You might want to show a message or disable the JComboBox if needed
		    label3.setText("No Challenges available");
		} else {
			System.out.println(dsChallengeDTO_s);
			challengesAcc = new JComboBox(dsChalUserDTO_s.toArray());


		    challengesAcc.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            int selectedIndex = challengesAcc.getSelectedIndex();
		            if (selectedIndex != -1) {
		                j = selectedIndex;
		                challengedetailAcc = "Name :           " + dsChalUserDTO.get(j).getName() + '\n' +
		                        "Sport :         " + dsChalUserDTO.get(j).getSport() + '\n' +
		                        "Start_date : " + dsChalUserDTO.get(j).getStartDate() + '\n' +
		                        "EndDate : " + dsChalUserDTO.get(j).getEndDate() + '\n' +
		                        "Target :    " + dsChalUserDTO.get(j).getTarget();
		                if (dsChalUserDTO.get(j).getDistanceorTime()) {
		                    challengedetailAcc += " km";
		                } else {
		                    challengedetailAcc += " min";
		                }
		            } else {
		                // Handle the case when no item is selected
		                challengedetailAcc = "No challenge selected.";
		            }
		            challengeDetailsAcc.setText(challengedetailAcc);
		        }
		    });
		}

		frame.add(challengesAcc);


				challengeDetailsAcc = new JTextArea(challengedetailAcc);
				frame.add(challengeDetailsAcc);

				challengesAcc.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        if (challengesAcc.getSelectedIndex() != -1) {
				            j = challengesAcc.getSelectedIndex();
				            challengedetailAcc = "Name :           " + dsChalUserDTO.get(j).getName() + '\n' + "Sport :         "
				                    + dsChalUserDTO.get(j).getSport() + '\n' + "Start_date : " + dsChalUserDTO.get(j).getStartDate()
				                    + '\n' + "EndDate : " + dsChalUserDTO.get(j).getEndDate() + '\n' + "Distance :    "
						                    + dsChalUserDTO.get(j).getDistanceorTime()+ '\n' + "Target :    "
								                    + dsChalUserDTO.get(j).getTarget();
				            if (dsChalUserDTO.get(j).getDistanceorTime()) {
				                challengedetailAcc += " km";
				            } else {
				                challengedetailAcc += " min";
				            }
				            challengeDetailsAcc.setText(challengedetailAcc);
				        } else {
				            // Handle the case when no item is selected
				            challengedetailAcc = "No challenge selected.";
				            challengeDetailsAcc.setText(challengedetailAcc);
				        }
				    }
				});
		
		// Username label constructor
		JLabel label2 = new JLabel("Sessions");
		label2.setBounds(100, 38, 70, 20);
		frame.add(label2);

		if (dsSessionDTO == null || dsSessionDTO.isEmpty()) {
			System.out.println("no sessions");
			// Handle the case when dsSessionDTO is null or empty
			sessions = new JComboBox();
			// You might want to show a message or disable the JComboBox if needed
			label2.setText("No Sessions available");
		} else {
			System.out.println("sessions ");
			// Create JComboBox with dsSessionDTO items
			sessions = new JComboBox(dsSessionDTO_s.toArray());
			sessiondetail = "Title :           " + dsSessionDTO.get(0).getTitle() + '\n' + "Sport :           "
					+ dsSessionDTO.get(0).getSport() + '\n' + "Start_date :  " + dsSessionDTO.get(0).getStartDate()
					+ '\n' + "Start_time :  " + dsSessionDTO.get(0).getStartTime() + '\n' + "Duration :     "
					+ dsSessionDTO.get(0).getDuration();
		}

		frame.add(sessions);
		SessionDetails = new JTextArea(sessiondetail);

		sessions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				i = sessions.getSelectedIndex();
				sessiondetail = "Title :           " + dsSessionDTO.get(i).getTitle() + '\n' + "Sport :         "
						+ dsSessionDTO.get(i).getSport() + '\n' + "Start_date : " + dsSessionDTO.get(i).getStartDate()
						+ '\n' + "Start_time : " + dsSessionDTO.get(i).getStartTime() + '\n' + "Duration :    "
						+ dsSessionDTO.get(i).getDuration();
				SessionDetails.setText(sessiondetail);
			}
		});
		frame.add(SessionDetails);

		JButton chalengeButton = new JButton("Create Challenge");
		chalengeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setVisible(false); // Hide the window
					new CreateChallengeDialog(loginController, controller, token);
					getAll();
					frame.dispose();
					frame.repaint();
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		frame.add(chalengeButton);

		JButton sessionButton = new JButton("Create Session");
		sessionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setVisible(false); // Hide the window
					new CreateSessionDialog(loginController, controller, token);
					getAll();
					frame.dispose();
					frame.repaint();

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		frame.add(sessionButton);

		JButton apuntarButton = new JButton("Apuntarse Challenge");
		apuntarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				frame.setVisible(false); // Hide the window
				new ChallengeFrame(loginController, controller, token);
				getAll();
				frame.dispose();
				frame.repaint();
			}
		});
		frame.add(apuntarButton);

		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.getServiceLoc().getService().logout(token);
				} catch (RemoteException e) {
					System.out.println("error login out");
					e.printStackTrace();
				}
				frame.dispose();
				new LoginDialog(loginController, controller);
			}
		});
		frame.add(new Label(""));
		frame.add(logoutButton);
		frame.pack();
	}

	private void handleWindowClosing() {
		try {
			controller.getServiceLoc().getService().logout(token);
		} catch (RemoteException e) {
			System.out.println("Error logging out");
			e.printStackTrace();
		}
	}

	public void updateChallengeDetails(ChallengeDTO c, JLabel l) {

		l.setText(c.toString());

	}

	public long getToken() {
		return token;
	}
}
