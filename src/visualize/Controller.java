package visualize;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RefineryUtilities;

import panels.MasterPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
		
		
		while(inputFile == null){
			
			if(fileName == null){
				
				inputFile =loadDialog();
			}
			else
				inputFile = fileName;
		}
		
		allFiles = LSParser.parseFile(inputFile);
		
		display = new MasterPanel("Timestamp Visualizer", allFiles);
		display.pack();
		RefineryUtilities.centerFrameOnScreen(display);
		
		display.setVisible(true);
		
	}
	
	private String loadDialog(){
		
		
		JOptionPane pop = new JOptionPane();
		String message = "Desired format is obtained by executing \n ls -ls --time-style=+%m/%d/%Y\\ %T" + "\n or \n" +
				"ls -Rls --time-style=+%m/%d/%Y\\ %T";
		
		pop.showMessageDialog(null, message);
		
		JFileChooser fc = new JFileChooser("Select Input File");
		fc.showOpenDialog(null);
		
		
		JTextArea log = new JTextArea(5,20);
		log.append(" Desired format is obtained by executing \"ls -ls --time-style=+%m/%d/%Y %T\"");
		
		fc.add(log);
		
		return fc.getSelectedFile().getPath();
	}
	

}
