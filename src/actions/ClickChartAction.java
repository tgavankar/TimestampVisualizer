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
	
	public ClickChartAction(DateRange range, MasterPanel panel)
	{
		this.range = range;
		this.panel = panel;
		//chartNum = n;
	}
	
	
	public void chartMouseClicked(ChartMouseEvent arg0) {
		String col = getColEntity(arg0.getEntity().toString());
		System.out.println(arg0.getEntity());
		if(null == col) return;
		
		switch(range.g)
		{
			case YEARS:
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
				//hourTOMin();
				break;
			case MINUTES:
				break;
			
		}
		
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	public void monthsToDoW(String col)
	{

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
}
