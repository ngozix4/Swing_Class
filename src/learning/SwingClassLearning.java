package learning;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class SwingClassLearning  extends JFrame{
	

	public SwingClassLearning()
	{
		// Set up main frame
		
		setTitle("Automation & Swing");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // center the frame on screen
		
		//Create a Tabbed Pane to hold multiple tabs
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//--------------- TAB 1: AUTOMATION INFO --------------------------
		JPanel automationPanel = new JPanel(new BorderLayout());
		
		// TextArea to display content
		JTextArea automationTextArea = new JTextArea();
		automationTextArea.setText("Automation Levels and Conceptual Framework:\n\n" +
	            "Manual Systems:\n" +
	            "- Human-driven, adaptable, low-cost\n" +
	            "- Pros: Flexibility, creativity, low infrastructure cost\n" +
	            "- Cons: Slow, error-prone, fatigue, inefficiency\n\n" +
	            "Fully Automated Systems:\n" +
	            "- Machine-driven, AI-powered, fast, precise\n" +
	            "- Pros: High efficiency, precision, scalability\n" +
	            "- Cons: High cost, safety & ethical concerns, job loss\n\n" +
	            "Key Difference:\n" +
	            "Manual → 0% automation, Fully Automated → 100% automation");
		
		automationTextArea.setEditable(false); // not editable
		JScrollPane automationScrollPane = new JScrollPane(automationTextArea); // add scrolling to the textarea
		automationPanel.add(automationScrollPane, BorderLayout.CENTER);
		
		// Add an image related to automation  
		JLabel imageLabel = new JLabel();
		ImageIcon icon =  new ImageIcon("automation.webp");
		imageLabel.setIcon(icon);
		automationPanel.add(imageLabel, BorderLayout.SOUTH); // Place the image below textt
		
		//------------------ TAB 2: SWING COMPONENTS -------------------------
		JPanel swingPanel = new JPanel();
		swingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
		
		JLabel lable = new JLabel("Choose your preferred SWING Component");
		swingPanel.add(lable);
		
		// ComboBox  for Swing Components
		String[] components = {"JButton", "JCheckBox", "JTextField", "JTabbedPane"};
		JComboBox<String> comboBox = new JComboBox<>(components);
		swingPanel.add(comboBox);
		
		//Button to show selection
		JButton showButton = new JButton("Show Selected");
		JLabel resultLabel = new JLabel("Section will appear here");
		swingPanel.add(showButton);
		swingPanel.add(resultLabel);
		
		// Event handling for button click
		showButton.addActionListener(e -> {
			String selected = (String) comboBox.getSelectedItem();
			resultLabel.setText("You selected: " + selected);
		});
		
		// CheckBoxes for extra options
		JCheckBox chk1 = new JCheckBox("Enable Dark Mode");
		JCheckBox chl2 = new JCheckBox("Enable Notifications");
		swingPanel.add(chk1);
		swingPanel.add(chl2);
		
		// Radio Buttons for difficulty selection
		JRadioButton rb1 = new JRadioButton("Beginner");
		JRadioButton rb2 = new JRadioButton("Intermediate");
		JRadioButton rb3 = new JRadioButton("Advanced");
		ButtonGroup group = new ButtonGroup();
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		swingPanel.add(rb1);
		swingPanel.add(rb2);
		swingPanel.add(rb3);
		
		// TextField for user input
		JLabel nameLabel = new JLabel("Enter your name: ");
		JTextField nameField = new JTextField(15);
		JButton greetButton = new JButton("Greet");
		JLabel greetLabel = new JLabel("");
		swingPanel.add(nameLabel);
		swingPanel.add(nameField);
		swingPanel.add(greetButton);
		swingPanel.add(greetLabel);
		
		//Event for greeting Button
		greetButton.addActionListener(e -> {
			String name = nameField.getText();
			greetLabel.setText("Hello, " + name + "!");
		});
		
		//------------- TAB 3: TABLE WITH INFO ------------------
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		// Table data for Automation comparison
		String[] columnNames = {"Aspect", "Manual System", "Fully Automated System"};
		Object[][] data = {
				{"Control", "Human-driven", "Machine Learning"},
				{"cost", "Low", "High"},
				{"Flexibility", "High", "Low"},
				{"Speed", "Slow", "Fast"},
				{"Error", "High", "Low"}
		};
		
		JTable table = new JTable(new DefaultTableModel(data, columnNames));
		JScrollPane tableScrollPane = new JScrollPane(table);
		tablePanel.add(tableScrollPane, BorderLayout.CENTER);
		
		//Add Tabs to TabbedPane
		tabbedPane.add("Automation Info", automationPanel);
		tabbedPane.add("Swing Components", swingPanel);
		tabbedPane.add("Comparison table", tablePanel);
		
		//Add TaabedPane to the Frame
		add(tabbedPane);
		
		//Make the frame visible
		setVisible(true);
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(SwingClassLearning::new);
	}
}
