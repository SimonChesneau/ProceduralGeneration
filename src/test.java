import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel Map = new JPanel();
	private final JPanel Button = new JPanel();
	private final JButton btnNewButton = new JButton("New button");
	private final JButton btnNewButton_1 = new JButton("New button");
	private final JLabel lblNewLabel = new JLabel("New label");
	private final JPanel parameterPanel = new JPanel();
	private final JPanel islandPresencePanel = new JPanel();
	private final JLabel islandPresenceLabel = new JLabel("New label");
	private final JSpinner islandPresenceSpinner = new JSpinner();
	private final JPanel setSeedPanel = new JPanel();
	private final JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		Map.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(Map);
		FlowLayout flowLayout = (FlowLayout) Button.getLayout();
		Button.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(Button, BorderLayout.SOUTH);
		
		Button.add(btnNewButton);
		
		Button.add(btnNewButton_1);
		
		Button.add(lblNewLabel);
		parameterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(parameterPanel, BorderLayout.EAST);
		parameterPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		parameterPanel.add(islandPresencePanel);
		islandPresencePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		islandPresencePanel.add(islandPresenceLabel);
		
		islandPresencePanel.add(islandPresenceSpinner);
		
		parameterPanel.add(setSeedPanel);
		
		setSeedPanel.add(chckbxNewCheckBox);
		textField.setColumns(10);
		
		setSeedPanel.add(textField);
	}

}
