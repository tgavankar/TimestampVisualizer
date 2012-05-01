package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				range.zooms--;
				monthsToYears();
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
				range.zooms--;
				minutesToHours();
				break;
			case SECONDS:
				range.zooms--;
				secondsToMinutes();
				break;
			
		}
		
		panel.redraw();
	}
	
	private void minutesToHours()
	{
		if(panel.numCharts == 1)
			panel.prefPanel.hoursButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.hoursButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.hoursButton.setSelected(true);
		
		for(int i = 0; i < range.hours.length; i++)
		{
			range.hours[i] = true;
		}
		
		range.g = Grouping.HOURS;
	}
	
	private void secondsToMinutes()
	{
		if(panel.numCharts == 1)
			panel.prefPanel.minutesButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.minutesButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.minutesButton.setSelected(true);
		
		for(int i = 0; i < range.minutes.length; i++)
		{
			range.minutes[i] = true;
		}
		
		range.g = Grouping.MINUTES;
	}
	
	private void dOwToMonths()
	{
		if(panel.numCharts == 1)
			panel.prefPanel.monthsButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.monthsButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.monthsButton.setSelected(true);
		
		for(int i = 0; i < range.months.length; i++)
		{
			range.months[i] = true;
		}
		
		range.g = Grouping.MONTHS;
	}
	
	private void hoursToDoW()
	{
		if(panel.numCharts == 1)
			panel.prefPanel.daysOfWeekButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.daysOfWeekButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.daysOfWeekButton.setSelected(true);
		
		for(int i = 0; i < range.daysOfWeek.length; i++)
		{
			range.daysOfWeek[i] = true;		
		}
		
		range.g = Grouping.DAYS_OF_WEEK;
	}
	
	private void monthsToYears()
	{
		if(panel.numCharts == 1)
			panel.prefPanel.yearsButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.yearsButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.yearsButton.setSelected(true);
		
		for(int i = 0; i < range.years.length; i++)
		{
			range.years[i] = true;		
		}
		
		range.g = Grouping.YEARS;
	}

}
