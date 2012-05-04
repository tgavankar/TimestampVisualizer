package visualize;

import org.jfree.ui.RefineryUtilities;

import panels.MasterPanel;
import actions.AddNewDataSetAction;


public class Controller 
{
	
	
	MasterPanel display;
	
	public Controller()
	{
		display = new MasterPanel();
		display.pack();
		RefineryUtilities.centerFrameOnScreen(display);
		display.setResizable(false);
		display.setVisible(true);
		(new AddNewDataSetAction(display)).actionPerformed(null);
		
	}
	

}
