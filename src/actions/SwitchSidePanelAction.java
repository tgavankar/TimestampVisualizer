package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.MasterPanel;

public class SwitchSidePanelAction implements ActionListener{

	MasterPanel master;
	int panelNum;
	
	public SwitchSidePanelAction(MasterPanel m, int pn)
	{
		master = m;
		panelNum = pn;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		master.sidePanel = panelNum;
		
		master.redraw();
	}
}
