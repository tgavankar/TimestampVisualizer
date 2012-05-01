package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

import visualize.DateRange;
import visualize.FileObject;
import actions.ClickChartAction;
import actions.DummyAction;
import actions.ExportAsPDFAction;
import actions.ExportAsPNGAction;
import actions.NumPanelsAction;
import actions.UndoZoomAction;

public class MasterPanel extends ApplicationFrame
{

	private static final long serialVersionUID = 1L;
	JPanel panel;
	public int numCharts = 1; //used to tell if we are viewing 1 or 2 charts
	
	
	//dimensions for various parts of the panel
	Dimension singleChartD = new Dimension(800, 600);
	Dimension dualChartD = new Dimension(800, 300);
	Dimension sideDimension = new Dimension(300,600);
	
	//represents which side panel to show
	//first digit represents which chart (0 for single, 1 for dual chart 1, 2 for dual chart 2)
	//second digit represents data/view (0 for data, 1 for view)
	public int sidePanel = 00; 
	
	ArrayList<FileObject> allFiles;
	
	//DateRange[] data = new DateRange[3];

	public DateRange[] view = new DateRange[3];
	
	//these colors represent the color of the side panel when you are editing
	//the single chart, the dual chart 1, and the dual chart 2
	//TODO choose better colors
	Color[] colors = {Color.gray, Color.gray, Color.gray};
	
	/*
	 * start single chart global variables
	 */
	//TODO use title
	String title; 
	

	public CategoryDataset dataset; 
	
		
	//DataPanel dataPanel;
	public ViewPanel prefPanel;
	//end single chart global variables
	
	
	
	/*
	 * start dual char1 global variables
	 */
	String title1;
	
	public CategoryDataset dataset1;
	
	//DataPanel dataPanel1;
	public ViewPanel prefPanel1;	
	//end dual chart1 global variables
	
	
	
	/*
	 * start dual chart2 global variables
	 */
	String title2;
	

	public CategoryDataset dataset2;
	
	//DataPanel dataPanel2;
	public ViewPanel prefPanel2;	
	//end dual chart2 global variables
	
	public MasterPanel(String title, ArrayList<FileObject> all)
	{
		super(title);
		allFiles = all;

		for(int i =0; i < 3; i++)
		{
			//data[i] = new DateRange();
			view[i] = new DateRange();
			view[i].getDataSet(all);
		}
		

		//dataPanel = new DataPanel(this, data[0], sideDimension, colors[0], "Single Chart: Data Selection");
		prefPanel = new ViewPanel(this, view[0], sideDimension, colors[0], "Single Chart: View Selection");
		
		//dataPanel1 = new DataPanel(this, data[1], sideDimension, colors[1], "Dual Chart 1: Data Selection");
		prefPanel1 = new ViewPanel(this, view[1], sideDimension, colors[1], "Dual Chart 1: View Selection");
		
		
		//dataPanel2 = new DataPanel(this, data[2], sideDimension, colors[2], "Dual Chart 2: Data Selection");
		prefPanel2 = new ViewPanel(this, view[2], sideDimension, colors[2], "Dual Chart 2: View Selection");
		

		panel = new JPanel(new GridBagLayout());
		redraw();
	}
	
	public void redraw()
	{
		switch(numCharts)
		{
			case 1:
				redraw1();
				break;
			case 2:
				redraw2();
				break;
		}
	}

	//draws panel with 1 chart
	public void redraw1()
	{
		//ArrayList<FileObject> selectedFiles = view[0].filterFiles(allFiles);
		//dataset = view[0].getDataSet(selectedFiles);
				
		panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		
		JMenuBar menubar = getBar();
		c.gridx = 0; c.gridy = 0;
		panel.add(menubar,c);
		
		JFreeChart chart = createChart(view[0]);
		ChartPanel p = new ChartPanel(chart);
		p.setPreferredSize(singleChartD);///////////////////////////////////
		p.addChartMouseListener(new ClickChartAction(view[0], this));
		c.gridy=1;		
		panel.add(p, c);
		
		
		c.gridx=2;
		panel.add(getSidePanel(), c);
		
		setContentPane(panel);
		
	}

	//draws panel with 2 charts
	public void redraw2()
	{
		/*
		ArrayList<FileObject> selectedFiles1 = view[1].filterFiles(allFiles);
		dataset1 = view[1].getDataSet(selectedFiles1);
		
		ArrayList<FileObject> selectedFiles2 = view[2].filterFiles(allFiles);
		dataset2 = view[2].getDataSet(selectedFiles2);
		*/	
				
		panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		
		JMenuBar menubar = getBar();
		c.gridx = 0; c.gridy = 0;
		panel.add(menubar,c);
		
		
		JPanel chartPanel = new JPanel(new GridBagLayout());
		GridBagConstraints chartC = new GridBagConstraints();
		
		JFreeChart chart1 = createChart(view[1]);
		chart1.setBorderPaint(Color.black);
		chart1.setBorderVisible(true);
		chart1.setBorderStroke(new BasicStroke(4f));
		ChartPanel p1 = new ChartPanel(chart1);
		p1.setPreferredSize(dualChartD);///////////////////////////////////
		p1.addChartMouseListener(new ClickChartAction(view[1], this));
		chartC.gridx=0;
		chartC.gridy=0;		
		chartPanel.add(p1, chartC);
		
		
		JFreeChart chart2 = createChart(view[2]);
		chart2.setBorderPaint(Color.black);
		chart2.setBorderVisible(true);
		chart2.setBorderStroke(new BasicStroke(4f));
		ChartPanel p2 = new ChartPanel(chart2);
		p2.setPreferredSize(dualChartD);///////////////////////////////////
		p2.addChartMouseListener(new ClickChartAction(view[2], this));
		chartC.gridx=0;
		chartC.gridy=1;			
		chartPanel.add(p2, chartC);
		
		c.gridy=1;
		panel.add(chartPanel, c);
		
		c.gridx=2;
		panel.add(getSidePanel(), c);
		
		
		setContentPane(panel);
		
	}
	
	public Component getSidePanel()
	{
		
		//first digit represents which chart
		//second digit represents data/view
		switch(sidePanel)
		{
			case 0:
				return prefPanel.panel;
			case 1:
				return prefPanel1.panel;
			case 2:
				return prefPanel2.panel;
		}
		
		return null;
	}
	
	private JFreeChart createChart(DateRange d ) 
	{
		//TODO change color of bars to match the associated data/view panels 
		//using the 'colors' variable
		
		//TODO label axis properly

		CategoryDataset dataset = d.getDataSet(d.filterFiles(allFiles));
		String xLabel = "";
		switch (d.g)
		{
		case YEARS:
			xLabel = "year"; break;
		case MONTHS:
			xLabel = "month"; break;
		case DAYS_OF_WEEK:
			xLabel = "day of the week"; break;
		case DAYS:
			xLabel = "day of the month"; break;
		case HOURS:
			xLabel = "hour of the day"; break;
		case MINUTES:
			xLabel = "minute"; break;
		case SECONDS:
			xLabel = "second"; break;
		}
		
		JFreeChart chart = ChartFactory.createBarChart(
				title, xLabel, "# files accessed", dataset, PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}

	public JMenuBar getBar()
	{
		JMenuBar mainMenuBar;

		JMenu data, help, undo;
		JMenuItem plainTextMenuItem;
		
		JRadioButtonMenuItem rbMenuItem;
		//ImageIcon icon = createImageIcon("jmenu.jpg");
		
		
		mainMenuBar = new JMenuBar();
		data = new JMenu("Select Format");
		data.setMnemonic(KeyEvent.VK_D);
		mainMenuBar.add(data);
		//create items for data menu
		rbMenuItem = new JRadioButtonMenuItem( "Single Graph");
		rbMenuItem.setSelected((1==numCharts));
		rbMenuItem.addActionListener(new NumPanelsAction(this, 1, 0));
		data.add(rbMenuItem);
		data.addSeparator();	
			
		rbMenuItem = new JRadioButtonMenuItem( "Double Graph");
		rbMenuItem.setSelected((2==numCharts));
		rbMenuItem.addActionListener(new NumPanelsAction(this, 2,1));
		data.add(rbMenuItem);
		mainMenuBar.add(data);
		

		
		
		/*
		//create items for view section
		view = new JMenu("View Selection");
		view.setMnemonic(KeyEvent.VK_V);
		mainMenuBar.add(view);
		
		ButtonGroup itemGroup = new ButtonGroup();

		
		submenu = new JMenu("Single Graph");
		rbMenuItem = new JRadioButtonMenuItem( "Single Graph");
		rbMenuItem.setSelected((1==numCharts));
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		itemGroup.add(rbMenuItem);
		rbMenuItem.addActionListener(new NumPanelsAction(this, 1));
		submenu.add(rbMenuItem);
		plainTextMenuItem = new JMenuItem("Standard Selection");
		plainTextMenuItem.addActionListener(new SwitchSidePanelAction(this, 1));
		plainTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		submenu.add(plainTextMenuItem);
		view.add(submenu);
				
		submenu = new JMenu("Double Graphs");
		rbMenuItem = new JRadioButtonMenuItem("Double Graphs");
		rbMenuItem.setSelected((2==numCharts));
		itemGroup.add(rbMenuItem);
		rbMenuItem.addActionListener(new NumPanelsAction(this, 2));
		submenu.add(rbMenuItem);
			submenu1 = new JMenu("Chart 1");
			plainTextMenuItem = new JMenuItem("Standard");
			plainTextMenuItem.addActionListener(new SwitchSidePanelAction(this, 11));
			submenu1.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Custom");
			submenu1.add(plainTextMenuItem);
			submenu.add(submenu1);
			
			submenu1 = new JMenu("Chart 2");
			plainTextMenuItem = new JMenuItem("Standard");
			plainTextMenuItem.addActionListener(new SwitchSidePanelAction(this, 21));
			submenu1.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Custom");
			submenu1.add(plainTextMenuItem);
			submenu.add(submenu1);
		view.add(submenu);
		*/
		
		undo= new JMenu("Undo Zoom");
		undo.setMnemonic(KeyEvent.VK_U);
			plainTextMenuItem = new JMenuItem("Single Chart");
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, prefPanel));
				undo.add(plainTextMenuItem);
				undo.addSeparator();
			plainTextMenuItem = new JMenuItem("Dual Chart 1");
				plainTextMenuItem.setMnemonic(KeyEvent.VK_1);
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, prefPanel1));
				undo.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Dual Chart 2");
			plainTextMenuItem.setMnemonic(KeyEvent.VK_2);
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, prefPanel2));
				undo.add(plainTextMenuItem);

		mainMenuBar.add(undo);
		
		/*export = new JMenu("Export");
		export.setMnemonic(KeyEvent.VK_E);
			plainTextMenuItem = new JMenuItem("Export to PNG");
				plainTextMenuItem.addActionListener(new ExportAsPNGAction(this));
				export.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Export to PDF");
				plainTextMenuItem.addActionListener(new ExportAsPDFAction(this));
				export.add(plainTextMenuItem);

		mainMenuBar.add(export);*/

		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		mainMenuBar.add(help);
		
		//mainMenuBar.setHelpMenu(JMenu menu);
		//TODO add useful help info
		
		return mainMenuBar;
	}
	
	
	
}
