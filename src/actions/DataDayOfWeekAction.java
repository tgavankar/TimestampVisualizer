package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import visualize.DateRange;

public class DataDayOfWeekAction implements ActionListener{

	DateRange data;
	int dayOfWeek;
	JCheckBox box;
	
	public DataDayOfWeekAction(DateRange range, int day, JCheckBox box)
	{
		this.data = range;
		this.dayOfWeek = day;
		this.box = box;
	}
	
	public void actionPerformed(ActionEvent e)
	{

		
		data.daysOfWeek[dayOfWeek] = box.isSelected();
	}
}
