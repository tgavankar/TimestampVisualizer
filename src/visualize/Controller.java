package visualize;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
			if(fileName == null)
				inputFile = loadDialog();
			else
				inputFile = fileName;
		
		if(inputFile==null) // User hit cancel & does not want to select file
			System.exit(0);
			
		try
		{
			allFiles = LSParser.parseFile(inputFile);
		}
		catch(Exception e)
		{
			JOptionPane pop = new JOptionPane();
			String message = "The specified file has an invalid format.\n\n" 
					+"Desired format is obtained by executing \n ls -ls --time-style=+%m/%d/%Y\\ %T" 
					+ "\n             or \n" +
					"ls -Rls --time-style=+%m/%d/%Y\\ %T\n";
			pop.setLocation(50,50);
			JOptionPane.showMessageDialog(pop, message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		display = new MasterPanel("Timestamp Visualizer", allFiles);
		display.pack();
		RefineryUtilities.centerFrameOnScreen(display);
		
		display.setVisible(true);
		
	}
	
	private String loadDialog(){
		
		/*
		JOptionPane pop = new JOptionPane();
		String message = "Desired format is obtained by executing \n ls -ls --time-style=+%m/%d/%Y\\ %T" + "\n or \n" +
				"ls -Rls --time-style=+%m/%d/%Y\\ %T";
		
		pop.showMessageDialog(null, message);
		*/
		
	
		/*
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select a file");
		int chosen = fc.showOpenDialog(null);
		
		if(chosen==JFileChooser.CANCEL_OPTION)
			return null;
		else return fc.getSelectedFile().getPath();
		*/

		
		FileDialog fd = new FileDialog(new Frame(), "Select a file", FileDialog.LOAD);
	    fd.setLocation(50, 50);
	    fd.setVisible(true);
	    if(fd.getDirectory()==null || fd.getFile()==null) return null;
	    return fd.getDirectory()+fd.getFile();
	}

}
