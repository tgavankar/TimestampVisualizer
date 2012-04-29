package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import visualize.DateRange;
import visualize.DateRange.Grouping;

public class ViewSelectAction implements ActionListener{


	
	JRadioButton b;
	DateRange range;
	Grouping button;
	
	public ViewSelectAction(JRadioButton b, DateRange range, Grouping button)
	{
		this.b = b;
		this.range = range;
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(b.isSelected())
		{
			range.g = button;
		}
	}
}
