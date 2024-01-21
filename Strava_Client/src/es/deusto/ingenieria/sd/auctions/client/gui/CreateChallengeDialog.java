package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

public class CreateChallengeDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static JTextField name;
	private static JComboBox<SportEnum> sport;
	private static SportEnum[] sports = SportEnum.values();
	private static String[] pickable = {"Distance", "Time"};
	private static boolean dist_time_variable;
	private static JTextField target;
	private static JComboBox<String> dist_time;
	private static JSpinner start_date;
	private static JSpinner end_date;
	
	public CreateChallengeDialog(AppWindow parentWind, Controller controller) throws ParseException
	{
		//JPanel panel = new JPanel(null);
		
		frame = new JFrame();
		frame.setTitle("CREATE CHALLENGE");
		//frame.add(panel);
		//panel.setSize(700,700);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null); 
		//frame.pack();
		 
		JLabel l1 = new JLabel("Create Challenge:");  
		//l1.setForeground(Color.blue);   
		l1.setBounds(80, 30, 400, 30);  
		l1.setVisible(true);
		frame.add(l1);  
		
        JLabel l3 = new JLabel("Name:");  
        l3.setBounds(80, 70, 200, 30);  
        frame.add(l3);  
        name = new JTextField();  
        name.setBounds(300, 70, 200, 30);  
        frame.add(name);   
        
        JLabel l4 = new JLabel("Sport:");  
        l4.setBounds(80, 150, 200, 30);  
        frame.add(l4);  
        sport = new JComboBox<SportEnum>(sports);  
        sport.setBounds(300, 150, 200, 30);  
        frame.add(sport);
        
        JLabel l5 = new JLabel("Target (km o mins):");  
        l5.setBounds(80, 310, 200, 30);  
        frame.add(l5);  
        target = new JTextField();  
        target.setBounds(300, 310, 200, 30);  
        frame.add(target);
        
        JLabel l6 = new JLabel("Distance/Time");
        dist_time_variable = true;
        l6.setBounds(80, 230, 200, 30);
        frame.add(l6);
        dist_time = new JComboBox<String>(pickable);
        dist_time.setBounds(300, 230, 200, 30);
        frame.add(dist_time);
        dist_time.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				variableChange();
			}
		});
        
        JLabel l7 = new JLabel("Start Date:");  
        l7.setBounds(80, 390, 200, 30);  
        frame.add(l7);        
        Date today1 = new Date();
		start_date = new JSpinner(new SpinnerDateModel(today1 , null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(start_date, "dd/MM/yy");
	    start_date.setEditor(editor);
	    start_date.setBounds(300, 390, 200, 30);
        frame.add(start_date);
        frame.repaint();
	    
        JLabel l8 = new JLabel("End Date:");  
        l8.setBounds(80, 470, 200, 30);  
        frame.add(l8);          
        Date today2 = new Date();
        end_date = new JSpinner(new SpinnerDateModel(today2, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(end_date, "dd/MM/yy");
        end_date.setEditor(editor2);
	    end_date.setBounds(300, 470, 200, 30);
        frame.add(end_date);  
        
        JButton btn1 = new JButton("Create");  
        btn1.setBounds(410, 550, 100, 30);  
        frame.add(btn1);
        btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createChallenge(parentWind, controller);
				
			}
		});
        
        JButton btn3 = new JButton("Cancel");  
        btn3.setBounds(150, 550, 100, 30);  
        frame.add(btn3);
        btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentWind.setEnabled(true);
				frame.dispose();
			}
		});
        frame.validate();
        frame.repaint();

	
	}
	
	private void variableChange() {

        int selectedIndex = dist_time.getSelectedIndex();

        if (selectedIndex == 0) {
            dist_time_variable = true;
            System.out.println("true");
        } else if (selectedIndex == 1) {
        	dist_time_variable = false;
        	 System.out.println("false");
        }
    }
	
	public void createChallenge(AppWindow parentWindow, Controller controller)
	{
		String name_s = name.getText();
		Date start_date_d = (Date) start_date.getValue();
		LocalDate start_date_ld = convertToLocalDateViaInstant(start_date_d);
		Date end_date_d = (Date) end_date.getValue();
		LocalDate end_date_ld = convertToLocalDateViaInstant(end_date_d);
		float target_f =  Float.parseFloat(target.getText());
		SportEnum sport_e = (SportEnum) sport.getSelectedItem();
		boolean dist_time_b = dist_time_variable;
		
		controller.makeChallenge(parentWindow.getToken(), 
				name_s, start_date_ld, end_date_ld, target_f, 
				sport_e, dist_time_b);
		parentWindow.setEnabled(true);
		frame.dispose();

		
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static void main(String[] args) throws ParseException {
		// new CreateChallengeDialog();
	}
}
