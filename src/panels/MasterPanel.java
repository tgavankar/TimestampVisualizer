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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import visualize.DateRange;
import visualize.FileObject;
import actions.AddNewDataSetAction;
import actions.ClickChartAction;
import actions.ExportAsPDFAction;
import actions.ExportAsPNGAction;
import actions.NumPanelsAction;
import actions.RemoveDataSetAction;
import actions.UndoZoomAction;

public class MasterPanel extends ApplicationFrame
{

	private static final long serialVersionUID = 1L;
	JPanel panel;
	public int numCharts = 1; //used to tell if we are viewing 1 or 2 charts
	
	//dimensions for various parts of the panel
	Dimension singleChartD = new Dimension(800, 600);
	Dimension dualChartD = new Dimension(800, 300);
	public Dimension sideDimension = new Dimension(300,600);
	
	//represents which side panel to show
	public int sidePanel = 0; 
	
	public  ArrayList<ArrayList<FileObject>> allFilesSingle;
	public ArrayList<ArrayList<FileObject>> allFilesDual1;
	public ArrayList<ArrayList<FileObject>> allFilesDual2;
	
	public ArrayList<String> singleGraphTitles;
	public ArrayList<String> dual1GraphTitles;
	public ArrayList<String> dual2GraphTitles;

	public DateRange[] view =  new DateRange[3];
	
	//these colors represent the color of the side panel when you are editing
	//the single chart, the dual chart 1, and the dual chart 2
	Color[] colors = {Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY};
	

	public ViewPanel prefPanel;
	
	public ViewPanel prefPanel1;	
	
	public ViewPanel prefPanel2;	
	
	
	
	public MasterPanel()
	{
		super("Timestamp Visualizer");
		allFilesSingle = new ArrayList<ArrayList<FileObject>>();
		allFilesDual1 = new ArrayList<ArrayList<FileObject>>();
		allFilesDual2 = new ArrayList<ArrayList<FileObject>>();
		

		singleGraphTitles = new ArrayList<String>();
		dual1GraphTitles = new ArrayList<String>();
		dual2GraphTitles = new ArrayList<String>();
		
		for(int i =0; i < 3; i++)
		{
			view[i] = new DateRange(i);
		}
		
		//addSet(true, all, title, true, true, true);
		
		

		view[0].getDataSet(allFilesSingle, singleGraphTitles);
		view[1].getDataSet(allFilesDual1, dual1GraphTitles);
		view[2].getDataSet(allFilesDual2, dual2GraphTitles);
		
		
		initPanels(null,null,null);
		

		panel = new JPanel(new GridBagLayout());
		redraw();
	}
	
	public void initPanels(ViewPanel prev1, ViewPanel prev2, ViewPanel prev3)
	{
		prefPanel = new ViewPanel(
				this, 
				view[0],
				sideDimension, 
				colors[0], 
				"Single Chart: View Selection",
				prev1);
		
		prefPanel1 = new ViewPanel(
				this, 
				view[1], 
				sideDimension,
				colors[1], 
				"Dual Chart 1: View Selection",
				prev2);
	
		prefPanel2 = new ViewPanel(
				this, 
				view[2], 
				sideDimension, 
				colors[2], 
				"Dual Chart 2: View Selection",
				prev3);
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
		panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		
		JMenuBar menubar = getBar();
		c.gridx = 0; c.gridy = 0;
		c.anchor=GridBagConstraints.WEST;
		c.weighty=1;
		panel.add(menubar,c);
		
		JFreeChart chart = createChart(view[0], 0);
		ChartPanel p = new ChartPanel(chart);
		chart.setBorderVisible(false);
		p.setPreferredSize(singleChartD);///////////////////////////////////
		p.addChartMouseListener(new ClickChartAction(view[0], this,0));
		c.gridy=1;		
		panel.add(p, c);
		
		
		c.gridx=2;
		panel.add(getSidePanel(), c);
		
		setContentPane(panel);
		
	}

	//draws panel with 2 charts
	public void redraw2()
	{
		panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		
		JMenuBar menubar = getBar();
		c.gridx = 0; c.gridy = 0;
		c.anchor=GridBagConstraints.WEST;
		c.weighty=1;
		panel.add(menubar,c);
		
		
		JPanel chartPanel = new JPanel(new GridBagLayout());
		GridBagConstraints chartC = new GridBagConstraints();
		
		JFreeChart chart1 = createChart(view[1], 1);
		chart1.setBorderVisible(sidePanel == 01);
		ChartPanel p1 = new ChartPanel(chart1);
		p1.setPreferredSize(dualChartD);///////////////////////////////////
		p1.addChartMouseListener(new ClickChartAction(view[1], this, 1));
		chartC.gridx=0;
		chartC.gridy=0;		
		chartPanel.add(p1, chartC);
		
		
		JFreeChart chart2 = createChart(view[2], 2);
		chart2.setBorderVisible(sidePanel==2);
		ChartPanel p2 = new ChartPanel(chart2);
		p2.setPreferredSize(dualChartD);///////////////////////////////////
		p2.addChartMouseListener(new ClickChartAction(view[2], this, 2));
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
	
	private void adjustYears(ArrayList<FileObject> files, boolean[] graphs)
	{
		for(FileObject file : files)
		{
			for(int i =0; i < graphs.length; i++)
			{
				if(!graphs[i]) continue;
				
				
				if(file.getYear() > view[i].maxYear)
				{
					if(view[i].minYear==Integer.MAX_VALUE)
					{
						view[i].maxYear = file.getYear();
					}
					else
					{
						boolean[] newYears = new boolean[file.getYear()-view[i].minYear+1];
						for(int j = 0; j< newYears.length; j++)
							newYears[j]=true;
						if(view[i].years != null)
						{
							for(int j = 0; j<view[i].years.length; j++)
							{
								newYears[j] = view[i].years[j];
							}
						}
						view[i].maxYear = file.getYear();
						view[i].years = newYears;
					}

				}
				
				
				if(file.getYear() < view[i].minYear)
				{
					if(view[i].maxYear==Integer.MIN_VALUE)
					{
						view[i].minYear = file.getYear();
					}
					else
					{
						int diff = view[i].minYear-file.getYear();
						boolean[] newYears = new boolean[view[i].maxYear - file.getYear() + 1];
						for(int j = 0; j< newYears.length; j++)
							newYears[j]=true;
						if(view[i].years != null)
						{
							for(int j = 0; j<view[i].years.length; j++)
							{
								newYears[j+diff] = view[i].years[j];
							}
						}
						view[i].minYear = file.getYear();
						view[i].years = newYears;
					}
				}
			}
		}
	}
	
	public void addSet(ArrayList<FileObject> files, String title, boolean g1, boolean g2, boolean g3)
	{
		if(g1) 
		{
			allFilesSingle.add(files);
			singleGraphTitles.add(title);
		}
		
		
		if(g2) 
		{
			allFilesDual1.add(files);
			dual1GraphTitles.add(title);
		}
		
		
		if(g3)
		{
			allFilesDual2.add(files);
			dual2GraphTitles.add(title);
		}
		
		boolean[] arr = new boolean[3];
		arr[0]=g1; arr[1] = g2; arr[2] = g3;

		adjustYears(files,arr);
		initPanels(prefPanel, prefPanel1, prefPanel2);
	}
	
	public JFreeChart createChart(DateRange d, int chartNum) 
	{
		//TODO change color of bars to match the associated data/view panels 
		//using the 'colors' variable
		
		//TODO label axis properly

		CategoryDataset dataset = null;
		
		if(chartNum==0) dataset = d.getDataSet(d.filterFiles(allFilesSingle),singleGraphTitles);
		if(chartNum==1) dataset = d.getDataSet(d.filterFiles(allFilesDual1), dual1GraphTitles);
		if(chartNum==2) dataset = d.getDataSet(d.filterFiles(allFilesDual2), dual2GraphTitles);

		String xLabel = "";
		switch (d.g)
		{
		case YEARS:
			xLabel = "Year"; break;
		case MONTHS:
			xLabel = "Month"; break;
		case DAYS_OF_WEEK:
			xLabel = "Day of the Week"; break;
		case DAYS:
			xLabel = "Day of the Month"; break;
		case HOURS:
			xLabel = "Hour of the Day"; break;
		case MINUTES:
			xLabel = "Minute"; break;
		case SECONDS:
			xLabel = "Second"; break;
		}
		JFreeChart chart = ChartFactory.createBarChart(
				null, 
				xLabel, 
				"# files accessed", 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				false);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
	    chart.getPlot().setBackgroundPaint(Color.GRAY);
		chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);
		chart.setBorderPaint(Color.BLACK);
		chart.setBorderStroke(new BasicStroke(3));
		chart.setBorderVisible(true);
		chart.getPlot().setInsets(new RectangleInsets(0,0,10,0));
		
		CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		
		ValueAxis yAxis = chart.getCategoryPlot().getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		BarRenderer br = new BarRenderer();
		br.setItemMargin(0.0);
		chart.getCategoryPlot().setRenderer(br);
		return chart;
	}

	public JMenuBar getBar()
	{
		JMenuBar mainMenuBar;

		JMenu data, help, undo, imp, export;
		JMenuItem plainTextMenuItem;
		
		JRadioButtonMenuItem rbMenuItem;
		
		
		mainMenuBar = new JMenuBar();
		data = new JMenu("Select Format");
		data.setMnemonic(KeyEvent.VK_D);
		mainMenuBar.add(data);
		//create items for data menu
		rbMenuItem = new JRadioButtonMenuItem( "Single Graph");
		rbMenuItem.setSelected((1==numCharts));
		rbMenuItem.addActionListener(new NumPanelsAction(this, 1, 0));
		data.add(rbMenuItem);
			
		rbMenuItem = new JRadioButtonMenuItem( "Double Graph");
		rbMenuItem.setSelected((2==numCharts));
		rbMenuItem.addActionListener(new NumPanelsAction(this, 2,1));
		data.add(rbMenuItem);
		mainMenuBar.add(data);
		

		
		
		undo= new JMenu("Undo Zoom");
		undo.setMnemonic(KeyEvent.VK_U);
			plainTextMenuItem = new JMenuItem("Single Chart");
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, 0));
				undo.add(plainTextMenuItem);
				undo.addSeparator();
			plainTextMenuItem = new JMenuItem("Dual Chart 1");
				plainTextMenuItem.setMnemonic(KeyEvent.VK_1);
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, 1));
				undo.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Dual Chart 2");
			plainTextMenuItem.setMnemonic(KeyEvent.VK_2);
				plainTextMenuItem.addActionListener(new UndoZoomAction(this, 2));
				undo.add(plainTextMenuItem);

		mainMenuBar.add(undo);
		
		imp = new JMenu("Import");
		imp.setMnemonic(KeyEvent.VK_I);
		plainTextMenuItem = new JMenuItem("Import a File");
		plainTextMenuItem.addActionListener(new AddNewDataSetAction(this));
		imp.add(plainTextMenuItem);
		plainTextMenuItem = new JMenuItem("Remove a File");
		plainTextMenuItem.addActionListener(new RemoveDataSetAction(this));
		imp.add(plainTextMenuItem);
		mainMenuBar.add(imp);
		
		
		export = new JMenu("Export");
		export.setMnemonic(KeyEvent.VK_E);
			plainTextMenuItem = new JMenuItem("Export to PNG");
				plainTextMenuItem.addActionListener(new ExportAsPNGAction(this));
				export.add(plainTextMenuItem);
			plainTextMenuItem = new JMenuItem("Export to PDF");
				plainTextMenuItem.addActionListener(new ExportAsPDFAction(this));
				export.add(plainTextMenuItem);

		mainMenuBar.add(export);

		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		mainMenuBar.add(help);
		
		//mainMenuBar.setHelpMenu(JMenu menu);
		//TODO add useful help info
		
		return mainMenuBar;
	}
	
	
	
}