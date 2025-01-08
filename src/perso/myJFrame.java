package perso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.border.LineBorder;

import perso.filter.CircleFilter;

import java.awt.Color;

public class myJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int rowNumber=100;
	private int cellMargin=0;
	
	public static myCell baseCell = new myCell(0,0, new CircleFilter());
	
	public static Map<String, myCell> cellMap = new HashMap<>();
	public static Map<String, Environment> environmentColorMap = new HashMap<>();
	public static List<Environment> environmentColorList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		environmentColorMap.put("Beach", Environment.beachEnvironment);
		environmentColorMap.put("Ocean", Environment.oceanEnvironment);
		environmentColorMap.put("Plain", Environment.plainEnvironment);
		

		for(Environment environment : environmentColorMap.values())
			environmentColorList.add(environment);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myJFrame frame = new myJFrame();
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
	public myJFrame() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(rowNumber, 0, cellMargin, cellMargin));
		
		
		fillContent(contentPane);
		
		startFilter(10);
		
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(0, 255, 0));
//		panel.setForeground(new Color(0, 255, 0));
//		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
//		contentPane.add(panel);
		
		
	}
	
	private void startFilter(int iterations) {
		
		for(int currentIteration=0; currentIteration< iterations; currentIteration++) {
			for(int i=1 ; i< rowNumber+1 ; i++) {
				for(int j=1; j< rowNumber+1 ; j++) {
					Coordinate cellCoordinate = new Coordinate(i, j);
					System.out.println(cellCoordinate.toString());
					myCell cell = cellMap.get(cellCoordinate.toString());
					cell.getFilter().applyColor();
				}
			}
		}
		
	}

	private void fillContent(JPanel parentPanel) {
		
		myCell cell = new myCell(0,0, new CircleFilter());
		
		for(int i= 1; i< rowNumber+1 ; i++) {
			for(int j= 1; j< rowNumber+1 ; j++) {
				cell = new myCell(i,j, new CircleFilter());
				parentPanel.add(cell);
				
				Coordinate coordinates = new Coordinate(i,j);
				cellMap.put(i+","+j, cell);
				System.out.print(i);
				System.out.print(',');
				System.out.println(j);
			}
		}
		
		
		for(String cellStringCoordinates : cellMap.keySet()) {
			myCell relatedCell = cellMap.get(cellStringCoordinates);
			
			relatedCell.getFilter().setNeighbours();
			setRandomColor(relatedCell);
		}
		
		cellMap.get("25,25").getFilter().applyColor();
		
//		cellMap.get("25,25").setBackground(new Color(255,0,0));
		
		
		
	}
	
	private void setRandomColor(myCell cell) {
		Random r = new Random();
		Environment randomSelectedEnvironment = environmentColorList.get(r.nextInt(0, environmentColorList.size()));
		cell.setEnvironment(randomSelectedEnvironment);
		cell.colorCell();
		
	}

}
