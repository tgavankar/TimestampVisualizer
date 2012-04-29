package panels;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

public class GraphPanel {

	CategoryDataset dataset;
	String x;
	String y;
	String title;
	
	JPanel panel;
	
	public GraphPanel(String t, String x, String y, CategoryDataset data)
	{
		dataset = data;
		this.x = x;
		this.y = y;		
		title = t;
		
		panel = new JPanel();
		
		drawPanel();
	}
	
	public void drawPanel()
	{
		JFreeChart chart = createChart();
		ChartPanel p = new ChartPanel(chart);
		
		panel.removeAll();
		panel.add(p);
	}
	
	private JFreeChart createChart( ) 
	{

		JFreeChart chart = ChartFactory.createBarChart(
				title, x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
	
}
