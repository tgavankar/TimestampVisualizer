import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		Dimension d = new Dimension(300,200);
		final CategoryDataset dataset = createDataset(f, t);
		final JFreeChart chart = createChart(dataset);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		panel.add(chartPanel);
		panel.add(getPanel(d,  6, Color.red), gbc);
		setContentPane(panel);
	}

	private JScrollPane getPanel(Dimension d, int rows, Color color) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(color);
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets = new Insets(10,5,10,5);
        gbc.weightx = 1.0;
        for(int i = 0, j = 1; i < rows; i++) {
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            panel.add(new JButton(String.valueOf(j++)), gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            panel.add(new JButton(String.valueOf(j++)), gbc);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(d);
        return scrollPane;
    }
	
	private CategoryDataset createDataset(ArrayList<FileObject> f, TimeFilter t) { 
		DefaultKeyedValues objs = new DefaultKeyedValues();
		
		ArrayList<FileObject> filtered = new ArrayList<FileObject>();
		
		for(int i=0; i<f.size(); i++) {
			
			if(t.hasHour(f.get(i).getHour())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getMinute())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getSecond())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getDay())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getDayOfWeek())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getMonth())) {
				filtered.add(f.get(i));
			}
			
			if(t.hasHour(f.get(i).getYear())) {
				filtered.add(f.get(i));
			}
		}
		
		
		switch(t.getGrouping()) {
			
			case SECONDS:
				int[] counts1 = new int[60];
			     for(int i=0; i<filtered.size(); i++) {
			         counts1[filtered.get(i).getSecond()]++;
			     }

			     for(int i=0; i<counts1.length; i++) {
			         objs.addValue(Integer.toString(i), counts1[i]);
			     }
				break;
			case MINUTES:
				int[] counts2 = new int[60];
			     for(int i=0; i<filtered.size(); i++) {
			         counts2[filtered.get(i).getMinute()]++;
			     }

			     for(int i=0; i<counts2.length; i++) {
			         objs.addValue(Integer.toString(i), counts2[i]);
			     }
				break;
			case HOURS:
			     int[] counts3 = new int[24];
			     for(int i=0; i<filtered.size(); i++) {
			         counts3[filtered.get(i).getHour()]++;
			     }

			     for(int i=0; i<counts3.length; i++) {
			         objs.addValue(Integer.toString(i), counts3[i]);
			     }
				break;
			case DAYS:
				int[] counts4 = new int[31];
			     for(int i=0; i<filtered.size(); i++) {
			         counts4[filtered.get(i).getDay()]++;
			     }

			     for(int i=0; i<counts4.length; i++) {
			         objs.addValue(Integer.toString(i), counts4[i]);
			     }
				break;
			case DAYSOFWEEK:
				int[] counts5 = new int[7];
			     for(int i=0; i<filtered.size(); i++) {
			         counts5[filtered.get(i).getDayOfWeek()]++;
			     }

			     for(int i=0; i<counts5.length; i++) {
			         objs.addValue(Integer.toString(i), counts5[i]);
			     }
				break;
			case MONTHS:
				int[] counts6 = new int[12];
			     for(int i=0; i<filtered.size(); i++) {
			         counts6[filtered.get(i).getMonth()]++;
			     }

			     for(int i=0; i<counts6.length; i++) {
			         objs.addValue(Integer.toString(i), counts6[i]);
			     }
				break;
			case YEARS:
				int[] counts7 = new int[7];
			     for(int i=0; i<filtered.size(); i++) {
			         counts7[filtered.get(i).getYear()]++;
			     }

			     for(int i=0; i<counts7.length; i++) {
			         objs.addValue(Integer.toString(i), counts7[i]);
			     }
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