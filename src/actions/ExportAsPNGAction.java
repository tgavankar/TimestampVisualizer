package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;

import panels.MasterPanel;
import visualize.DateRange;

public class ExportAsPNGAction implements ActionListener{

	MasterPanel panel;
	
	public ExportAsPNGAction(MasterPanel m) 
	{
		panel = m;
	}

	public void actionPerformed(ActionEvent e) {
		if(panel.numCharts == 1) {
			JFreeChart chart = panel.createChart(panel.dataset);
			try {
				String savename = saveFile(new Frame(), "Save...", ".", "chart.png");
				if(!savename.equals(".null")) { // User hit cancel
					ChartUtilities.saveChartAsPNG(new File(savename), chart, 800, 600);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(panel.numCharts == 2) {
			JFreeChart chart1 = panel.createChart(panel.dataset1);
			try {
				String savename = saveFile(new Frame(), "Save Top Chart...", ".", "chart_top.png");
				if(!savename.equals(".null")) {
					ChartUtilities.saveChartAsPNG(new File(savename), chart1, 800, 600);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			JFreeChart chart2 = panel.createChart(panel.dataset2);
			try {
				String savename = saveFile(new Frame(), "Save Bottom Chart...", ".", "chart_bottom.png");
				if(!savename.equals(".null")) {
					ChartUtilities.saveChartAsPNG(new File(savename), chart2, 800, 600);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private String saveFile(Frame f, String title, String defDir, String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
	    fd.setFile(fileType);
	    fd.setDirectory(defDir);
	    fd.setLocation(50, 50);
	    fd.setVisible(true);
	    return fd.getDirectory() + fd.getFile();
	}
	

}
