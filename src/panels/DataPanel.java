package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import actions.DataDayOfWeekAction;
import actions.DataMonthAction;
import actions.RedrawAction;
import actions.SwitchSidePanelAction;
import actions.ViewSelectAction;

import visualize.DateRange;
import visualize.ReDrawAction;
import visualize.TextAction;

public class DataPanel {
	
	//JScrollPane sidePanel;
	public MasterPanel m;
	public JPanel panel;
	DateRange range;
	String title;
	
	int offset = 0;
	Dimension d;
	Color c;
	
	//Day of Week Checkboxes
	JCheckBox[] hoursB = new JCheckBox[24];
	JLabel hoursL = new JLabel("Select Hours");
	JCheckBox[] daysOfWeekB = new JCheckBox[7];
	JLabel daysOfWeekL = new JLabel("Select Days of Week");
	JCheckBox[] monthsB = new JCheckBox[12];
	JLabel monthsL = new JLabel("Select Months");
	
	public DataPanel(MasterPanel master, DateRange r, Dimension d, Color c, String s)
	{
		m = master;
		this.d = d;
		this.c = c;
		range = r;
		title = s;
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setPreferredSize(d);
        panel.setBackground(c);
        
        GridBagConstraints constraints = getConstraints();

        
        addTitle();
        
        addHours();
        
        //day of week checkboxes
        addDaysOfWeek();
        
                
        //date form fields
        
        //month checkboxes
        addMonths();
        
        //year form fields
        
        

        
        constraints.gridx = 3;
        constraints.gridy = offset++;
        constraints.gridwidth = 3;
        final JButton bSubmit = new JButton("submit");
        bSubmit.addActionListener(new RedrawAction(m));
 
        panel.add(bSubmit, constraints);
	}

	public GridBagConstraints getConstraints()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.weightx =.5;
		
		return c;
	}
	
	public void addTitle()
	{
		JLabel title = new JLabel(this.title);
		JButton b = new JButton("Switch to View Selection");
		b.addActionListener(new SwitchSidePanelAction(m, 1)); //TODO change second parameter to be
															//the appropriate view panel
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		panel.add(title, c);
		
		c.gridy = offset++;
		c.insets = new Insets(0,0,30, 0);
		panel.add(b, c);
	}

	public void addHours()
	{
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(hoursL, c);

     
		JCheckBox curr;
		int count = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,0);
		for(int x = 1; x < 5; x ++)
		{
			for(int y = 0; y < 6; y++)
			{
				if( count < hoursB.length)
				{
					curr = hoursB[count];
					curr = new JCheckBox(count + ":00");
					curr.setBackground(this.c);
					curr.setSelected(true);
					curr.addActionListener(new DataMonthAction(range, count, curr));
    			
	    			
	        		c.gridx = x;
	        		c.gridy = offset + y;
	        		panel.add(curr, c);
	        		count++;
				}
				else break;
			}
		}
		
		offset += 6;

}		
	
	public void addDaysOfWeek()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(daysOfWeekL, c);
		

        
        JCheckBox curr;
        int count = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        for(int x = 1; x < 4; x ++)
        {
        	for(int y = 0; y < 3; y++)
        	{
        		if( count < daysOfWeekB.length)
        		{
        			curr = daysOfWeekB[count];
                	curr = new JCheckBox(DateRange.getDay(count));
                	curr.setBackground(this.c);
        			curr.setSelected(true);
        			curr.addActionListener(new DataDayOfWeekAction(range, count, curr));
        			
        			//add action listeners and check boxes
   			
	        		c.gridx = x;
	        		c.gridy = y + offset;
	        		panel.add(curr, c);
	        		count++;
        		}
        		else break;
        	}
        }
        
        offset += 3;
        
	}

	public void addMonths()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(monthsL, c);

         
	    JCheckBox curr;
	    int count = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 1;
	    c.insets = new Insets(0,0,0,0);
	    for(int x = 1; x < 4; x ++)
	    {
	    	for(int y = 0; y < 5; y++)
	    	{
	    		if( count < monthsB.length)
	    		{
        			curr = monthsB[count];
                	curr = new JCheckBox(DateRange.getMonth(count));
                	curr.setBackground(this.c);
        			curr.setSelected(true);
        			curr.addActionListener(new DataMonthAction(range, count, curr));
	    			
	    			
	        		c.gridx = x;
	        		c.gridy = offset + y;
	        		panel.add(curr, c);
	        		count++;
	    		}
	    		else break;
	    	}
	    }
	    
	    offset += 5;
    
	}

	public void addYears()
	{
		
	}
	
	


}
