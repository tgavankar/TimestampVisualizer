package actions;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import panels.MasterPanel;

public class ExportAsPNGAction implements ActionListener{

 MasterPanel panel;
 
 public ExportAsPNGAction(MasterPanel m) 
 {
  panel = m;
 }

 public void actionPerformed(ActionEvent e) {
  if(panel.numCharts == 1) {
   JFreeChart chart = panel.createChart(panel.view[0],0);
   try {
    String savename = saveFile(new Frame(), "Save...", ".", "chart.png");
    if(savename != null) { // User hit cancel
     ChartUtilities.saveChartAsPNG(new File(savename), chart, 800, 600);
    }
   } catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
  }
  else if(panel.numCharts == 2) {
   JFreeChart chart1 = panel.createChart(panel.view[1],1);
   try {
    String savename = saveFile(new Frame(), "Save Top Chart...", ".", "chart_top.png");
    if(savename!=null) {
     ChartUtilities.saveChartAsPNG(new File(savename), chart1, 800, 600);
    }
   } catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }
   
   
   JFreeChart chart2 = panel.createChart(panel.view[2],2);
   try {
    String savename = saveFile(new Frame(), "Save Bottom Chart...", ".", "chart_bottom.png");
    if(savename != null) {
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
     fd.setLocation(300,200);
     fd.setVisible(true);
     if(fd.getDirectory()==null || fd.getFile()==null) return null;
     return fd.getDirectory()+fd.getFile();
 }
 

}
