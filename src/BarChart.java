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

	public BarChart(String title, ArrayList<FileObject> f, TimeResolution t) {
		super(title);

		final CategoryDataset dataset = createDataset(f, t);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset(ArrayList<FileObject> f, TimeResolution t) { 
		DefaultKeyedValues objs = new DefaultKeyedValues();
		Random r = new Random();
		
		// Find better way to handle multiple options (i.e. month==Feb && by days)
		switch(t) {
		case SECONDS:
			break;
		case MINUTES:
			break;
		case HOURS:
			int[] counts = new int[24];
			for(int i=0; i<counts.length; i++) {
				counts[f.get(i).getHour()]++;
			}
			for(int i=0; i<counts.length; i++) {
				objs.addValue(Integer.toString(i), counts[i]);
			}
			break;
		case DAYS:
			break;
		case DAYSOFWEEK:
			break;
		case WEEKS:
			break;
		case MONTHS:
			break;
		case YEARS:
			break;
		}
		
		return DatasetUtilities.createCategoryDataset("Day", objs);
	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart(
				"File Modified Over Hour Counts", "Hour of Day", "Files Modified", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
}