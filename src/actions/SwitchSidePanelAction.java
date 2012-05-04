package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import panels.MasterPanel;

public class SwitchSidePanelAction implements ActionListener{

	MasterPanel master;
	
	public SwitchSidePanelAction(MasterPanel m)
	{
		master = m;
		
	}
	
	// TODO implement this
	public SwitchSidePanelAction(MasterPanel m, int panel)
	{
		this(m);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		JButton source = (JButton) e.getSource();
		if(source.getText().equals("Top Graph")) master.sidePanel = 1;
		if(source.getText().equals("Bottom Graph")) master.sidePanel =2;
		
		master.redraw();
	}
}
