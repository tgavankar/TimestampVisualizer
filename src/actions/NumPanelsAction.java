package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.MasterPanel;

public class NumPanelsAction implements ActionListener{

	MasterPanel panel;
	int val;
	int panelNum;
	
	public NumPanelsAction(MasterPanel m , int n, int panelNum) 
	{
		panel = m;
		val = n;
		this.panelNum = panelNum;
	}

	public void actionPerformed(ActionEvent e) 
	{
		panel.numCharts = val;
		panel.sidePanel = panelNum;
		panel.redraw();
	}
	
	

}
