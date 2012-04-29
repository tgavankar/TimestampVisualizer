package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import panels.MasterPanel;
import panels.ViewPanel;
import visualize.DateRange;
import visualize.DateRange.Grouping;

//TODO extend functionality
public class UndoZoomAction implements ActionListener{

	MasterPanel panel;
	DateRange range;
	ViewPanel vp;
	
	public UndoZoomAction(MasterPanel m , ViewPanel vp) 
	{
		panel = m;
		range = vp.range;
		this.vp = vp;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(range.zooms <= 0) return;

		switch(range.g)
		{
			case YEARS:
				break;
			case MONTHS:
				break;
			case DAYS_OF_WEEK:
				range.zooms--;
				dOwToMonths();
				break;
			case HOURS:
				range.zooms--;
				hoursToDoW();
				break;
			case MINUTES:
				break;
			
		}
		
		panel.redraw();
	}
	
	private void dOwToMonths()
	{
		JCheckBox[] boxes = vp.monthsB;
		for(int i = 0; i < boxes.length; i++)
		{
			System.out.println(i);
			range.months[i] = boxes[i].isSelected();
		}
		
		range.g = Grouping.MONTHS;
	}
	
	private void hoursToDoW()
	{
		JCheckBox[] boxes = vp.daysOfWeekB;
		for(int i = 0; i < boxes.length; i++)
		{
			System.out.println(i);
			range.daysOfWeek[i] = boxes[i].isSelected();		
		}
		
		range.g = Grouping.DAYS_OF_WEEK;
	}

}
