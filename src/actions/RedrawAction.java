package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import panels.MasterPanel;
import visualize.DateRange;

public class RedrawAction implements ActionListener{

	MasterPanel panel;
	DateRange view;
	
	public RedrawAction(MasterPanel m) 
	{
		panel = m;
	}

	public void actionPerformed(ActionEvent e) {
		panel.redraw();
	}
	
	

}
