package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import panels.MasterPanel;
import visualize.DateRange;

public class NumPanelsAction implements ActionListener{

	MasterPanel panel;
	int val;
	
	public NumPanelsAction(MasterPanel m , int n) 
	{
		panel = m;
		val = n;
	}

	public void actionPerformed(ActionEvent e) {
		panel.numCharts = val;
		panel.redraw();
	}
	
	

}
