import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ResponsibleParty extends JFrame{

	JFrame mainFrame;
	JPanel hdPanel, bgPanel, formPanel; 
	JLabel hdLabel, shdLabel, titleLabel, dateLabel;
	JButton backButton, nextButton;
	
	ImageIcon logo, mainBG;
	
	public void SecondFrame() {
		
		logo = new ImageIcon("logo.png");
		Image image = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		mainBG = new ImageIcon("bg.png");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    hdPanel = new JPanel();
		hdPanel.setBackground(new Color(180, 227, 192));
		hdPanel.setBounds(0, 0, screenSize.width, 70);
		hdPanel.setLayout(new BoxLayout(hdPanel, BoxLayout.Y_AXIS));

		mainFrame = new JFrame("I-70 Clinic");
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setSize(screenSize.width - 100, screenSize.height - 100);
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);	
		mainFrame.setLayout(null);
		mainFrame.setIconImage(logo.getImage());
		
		JPanel headerRow = new JPanel();
		headerRow.setOpaque(false); // to make it transparent
		headerRow.setLayout(new FlowLayout(FlowLayout.CENTER, 20,3));

		JLabel logoLabel = new JLabel(new ImageIcon(image));
		headerRow.add(logoLabel);
		
		hdLabel = new JLabel("I-70 MEDICAL CLINIC REGISTRATION FORM");
		hdLabel.setFont(new Font("Arial", Font.BOLD, 24));
		headerRow.add(hdLabel);
		
		shdLabel = new JLabel("Please print and fill-out completely");
		shdLabel.setFont(new Font("Arial", Font.ITALIC, 14));
		shdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		hdPanel.add(headerRow);
		hdPanel.add(shdLabel);
		
		bgPanel = new JPanel() {
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(mainBG.getImage(), 0, 0, getWidth(), getHeight(), this);
        	}
		};
		bgPanel.setLayout(new BorderLayout());
		
		formPanel = new JPanel(new GridBagLayout());
		formPanel.setOpaque(false);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(20, 10, 20, 10);
	    gbc.fill = GridBagConstraints.HORIZONTAL;
		
		titleLabel = new JLabel("Responsible Party");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 6;
		formPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;
        
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate currentDate = LocalDate.now();
        dateLabel = new JLabel("Date: " + date.format(currentDate));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        hdPanel.add(dateLabel, gbc);
        
		addLabelTextField(formPanel, gbc, "Person Responsible for Bill ", 0, 1);
		addBirthdateComboBoxes(formPanel, gbc, 1, 1);
		addLabelTextField(formPanel, gbc, "Address (If Different)", 0, 2);
		addLabelTextField(formPanel, gbc, "Phone Number", 1, 2);
		
		addLabelTextField(formPanel, gbc, "Occupation", 0, 3);
		addLabelTextField(formPanel, gbc, "Employer", 1, 3);
		addLabelTextField(formPanel, gbc, "Employer Address", 0, 4);
		addLabelTextField(formPanel, gbc, "Employer Phone Number", 1, 4);
		
		backButton = new JButton("Back");
        gbc.gridx = 7;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        formPanel.add(backButton, gbc);

		nextButton = new JButton("Next");
        gbc.gridx = 8;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        formPanel.add(nextButton, gbc);
		
		bgPanel.add(hdPanel, BorderLayout.NORTH);
		bgPanel.add(formPanel, BorderLayout.CENTER);
		mainFrame.setContentPane(bgPanel);
	}

	private void addLabelTextField(JPanel panel, GridBagConstraints gbc, String label, int x, int y) {
		gbc.gridx = x * 2;
		gbc.gridy = y;
		JLabel tempLabel = new JLabel(label + ":");
	    tempLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    panel.add(tempLabel, gbc);
		gbc.gridx = x * 2 + 1; 
		gbc.gridy = y;
		panel.add(new JTextField(15), gbc);
	}

	// helper method for dates
	private void addBirthdateComboBoxes(JPanel panel, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x * 2;
		gbc.gridy = y;
		JLabel label = new JLabel("Birthdate:");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(label, gbc);

		gbc.gridx = x * 2 + 1;
		gbc.gridy = y;

		JPanel birthdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		String[] months = {
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		};

		String[] days = new String[31];
		for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);

		String[] years = new String[100];
		int currentYear = LocalDate.now().getYear();
		for (int i = 0; i < 100; i++) years[i] = String.valueOf(currentYear - i);

		birthdatePanel.add(new javax.swing.JComboBox<>(months));
		birthdatePanel.add(new javax.swing.JComboBox<>(days));
		birthdatePanel.add(new javax.swing.JComboBox<>(years));

		panel.add(birthdatePanel, gbc);
	}


	public static void main(String[] args) {
            ResponsibleParty runFrame = new ResponsibleParty();
            runFrame.SecondFrame();
    }

}
