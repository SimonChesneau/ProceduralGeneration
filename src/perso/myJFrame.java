package perso;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.border.LineBorder;

import perso.algorithms.AStar;
import perso.algorithms.Dijkstra;
import perso.algorithms.SearchAlgorithm;
import perso.filter.CircleFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class myJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel buttonPane;
	private JPanel framePane;
	public static int rowNumber=100;
	public static int filterIteration=10;
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
	
	
	private JPanel contentPane;
	private final static JPanel mapPannel = new JPanel();
	private final JPanel buttonPannel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton newMapButton = new JButton("Generate a new map");
	private final JButton startAStarButton = new JButton("Start A*");
	private final JButton startDijkstraButton = new JButton("Start Dijkstra");
	private final JButton cleanMapButton = new JButton("Clean map");
	private final static JLabel seedLabel = new JLabel("Seed:");
	private final JPanel parameterPanel = new JPanel();
	
	private final JPanel mapSizePanel = new JPanel();
	private final JLabel mapSizeLabel = new JLabel("Map size");
	private final JSpinner mapSizeSpinner = new JSpinner();
	private final JPanel filterIterationPanel = new JPanel();
	private final JLabel filterIterationLabel = new JLabel("Filter iteration");
	private final JSpinner filterIterationSpinner = new JSpinner();
	private final JPanel islandPresencePanel = new JPanel();
	private final JLabel islandPresenceLabel = new JLabel("Island presence");
	private final JSpinner islandPresenceSpinner = new JSpinner();
	private final JPanel islandSizePanel = new JPanel();
	private final JLabel islandSizeLabel = new JLabel("Island size");
	private final JSpinner islandSizeSpinner = new JSpinner();
	private final JPanel mountainPresencePanel = new JPanel();
	private final JLabel mountainPresenceLabel = new JLabel("Mountain presence");
	private final JSpinner mountainPresenceSpinner = new JSpinner();
	private final JPanel mountainSizePanel = new JPanel();
	private final JLabel mountainSizeLabel = new JLabel("Mountain size");
	private final JSpinner mountainSizeSpinner = new JSpinner();
	private final JPanel snowPresencePanel = new JPanel();
	private final JLabel snowPresenceLabel = new JLabel("Snow presence");
	private final JSpinner snowPresenceSpinner = new JSpinner();
	private final JPanel forestPresencePanel = new JPanel();
	private final JLabel forestPresenceLabel = new JLabel("Forest presence");
	private final JSpinner forestPresenceSpinner = new JSpinner();
	private final JPanel setSeedPanel = new JPanel();
	private final JCheckBox setSeedCheckbox = new JCheckBox("Use set seed ?");
	private final JTextField setSeedTextField = new JTextField();
	
	private static List<SearchAlgorithm> usedAlgo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		usedAlgo = new ArrayList<>();
		
		environmentColorMap.put("Beach", Environment.beachEnvironment);
		environmentColorMap.put("Ocean", Environment.oceanEnvironment);
		environmentColorMap.put("Plain", Environment.plainEnvironment);
		
		newSeed(0L);
		
		//seed = 4585045422125897541L;
		
		

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
					
					/*new Thread( () -> {
						myJFrame.fillContent();
						frame.startFilter(10);
						frame.repaint();
						
						new Thread( () -> {
							startAstar();
						}).start();
						new Thread( () -> {
							startDijkstra();
						}).start();
					}).start();*/
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}

	private static void newSeed(long givenSeed) {
		if(givenSeed == 0) {
			Random r = new Random();
			seed= r.nextLong();
		}else
			seed = givenSeed;
		
		System.out.println("seed: " + seed);
		seedLabel.setText("Seed: " +seed);
		rand = new Random(seed);
	}

	/**
	 * Create the frame.
	 */
	public myJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		mapPannel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mapPannel.setLayout(new GridLayout(rowNumber, 0, cellMargin, cellMargin));
		
		contentPane.add(mapPannel);
		
		FlowLayout flowLayout = (FlowLayout) buttonPannel.getLayout();
		buttonPannel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(buttonPannel, BorderLayout.SOUTH);
		buttonPannel.add(newMapButton);
		buttonPannel.add(startAStarButton);
		buttonPannel.add(startDijkstraButton);
		buttonPannel.add(cleanMapButton);
		buttonPannel.add(seedLabel);
		
		parameterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(parameterPanel, BorderLayout.EAST);
		parameterPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		mapSizePanel.setLayout(new GridLayout(0, 2, 0, 0));
		parameterPanel.add(mapSizePanel);
		mapSizePanel.add(mapSizeLabel);
		mapSizePanel.add(mapSizeSpinner);
		filterIterationPanel.setLayout(new GridLayout(0, 2, 0, 0));
		parameterPanel.add(filterIterationPanel);
		filterIterationPanel.add(filterIterationLabel);
		filterIterationPanel.add(filterIterationSpinner);
		
		
		mapSizePanel.setLayout(new GridLayout(0, 2, 0, 0));
		parameterPanel.add(mapSizePanel);
		mapSizePanel.add(mapSizeLabel);
		mapSizePanel.add(mapSizeSpinner);
		
		islandPresencePanel.setLayout(new GridLayout(0, 2, 0, 0));
		parameterPanel.add(islandPresencePanel);
		islandPresencePanel.add(islandPresenceLabel);
		islandPresencePanel.add(islandPresenceSpinner);
		parameterPanel.add(islandSizePanel);
		islandSizePanel.setLayout(new GridLayout(0, 2, 0, 0));
		islandSizePanel.add(islandSizeLabel);
		islandSizePanel.add(islandSizeSpinner);
		parameterPanel.add(mountainPresencePanel);
		mountainPresencePanel.setLayout(new GridLayout(0, 2, 0, 0));
		mountainPresencePanel.add(mountainPresenceLabel);
		mountainPresencePanel.add(mountainPresenceSpinner);
		parameterPanel.add(mountainSizePanel);
		mountainSizePanel.setLayout(new GridLayout(0, 2, 0, 0));
		mountainSizePanel.add(mountainSizeLabel);
		mountainSizePanel.add(mountainSizeSpinner);
		parameterPanel.add(snowPresencePanel);
		snowPresencePanel.setLayout(new GridLayout(0, 2, 0, 0));
		snowPresencePanel.add(snowPresenceLabel);
		snowPresencePanel.add(snowPresenceSpinner);
		parameterPanel.add(forestPresencePanel);
		forestPresencePanel.setLayout(new GridLayout(0, 2, 0, 0));
		forestPresencePanel.add(forestPresenceLabel);
		forestPresencePanel.add(forestPresenceSpinner);
		
		parameterPanel.add(setSeedPanel);
		setSeedPanel.setLayout(new GridLayout(0, 2, 0, 0));
		setSeedPanel.add(setSeedCheckbox);
		setSeedPanel.add(setSeedTextField);
		
		mapSizeSpinner.setValue(rowNumber);
		filterIterationSpinner.setValue(filterIteration);
		islandPresenceSpinner.setValue(CircleFilter.getIslandNumberFactor());
		islandSizeSpinner.setValue(CircleFilter.getIslandSizeFactor());
		mountainPresenceSpinner.setValue(CircleFilter.getMountainPresenceFactor());
		mountainSizeSpinner.setValue(CircleFilter.getMountainSizeFactor());
		snowPresenceSpinner.setValue(CircleFilter.getSnowPresenceFactor());
		forestPresenceSpinner.setValue(CircleFilter.getForestPresenceFactor());
		
		setSeedTextField.setText(seed+"");
		setSeedTextField.setEnabled(false);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		mapSizeSpinner.addChangeListener(e -> {
			startAStarButton.setEnabled(false);
			startDijkstraButton.setEnabled(false);
			cleanMapButton.setEnabled(false);
            int value = (int) mapSizeSpinner.getValue(); 
            rowNumber = value;
            endingPoint = new Coordinate(rowNumber, rowNumber);
            mapPannel.revalidate();
            mapPannel.repaint();
        });
		filterIterationSpinner.addChangeListener(e -> {
            int value = (int) filterIterationSpinner.getValue(); 
            filterIteration = value;
        });
		islandPresenceSpinner.addChangeListener(e -> {
            int value = (int) islandPresenceSpinner.getValue(); 
            CircleFilter.setIslandNumberFactor(value);
        });
		islandSizeSpinner.addChangeListener(e -> {
            int value = (int) islandSizeSpinner.getValue(); 
            CircleFilter.setIslandSizeFactor(value);
        });
		mountainPresenceSpinner.addChangeListener(e -> {
            int value = (int) mountainPresenceSpinner.getValue(); 
            CircleFilter.setMountainPresenceFactor(value);
        });
		mountainSizeSpinner.addChangeListener(e -> {
            int value = (int) mountainSizeSpinner.getValue(); 
            CircleFilter.setMountainSizeFactor(value);
        });
		snowPresenceSpinner.addChangeListener(e -> {
            int value = (int) snowPresenceSpinner.getValue(); 
            CircleFilter.setSnowPresenceFactor(value);
        });
		forestPresenceSpinner.addChangeListener(e -> {
            int value = (int) forestPresenceSpinner.getValue(); 
            CircleFilter.setForestPresenceFactor(value);
        });
		
		setSeedCheckbox.addItemListener(e ->{
			if (e.getStateChange() == ItemEvent.SELECTED)
                setSeedTextField.setEnabled(true);
            else
                setSeedTextField.setEnabled(false);	
		});
		
		
		newMapButton.addActionListener(e ->{
			
			new Thread(() ->{
				if(setSeedCheckbox.isSelected())
					newSeed(Long.parseLong(setSeedTextField.getText()));
				else
					newSeed(0L);
	            mapPannel.setLayout(new GridLayout(rowNumber, 0, cellMargin, cellMargin));
				newMapButton.setEnabled(false);
				startAStarButton.setEnabled(false);
				startDijkstraButton.setEnabled(false);
				cleanMapButton.setEnabled(false);
				System.out.println("Generating a new map");
				fillContent();
				frame.revalidate();
				frame.repaint();
				
				startFilter(filterIteration);
				System.out.println("New map generated");
				mapPannel.revalidate();
				mapPannel.repaint();
				frame.revalidate();
				frame.repaint();
				cleanMapButton.setEnabled(true);
				startDijkstraButton.setEnabled(true);
				startAStarButton.setEnabled(true);
				newMapButton.setEnabled(true);
			}).start();
		});
		
		startAStarButton.addActionListener(e -> {
			new Thread(() ->{
				startAStarButton.setEnabled(false);
				cleanMapButton.setEnabled(false);
				startAstar();
				revalidate();
				repaint();
				cleanMapButton.setEnabled(true);
				startAStarButton.setEnabled(true);
			}).start();
		});
		
		startDijkstraButton.addActionListener(e -> {
			new Thread(() ->{
				startDijkstraButton.setEnabled(false);
				cleanMapButton.setEnabled(false);
				startDijkstra();
				revalidate();
				repaint();
				cleanMapButton.setEnabled(true);
				startDijkstraButton.setEnabled(true);
			}).start();
		});

		cleanMapButton.addActionListener(e ->{
			new Thread(() ->{
				cleanMapButton.setEnabled(false);
				cleanMap();
				cleanMapButton.setEnabled(true);
			}).start();
		});
		
		//fillContent();
	
		
		
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

	private static void fillContent() {
		mapPannel.removeAll();
		cellMap.clear();
		
		myCell cell = new myCell(0,0, new CircleFilter());
		
		//int coreNumber = Runtime.getRuntime().availableProcessors();
		//List<Thread> threadToWaitFor = new ArrayList<>();
		
		//int rowNumberPerCore = rowNumber/coreNumber;
		//int leftover = rowNumber - (rowNumberPerCore*coreNumber);
		//for(int k=0 ; k< coreNumber ; k++) {
			//final int currentK = k; // Capturer la valeur de k pour chaque thread
			
			//Thread generatingThread = new Thread(() -> {
				//for(int i=currentK*rowNumberPerCore+1; i< (currentK+1)*rowNumberPerCore+1 ; i++) {
				for(int i= 1; i<= rowNumber ; i++) {
					for(int j= 1; j<= rowNumber ; j++) {
						myCell cell2 = new myCell(i,j, new CircleFilter());
						mapPannel.add(cell2);
						
						Coordinate coordinates = new Coordinate(i,j);
						cellMap.put(coordinates.toString(),cell2);
						System.out.print(i);
						System.out.print(',');
						System.out.println(j);
					}
				}
			//});
			//threadToWaitFor.add(generatingThread);
			
		//}
		/*Thread generatingThread = new Thread(() -> {
			for(int i = rowNumber ; i > rowNumber-leftover; i--) {
				for(int j = 0 ; j <= rowNumber; j++) {
					myCell cell2 = new myCell(i,j, new CircleFilter());
					mapPannel.add(cell2);
					
					Coordinate coordinates = new Coordinate(i,j);
					cellMap.put(coordinates.toString(), cell2);
					System.out.print(i);
					System.out.print(',');
					System.out.println(j);
				}
			}
		});
		threadToWaitFor.add(generatingThread);
		
		
		
		for(Thread thread : threadToWaitFor) {
			try {
				thread.start();
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		
		for(String cellStringCoordinates : cellMap.keySet()) {
			myCell relatedCell = cellMap.get(cellStringCoordinates);
			
			relatedCell.getFilter().setNeighbours();
			setRandomColor(relatedCell);
		}
	
		
		
		
	}
	
	private static void setRandomColor(myCell cell) {
		
		Environment randomSelectedEnvironment = baseEnvironmentColorList.get(rand.nextInt(0, baseEnvironmentColorList.size()));
		cell.setEnvironment(randomSelectedEnvironment);
		cell.colorCell();
		
	}
	
	private static void startAstar() {
		AStar algo = new AStar(cellMap, startingPoint, endingPoint);
		usedAlgo.add(algo);
		algo.start();
		algo.colorPath();
	}
	
	private static void startDijkstra() {
		Dijkstra algo = new Dijkstra(cellMap, startingPoint, endingPoint);
		usedAlgo.add(algo);
		algo.start();
		algo.colorPath();
		
	}
	
	private static void cleanMap() {
		for(SearchAlgorithm algo : usedAlgo)
			for(myCell cell : algo.getFoundPath().values()) {
				if(cell != null)
					cell.colorCell();
			}
		
		usedAlgo.clear();
		
	}

}
