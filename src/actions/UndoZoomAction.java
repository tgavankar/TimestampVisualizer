package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.MasterPanel;
import visualize.DateRange;
import visualize.DateRange.Grouping;

//TODO extend functionality
public class UndoZoomAction implements ActionListener{

	MasterPanel panel;
	DateRange range;
	int panelNum;
	
	public UndoZoomAction(MasterPanel m , int panelNum) 
	{
		panel = m;
		range = panel.view[panelNum];
		this.panelNum = panelNum;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(range.zooms <= 0) return;

		
		if(panelNum != 0)
		{
			panel.sidePanel = panelNum;
		}
		
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
		if(panelNum == 0)
			panel.prefPanel.hoursButton.setSelected(true);
		else if(panelNum == 1)
			panel.prefPanel1.hoursButton.setSelected(true);
		else if(panelNum==2)
			panel.prefPanel2.hoursButton.setSelected(true);
		
		for(int i = 0; i < range.hours.length; i++)
		{
			range.hours[i] = true;
		}
		
		range.g = Grouping.HOURS;
	}
	
	private void secondsToMinutes()
	{
		if(panelNum ==0)
			panel.prefPanel.minutesButton.setSelected(true);
		else if(panelNum== 1)
			panel.prefPanel1.minutesButton.setSelected(true);
		else if(panelNum==2)
			panel.prefPanel2.minutesButton.setSelected(true);
		
		for(int i = 0; i < range.minutes.length; i++)
		{
			range.minutes[i] = true;
		}
		
		range.g = Grouping.MINUTES;
	}
	
	private void dOwToMonths()
	{
		if(panelNum==0)
			panel.prefPanel.monthsButton.setSelected(true);
		else if(panelNum == 1)
			panel.prefPanel1.monthsButton.setSelected(true);
		else if(panelNum==2)
			panel.prefPanel2.monthsButton.setSelected(true);
		
		for(int i = 0; i < range.months.length; i++)
		{
			range.months[i] = true;
		}
		
		range.g = Grouping.MONTHS;
	}
	
	private void hoursToDoW()
	{
		if(panelNum==0)
			panel.prefPanel.daysOfWeekButton.setSelected(true);
		else if(panelNum == 1)
			panel.prefPanel1.daysOfWeekButton.setSelected(true);
		else if(panelNum==2)
			panel.prefPanel2.daysOfWeekButton.setSelected(true);
		
		for(int i = 0; i < range.daysOfWeek.length; i++)
		{
			range.daysOfWeek[i] = true;		
		}
		
		range.g = Grouping.DAYS_OF_WEEK;
	}
	
	private void monthsToYears()
	{
		if(panelNum==0)
			panel.prefPanel.yearsButton.setSelected(true);
		else if(panelNum == 1)
			panel.prefPanel1.yearsButton.setSelected(true);
		else if(panelNum==2)
			panel.prefPanel2.yearsButton.setSelected(true);
		
		for(int i = 0; i < range.years.length; i++)
		{
			range.years[i] = true;		
		}
		
		range.g = Grouping.YEARS;
	}

}
