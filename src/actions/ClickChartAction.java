package actions;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;

import panels.MasterPanel;
import visualize.DateRange;
import visualize.DateRange.Grouping;


//TODO extend functionality
public class ClickChartAction implements ChartMouseListener{

	DateRange range;
	MasterPanel panel;
	int chartNum;
	
	public ClickChartAction(DateRange range, MasterPanel panel, int chartNum)
	{
		this.range = range;
		this.panel = panel;
		this.chartNum = chartNum;
	}
	
	
	public void chartMouseClicked(ChartMouseEvent arg0) {
		String col = getColEntity(arg0.getEntity().toString());
		
		if(chartNum != 0)
			panel.sidePanel = chartNum;
		
		if(null == col) 
		{
			panel.redraw();
			return;
		}
		
		switch(range.g)
		{
			case YEARS:
				range.zooms++;
				yearsToMonths(col);
				break;
			case MONTHS:
				range.zooms++;
				monthsToDoW(col);
				break;
			case DAYS_OF_WEEK:
				range.zooms++;
				dOwToHours(col);
				break;
			case HOURS:
				range.zooms++;
				hoursToMin(col);
				break;
			case MINUTES:
				range.zooms++;
				minutesToSec(col);
				break;
			case SECONDS:
				break;
			
		}
		
	}


	
	private String getColEntity(String s)
	{
		String start = " columnKey=";
		int columnKeyInd = s.indexOf(start);
		int endInd = s.indexOf(",", columnKeyInd);
		
		if(endInd < 0 || columnKeyInd < 0) return null;
		
		String sub = s.substring(columnKeyInd + start.length(), endInd);
		
		return sub;
	}
	
	public void minutesToSec(String col)
	{
		if(panel.numCharts == 1)
			panel.prefPanel.secondsButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.secondsButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.secondsButton.setSelected(true);
		
		range.g = Grouping.SECONDS;
		for(int i = 0; i < range.minutes.length; i++)
		{
			if(col.equals(""+i))
			{
				range.minutes[i] = true;
			}
			else range.minutes[i] = false;
		}
		
		panel.redraw();
	}
	
	
	public void hoursToMin(String col)
	{
		if(panel.numCharts == 1)
			panel.prefPanel.minutesButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.minutesButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.minutesButton.setSelected(true);
		
		range.g = Grouping.MINUTES;
		for(int i = 0; i < range.hours.length; i++)
		{
			if(col.equals(""+i))
			{
				range.hours[i] = true;
			}
			else range.hours[i] = false;
		}
		
		panel.redraw();
	}
	
	public void yearsToMonths(String col)
	{
		if(panel.numCharts == 1)
			panel.prefPanel.monthsButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.monthsButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.monthsButton.setSelected(true);

		range.g = Grouping.MONTHS;
		int yr = range.minYear;
		for(int i = 0; i < range.years.length; i++)
		{
			if(col.equals(""+yr))
			{
				range.years[i] = true;
			}
			else range.years[i] = false;
			yr++;
		}
		
		panel.redraw();
	}
	
	
	public void monthsToDoW(String col)
	{
		if(panel.numCharts == 1)
			panel.prefPanel.daysOfWeekButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.daysOfWeekButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.daysOfWeekButton.setSelected(true);

		range.g = Grouping.DAYS_OF_WEEK;
		
		for(int i = 0; i < range.months.length; i++)
		{
			if(col.equals(DateRange.getMonth(i)))
			{
				range.months[i] = true;
			}
			else range.months[i] = false;
		}
		
		panel.redraw();
	}
	
	public void dOwToHours(String col)
	{
		if(panel.numCharts == 1)
			panel.prefPanel.hoursButton.setSelected(true);
		else if(panel.sidePanel == 1)
			panel.prefPanel1.hoursButton.setSelected(true);
		else if(panel.sidePanel==2)
			panel.prefPanel2.hoursButton.setSelected(true);
		
		range.g = Grouping.HOURS;
		
		for(int i = 0; i < range.daysOfWeek.length; i++)
		{
			if(col.equals(DateRange.getDay(i)))
			{
				range.daysOfWeek[i] = true;
			}
			else range.daysOfWeek[i] = false;
		}
		
		panel.redraw();
	}


	public void chartMouseMoved(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
