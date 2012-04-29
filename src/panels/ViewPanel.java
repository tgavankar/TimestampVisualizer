package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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

public class ViewPanel {
	
	//JScrollPane sidePanel;
	public MasterPanel m;
	public JPanel panel;
	public DateRange range;
	String title;
	
	int offset = 0;
	Dimension d;
	Color c;
	
	//Day of Week Checkboxes
	public JCheckBox[] daysOfWeekB = new JCheckBox[7];
	public JRadioButton daysOfWeekL = new JRadioButton("Select Days of Week");
	
	public JCheckBox[] monthsB = new JCheckBox[12];
	public JRadioButton monthsL = new JRadioButton("Select Months");
	
	
	public JCheckBox[] hoursB = new JCheckBox[24];
	public JRadioButton hoursL = new JRadioButton("Select Hours");
	
	public ViewPanel(MasterPanel master, DateRange r, Dimension d, Color c, String s)
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

        
        ButtonGroup g = new ButtonGroup();
        g.add(hoursL);
        g.add(daysOfWeekL);
        g.add(monthsL);
        
        addTitle();
        
        //add hour checkboxes
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
		JButton b = new JButton("Switch to Data Selection");
		b.addActionListener(new SwitchSidePanelAction(m, 0)); //TODO change second parameter to be
															//appropriate data panel
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		panel.add(title, c);
		
		c.gridy = offset++;
		c.insets = new Insets(0,0, 30, 0);
		panel.add(b, c);
	}

	public void addHours()
	{
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 6;
		c.insets = new Insets(10, 0, 0, 0);
		hoursL.setBackground(this.c);
		hoursL.addActionListener(new ViewSelectAction(hoursL, range, DateRange.Grouping.HOURS));
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
					hoursB[count]=curr;
	    			
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
		daysOfWeekL.setSelected(true);
		daysOfWeekL.setBackground(this.c);
		daysOfWeekL.addActionListener(new ViewSelectAction(daysOfWeekL, range, DateRange.Grouping.DAYS_OF_WEEK));
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
        			daysOfWeekB[count] = curr;
        			
        			//add action listeners and check boxes
   			
	        		c.gridx = x;
	        		c.gridy = offset + y;
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
		monthsL.setBackground(this.c);
		monthsL.addActionListener(new ViewSelectAction(monthsL, range, DateRange.Grouping.MONTHS));
		panel.add(monthsL, c);

         
	    JCheckBox curr;
	    int count = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 1;
	    c.insets = new Insets(0,0,0,0);
	    for(int x = 1; x < 4; x ++)
	    {
	    	for(int y = 0; y < 4; y++)
	    	{
	    		if( count < monthsB.length)
	    		{
        			curr = monthsB[count];
                	curr = new JCheckBox(DateRange.getMonth(count));
                	curr.setBackground(this.c);
        			curr.setSelected(true);
        			curr.addActionListener(new DataMonthAction(range, count, curr));
        			monthsB[count] = curr;
	    			
	    			
	        		c.gridx = x;
	        		c.gridy = offset + y;
	        		panel.add(curr, c);
	        		count++;
	    		}
	    		else break;
	    	}
	    }
	    
	    offset += 4;
    
	}

	public void addYears()
	{
		
	}
	
	

}
