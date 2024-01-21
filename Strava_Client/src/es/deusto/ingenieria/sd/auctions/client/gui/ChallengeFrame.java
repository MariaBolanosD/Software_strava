package es.deusto.ingenieria.sd.auctions.client.gui;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.Controller;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFrame extends JFrame {

    private DefaultListModel<ChallengeDTO> challengeListModel;
    private JTextArea detailsTextArea;
    private String challengeDetail;

    public ChallengeFrame(AppWindow appWindow, Controller controller) {
        super("Challenge List");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear lista de desafíos
        List<ChallengeDTO> challenges = controller.getChallenges();

        // Crear el modelo de lista y agregar los desafíos
        challengeListModel = new DefaultListModel<>();
        for (ChallengeDTO challenge : challenges) {
            challengeListModel.addElement(challenge);
        }

        // Crear la lista y agregar el modelo de lista a la lista
        JList<ChallengeDTO> challengeList = new JList<>(challengeListModel);
        challengeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        challengeList.setBackground(new Color(255, 245, 225));
        challengeList.setForeground(Color.DARK_GRAY);
        challengeList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        challengeList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Crear el JScrollPane y agregar la lista a él
        JScrollPane listScrollPane = new JScrollPane(challengeList);

        // Crear el área de texto para mostrar los detalles
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setBackground(new Color(255, 245, 225));
        detailsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Crear el JScrollPane y agregar el área de texto a él
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        int j = challengeList.getSelectedIndex();
        challengeDetail =   "Name :           " + challenges.get(j).getName() + '\n' +
      		"Sport :         " + challenges.get(j).getSport() + '\n' + 
      		"Start_date : " + challenges.get(j).getStartDate()+ '\n' + 
      		"EndDate : " + challenges.get(j).getEndDate()+'\n'+
      		"Target :    " + challenges.get(j).getTarget();
		if(challenges.get(j).getDistanceorTime() == true)
        {
			challengeDetail += " km";
        }
        else {
        	challengeDetail += " min";
        }
		detailsTextArea = new JTextArea(challengeDetail);

        // Crear el botón "Accept Challenge"
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(255, 165, 0));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				appWindow.setEnabled(true);
				dispose();

			}
		});
		
        JButton acceptButton = new JButton("Accept Challenge");
        acceptButton.setBackground(new Color(255, 165, 0));
        acceptButton.setForeground(Color.WHITE);
        acceptButton.setFocusPainted(false);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChallengeDTO selectedChallenge = challengeList.getSelectedValue();
                if (selectedChallenge != null) {
                    detailsTextArea.setText("Challenge accepted: " + selectedChallenge);
                    controller.getAcceptedChallenges(appWindow.getToken()).add(selectedChallenge);
                    JOptionPane.showMessageDialog(ChallengeFrame.this, "Challenge accepted: " + selectedChallenge, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ChallengeFrame.this, "Selecciona un desafío primero", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configurar el diseño del JFrame usando JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScrollPane, detailsScrollPane);
        splitPane.setDividerLocation(250); // Ajusta la ubicación del divisor vertical

        // Crear un JPanel para organizar mejor la interfaz
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(255, 245, 225));

        // Agregar componentes al panel
        panel.add(splitPane, BorderLayout.CENTER);
        JPanel pl = new JPanel(new FlowLayout());
        pl.add(cancelButton);
        pl.add(acceptButton);
        panel.add(pl, BorderLayout.SOUTH);

        // Agregar el panel al JFrame
        add(panel);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // new ChallengeFrame();
            }
        });
    }

}
