package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import visualize.DateRange;

public class DataMonthAction implements ActionListener{

	DateRange range;
	int month;
	JCheckBox box;
	
	public DataMonthAction(DateRange range, int month, JCheckBox box)
	{
		this.range = range;
		this.month = month;
		this.box = box;
	}
	
	public void actionPerformed(ActionEvent e)
	{

		
		range.months[month] = box.isSelected();
	}
}
