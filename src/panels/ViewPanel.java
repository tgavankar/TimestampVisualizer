package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import visualize.DateRange;
import actions.RedrawAction;
import actions.SwitchSidePanelAction;
import actions.ViewSelectAction;

public class ViewPanel {
	
	//JScrollPane sidePanel;
	public MasterPanel m;
	public JPanel panel;
	public DateRange range;
	String title;
	
	int offset = 0;
	Dimension d;
	Color c;
	
	public JRadioButton yearsButton = new JRadioButton("Year");
	public JRadioButton monthsButton = new JRadioButton("Month");
	public JRadioButton daysOfWeekButton = new JRadioButton("Day of week");
	public JRadioButton hoursButton = new JRadioButton("Hour");
	public JRadioButton minutesButton = new JRadioButton("Minute");
	public JRadioButton secondsButton = new JRadioButton("Second");
	
	public JList yearList;
	public JList monthList;
	public JList dayList;
	public JList hourList;
	public JList minuteList;
	public JList secondList;
	
	
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
        g.add(yearsButton);
        g.add(monthsButton);
        g.add(daysOfWeekButton);
        g.add(hoursButton);
        g.add(minutesButton);
        g.add(secondsButton);
       
        
        addTitle();
        addYears();
        addMonths();
        addDaysOfWeek();
        addHours();
        addMinutes();
        addSeconds();
        
        addYearList();
        addMonthsList();
        addDaysList();
        addHoursList();
        addMinutesList();
        addSecondsList();
        
        constraints.gridx = 2;
        constraints.gridy = offset++;
        constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;

		panel.add(new JLabel(" "), constraints);
		
		constraints.gridy = offset++;
		
        final JButton bSubmit = new JButton("Apply");
        bSubmit.addActionListener(new RedrawAction(m));

        /*final JButton export = new JButton("Export");
        export.addActionListener(new ExportAction(m));*/
        
        panel.add(bSubmit, constraints);
        
        /*constraints.gridy = offset++;
        panel.add(new JLabel(" "), constraints);
        constraints.gridy = offset++;
        panel.add(export, constraints);*/
        
	}
	
	
	public void addSecondsList()
	{
		String[] sec = new String[range.seconds.length+1];
		sec[0] = "Select all";
		for(int i = 1; i < sec.length; i++)
		{
			sec[i] = ""+(i-1);
		}
		JList list = new JList(sec);
		list.setVisibleRowCount(7);

		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		secondList = list;
		JScrollPane sp = new JScrollPane(list);
		
		GridBagConstraints c = new GridBagConstraints();
		
		offset -=2;
		
		c.gridx = 3;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		

		
		panel.add(new JLabel("Second"),c);
		
		//c.anchor = GridBagConstraints.WEST;

		c.gridy = offset++;
		c.anchor = GridBagConstraints.WEST;

		panel.add(sp, c);
	}
	
	
	public void addMinutesList()
	{
		String[] min = new String[range.minutes.length+1];
		min[0] = "Select all";
		for(int i = 1; i < min.length; i++)
		{
			min[i] = ""+(i-1);
		}
		JList list = new JList(min);
		list.setVisibleRowCount(7);

		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		minuteList = list;
		JScrollPane sp = new JScrollPane(list);
		
		GridBagConstraints c = new GridBagConstraints();
		
		offset -=2;
		
		c.gridx = 2;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		

		
		panel.add(new JLabel("Minute"),c);
		
		//c.anchor = GridBagConstraints.WEST;

		c.gridy = offset++;
		
		panel.add(sp, c);
	}
	
	
	public void addHoursList()
	{
		String[] hrs = new String[range.hours.length+1];
		hrs[0] = "Select all";
		for(int i = 1; i < hrs.length; i++)
		{
			hrs[i] = ""+(i-1);
		}
		JList list = new JList(hrs);
		list.setVisibleRowCount(7);

		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		hourList = list;
		JScrollPane sp = new JScrollPane(list);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		

		
		panel.add(new JLabel("Hour"),c);
		
		//c.anchor = GridBagConstraints.WEST;

		c.gridy = offset++;
		
		panel.add(sp, c);
	}
	
	
	public void addDaysList()
	{
		String[] days = new String[range.daysOfWeek.length+1];
		days[0] = "Select all";
		for(int i = 1; i < days.length; i++)
		{
			days[i] = ""+DateRange.getDay(i-1);
		}
		JList list = new JList(days);
		list.setVisibleRowCount(7);

		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		dayList = list;
		JScrollPane sp = new JScrollPane(list);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		GridBagConstraints c = new GridBagConstraints();

		offset -= 2;
		
		c.gridx = 3;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		

		
		panel.add(new JLabel("Day"),c);
		
		//c.anchor = GridBagConstraints.WEST;
		c.gridy = offset++;
		c.anchor = GridBagConstraints.WEST;

		panel.add(sp, c);
	}
	
	
	public void addMonthsList()
	{
		String[] mos = new String[range.months.length+1];
		mos[0] = "Select all";
		for(int i = 1; i < mos.length; i++)
		{
			mos[i] = DateRange.getMonth(i-1).substring(0,3);
		}
		JList list = new JList(mos);
		list.setVisibleRowCount(7);

		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		monthList = list;
		JScrollPane sp = new JScrollPane(list);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		GridBagConstraints c = new GridBagConstraints();

		offset -= 2;
		
		c.gridx = 2;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		

		
		panel.add(new JLabel("Month"),c);
		//c.anchor = GridBagConstraints.WEST;

		c.gridy = offset++;
		
		panel.add(sp, c);
	}
	
	
	public void addYearList()
	{
		String[] yrs = new String[range.years.length+1];
		int year = range.minYear;
		yrs[0] = "Select all";
		for(int i = 1; i < yrs.length; i++)
		{
			yrs[i] = ""+year;
			year++;
		}
		JList list = new JList(yrs);
		
		list.setVisibleRowCount(7);
		int[] ind = new int[1];
		ind[0] = 0;
		list.setSelectedIndices(ind);
		yearList = list;
		JScrollPane sp = new JScrollPane(list);
		
		GridBagConstraints c = new GridBagConstraints();

		JLabel title = new JLabel("  Select files to include, by...");
		
		
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 0, 0, 0);
		
		panel.add(new JLabel(" "),c);
		c.gridy = offset++;
		panel.add(title, c);
		
		c.anchor = GridBagConstraints.CENTER;


		
		c.gridy = offset++;
		c.gridx = 1;
		c.gridwidth = 1;
		
		panel.add(new JLabel("Year"),c);
		
		c.gridy = offset++;
		c.fill = GridBagConstraints.VERTICAL;
		//c.anchor = GridBagConstraints.WEST;

		
		panel.add(sp, c);
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
		GridBagConstraints c = new GridBagConstraints();
		
		String toAppendToTitle = "";
		
		c.gridx = 0;
		c.gridy = offset++;
		c.gridwidth = 4;
		
		
		if(title.indexOf("Single")==-1)
		{
			String[] choices = new String[2];
			choices[0] = "Graph 1 (top)";
			choices[1] = "Graph 2 (bottom)";
			JComboBox box = new JComboBox(choices);
			
		if(title.indexOf("1") != -1)
		{
			box.setSelectedIndex(0);
			box.addActionListener(new SwitchSidePanelAction(m));
			
			c.gridwidth = 3;
			c.gridx = 1;
			panel.add(box,c);
			
			c.gridy = offset++;
			panel.add(new JLabel(" "),c);
			
			c.gridy = offset++;
			c.gridx = 0;
			c.gridwidth = 4;
			toAppendToTitle="IN GRAPH 1 ";
		}
		else if(title.indexOf("2") != -1)
		{
			
			box.setSelectedIndex(1);
			box.addActionListener(new SwitchSidePanelAction(m));
			c.gridwidth = 3;
			c.gridx = 1;
			panel.add(box,c);
			
			c.gridy = offset++;
			panel.add(new JLabel(" "),c);
			
			c.gridy = offset++;
			c.gridx = 0;
			c.gridwidth = 4;
			toAppendToTitle = "IN GRAPH 2 ";
		}
		}
		
		String label = "  View files "+toAppendToTitle+"by...";
		
		
		JLabel title = new JLabel(label);
		
		
		c.anchor = GridBagConstraints.WEST;

		panel.add(title, c);
		
	}

	public void addSeconds()
	{
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 3;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;

		c.insets = new Insets(10, 0, 0, 0);
		secondsButton.setBackground(this.c);
		secondsButton.addActionListener(new ViewSelectAction(secondsButton, range, DateRange.Grouping.SECONDS));
		panel.add(secondsButton, c);
	}	
	
	public void addMinutes()
	{
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 2;
		c.gridy = offset;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;

		c.insets = new Insets(10, 0, 0, 0);
		minutesButton.setBackground(this.c);
		minutesButton.addActionListener(new ViewSelectAction(minutesButton, range, DateRange.Grouping.MINUTES));
		panel.add(minutesButton, c);
	}	
	
	public void addHours()
	{
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx = 1;
		c.gridy = offset;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;

		c.insets = new Insets(10, 0, 0, 0);
		hoursButton.setBackground(this.c);
		hoursButton.addActionListener(new ViewSelectAction(hoursButton, range, DateRange.Grouping.HOURS));
		panel.add(hoursButton, c);
	}	
	
	public void addDaysOfWeek()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = offset++;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;

		c.insets = new Insets(10, 0, 0, 0);
		daysOfWeekButton.setBackground(this.c);
		daysOfWeekButton.addActionListener(new ViewSelectAction(daysOfWeekButton, range, DateRange.Grouping.DAYS_OF_WEEK));
		panel.add(daysOfWeekButton, c);
		

	}

	public void addMonths()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 2;
		c.gridy = offset;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 0, 0, 0);
		monthsButton.setBackground(this.c);
		monthsButton.addActionListener(new ViewSelectAction(monthsButton, range, DateRange.Grouping.MONTHS));
		panel.add(monthsButton, c);

	}

	public void addYears()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = offset;
		c.gridwidth = 1;
		panel.add(new JLabel("  "), c);
		
		
		c.gridx = 1;
		c.gridy = offset;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 0, 0, 0);
		yearsButton.setSelected(true);
		yearsButton.setBackground(this.c);
		yearsButton.addActionListener(new ViewSelectAction(yearsButton, range, DateRange.Grouping.YEARS));
		panel.add(yearsButton, c);
	}
	
	

}
