package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

public class CreateSessionDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static JTextField title;
	private static JComboBox<SportEnum> sport;
	private static SportEnum[] sports = SportEnum.values();
	private static JTextField distance;
	private static JSpinner start_date;
	private static JFormattedTextField start_time;
	DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static JTextField duration;
	
	public CreateSessionDialog(AppWindow parentWind, Controller controller) throws ParseException
	{
		//JPanel panel = new JPanel(null);
		
		frame = new JFrame();
		frame.setTitle("CREATE SESSION");
		//frame.add(panel);
		//panel.setSize(700,700);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null); 
		//frame.pack();
		 
		JLabel l1 = new JLabel("Create Session:");  
		//l1.setForeground(Color.blue);   
		l1.setBounds(80, 30, 400, 30);  
		l1.setVisible(true);
		frame.add(l1);  
		
        JLabel l3 = new JLabel("Title:");  
        l3.setBounds(80, 70, 200, 30);  
        frame.add(l3);  
        title = new JTextField();  
        title.setBounds(300, 70, 200, 30);  
        frame.add(title);   
        
        JLabel l4 = new JLabel("Sport:");  
        l4.setBounds(80, 150, 200, 30);  
        frame.add(l4);  
        sport = new JComboBox<SportEnum>(sports);  
        sport.setBounds(300, 150, 200, 30);  
        frame.add(sport);
        
        JLabel l5 = new JLabel("Distance (km):");  
        l5.setBounds(80, 230, 200, 30);  
        frame.add(l5);  
        distance = new JTextField();  
        distance.setBounds(300, 230, 200, 30);  
        frame.add(distance);  
        
        JLabel l7 = new JLabel("Start Date:");  
        l7.setBounds(80, 310, 200, 30);  
        frame.add(l7);        
        Date today1 = new Date();
		start_date = new JSpinner(new SpinnerDateModel(today1 , null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(start_date, "dd/MM/yy");
	    start_date.setEditor(editor);
	    start_date.setBounds(300, 310, 200, 30);
        frame.add(start_date);
        frame.repaint();
	    
        JLabel l8 = new JLabel("Start Time: (HH:MM:SS)");  
        l8.setBounds(80, 390, 200, 30);  
        frame.add(l8);          
        MaskFormatter formatter = new MaskFormatter("##:##:##");
        formatter.setPlaceholderCharacter('0');
        start_time = new JFormattedTextField(formatter);
        start_time.setValue("00:00:00");
	    start_time.setBounds(300, 390, 200, 30);
        frame.add(start_time);  
        
        JLabel l9 = new JLabel("Duration (mins):");  
        l9.setBounds(80, 470, 200, 30);  
        frame.add(l9);  
        duration = new JTextField();  
        duration.setBounds(300, 470, 200, 30);  
        frame.add(duration);    
        
        JButton btn1 = new JButton("Create");  
        btn1.setBounds(410, 550, 100, 30);  
        frame.add(btn1);
        btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createSession(parentWind, controller);
				
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
	
	public void createSession(AppWindow parentWind, Controller controller)
	{
		String title_s = title.getText();
		SportEnum sport_enum = (SportEnum) sport.getSelectedItem();
		double distance_d = Double.parseDouble(distance.getText());
		Date start_date_d = (Date) start_date.getValue();
		LocalDate start_date_ld = convertToLocalDateViaInstant(start_date_d);
		String start_time_s = start_time.getText();
		LocalTime start_time_lt = LocalTime.parse(start_time_s, formatterTime);
		double duration_d = Double.parseDouble(duration.getText());
		
		controller.makeSession(parentWind.getToken(), 
				title_s, sport_enum, distance_d, 
				start_date_ld, start_time_lt, duration_d);
		parentWind.setEnabled(true);
		frame.dispose();
		
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	
	public static void main(String[] args) throws ParseException {
		// new CreateSessionDialog();
	}
}
