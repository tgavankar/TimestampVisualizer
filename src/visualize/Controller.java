package visualize;

import java.awt.Dimension;
import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RefineryUtilities;

import panels.MasterPanel;

public class Controller 
{

	
	static String inputFile;
	ArrayList<FileObject> allFiles;  //all files from parsed ls dump
	ArrayList<FileObject> selectedFiles; //files 
	
	DateRange data = new DateRange();
	DateRange view = new DateRange();
	
	CategoryDataset dataset;
	
	MasterPanel display;
	Dimension sideDimension = new Dimension(300,600);
	
	//flags for how file will be displayed
	int displayBy = 0; //unit for x-axis of graph 
	
	public Controller(String fileName)
	{
		inputFile = fileName;
		
		allFiles = LSParser.parseFile(inputFile);
		
		display = new MasterPanel("Timestamp Visualizer", allFiles);
		display.pack();
		RefineryUtilities.centerFrameOnScreen(display);
		
		display.setVisible(true);
		
	}
	

	
}
