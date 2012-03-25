import java.util.ArrayList;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChart extends ApplicationFrame {

	public BarChart(String title, ArrayList<FileObject> f, TimeFilter t) {
		super(title);

		final CategoryDataset dataset = createDataset(f, t);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset(ArrayList<FileObject> f, TimeFilter t) { 
		DefaultKeyedValues objs = new DefaultKeyedValues();
		
		ArrayList<FileObject> filtered = new ArrayList<FileObject>();
		
		for(int i=0; i<f.size(); i++) {
			// TODO: all the other checks for other times
			if(t.hasHour(f.get(i).getHour())) {
				filtered.add(f.get(i));
			}
		}
		
		
		switch(t.getGrouping()) {
			// TODO: all the other cases of groupings
			case SECONDS:
				break;
			case MINUTES:
				break;
			case HOURS:
			     int[] counts = new int[24];
			     for(int i=0; i<filtered.size(); i++) {
			         counts[filtered.get(i).getHour()]++;
			     }

			     for(int i=0; i<counts.length; i++) {
			         objs.addValue(Integer.toString(i), counts[i]);
			     }
				break;
			case DAYS:
				break;
			case DAYSOFWEEK:
				break;
			case MONTHS:
				break;
			case YEARS:
				break;
		}
		
		return DatasetUtilities.createCategoryDataset("XAXIS NAME", objs);
	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart(
				"File Modified Over Hour Counts", "Hour of Day", "Files Modified", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
}