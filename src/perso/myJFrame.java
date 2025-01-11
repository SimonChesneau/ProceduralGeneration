package perso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.border.LineBorder;

import perso.algorithms.AStar;
import perso.algorithms.Dijkstra;
import perso.filter.CircleFilter;

import java.awt.Color;
import java.awt.Dimension;

public class myJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonPane;
	private JPanel framePane;
	public static int rowNumber=100;
	private int cellMargin=0;
	public static long seed = 0;
	public static Random rand;
	
	private static myJFrame frame;
	private static Thread generatingTerrain;
	
	public static myCell baseCell = new myCell(0,0, new CircleFilter());
	
	public static Map<String, myCell> cellMap = new HashMap<>();
	public static Map<String, Environment> environmentColorMap = new HashMap<>();
	public static List<Environment> baseEnvironmentColorList = new ArrayList<>();
	public static List<Environment> allEnvironmentColorList = new ArrayList<>();

	
	private static Coordinate startingPoint = new Coordinate(1, 1);
	private static Coordinate endingPoint = new Coordinate(rowNumber, rowNumber);
	private static Coordinate actualPoint;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		environmentColorMap.put("Beach", Environment.beachEnvironment);
		environmentColorMap.put("Ocean", Environment.oceanEnvironment);
		environmentColorMap.put("Plain", Environment.plainEnvironment);
		
		Random r = new Random();
		seed= r.nextLong();
		
		//seed = 4585045422125897541L;
		seed = -7444381420789894831L;
		System.out.println("seed: " + seed);
		rand = new Random(seed);
		

		for(Environment environment : environmentColorMap.values()) {
			baseEnvironmentColorList.add(environment);
			allEnvironmentColorList.add(environment);
		}
		
		allEnvironmentColorList.add(Environment.mountainEnvironment);
		allEnvironmentColorList.add(Environment.snowEnvironment);
		allEnvironmentColorList.add(Environment.forestEnvironment);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new myJFrame();					
					frame.setVisible(true);
					
					new Thread( () -> {
						frame.startFilter(10);
						frame.repaint();
						
						new Thread( () -> {
							startAstar();
						}).start();
						new Thread( () -> {
							startDijkstra();
						}).start();
					}).start();
					
					
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
		int width=0;
		int height=0;
		try {
			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int) screensize.getWidth();
			height = (int) screensize.getHeight();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width=1020;
		height=700;
		setBounds(0, 0, width, height);
		framePane = new JPanel();
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new GridLayout(rowNumber, 0, cellMargin, cellMargin));
		
		buttonPane = new JPanel();
		buttonPane.setBorder(new LineBorder(new Color(0,0,0),2));
		
		framePane.add(contentPane);
		framePane.add(buttonPane);
		
		setContentPane(contentPane);
		
		fillContent(contentPane);
	
		
		
	}
	
	private void startFilter(int iterations) {
		
		for(int currentIteration=0; currentIteration< iterations; currentIteration++) {
			for(int i=1 ; i< rowNumber+1 ; i++) {
				for(int j=1; j< rowNumber+1 ; j++) {
					Coordinate cellCoordinate = new Coordinate(i, j);
					//System.out.println(cellCoordinate.toString());
					myCell cell = cellMap.get(cellCoordinate.toString());
					cell.getFilter().applyColor();
				}
			}
		}
		
		for(int currentIteration=0; currentIteration< 10; currentIteration++) {
			for(int i=1 ; i< rowNumber+1 ; i++) {
				for(int j=1; j< rowNumber+1 ; j++) {
					Coordinate cellCoordinate = new Coordinate(i, j);
					myCell cell = cellMap.get(cellCoordinate.toString());
					cell.getFilter().fermeture(true);
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
//				System.out.print(i);
//				System.out.print(',');
//				System.out.println(j);
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
		
		Environment randomSelectedEnvironment = baseEnvironmentColorList.get(rand.nextInt(0, baseEnvironmentColorList.size()));
		cell.setEnvironment(randomSelectedEnvironment);
		cell.colorCell();
		
	}
	
	private static void startAstar() {
		AStar algo = new AStar(cellMap, startingPoint, endingPoint);
		algo.start();
		algo.colorPath();
	}
	
	private static void startDijkstra() {
		Dijkstra algo = new Dijkstra(cellMap, startingPoint, endingPoint);
		algo.start();
		algo.colorPath();
		
	}

}
