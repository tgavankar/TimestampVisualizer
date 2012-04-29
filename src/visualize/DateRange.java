package visualize;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class DateRange {
	

	
	public enum Grouping {
		SECONDS,
		MINUTES,
		HOURS,
		DAYS,
		DAYS_OF_WEEK,
		MONTHS,
		YEARS
	}
	
	public boolean[] hours = new boolean[24];
	public boolean[] daysOfWeek = new boolean[7];
	public boolean[] months = new boolean[12];
	
	public Grouping g;
	
	public int zooms = 0;
	//years?

	
	/*
	 *  default assumes no restrictions, that you want all data
	 */
	public DateRange()
	{
		//set all hours to true
		for(int i = 0; i < hours.length; i++) hours[i] = true;
		
		//set all days of week to true
		for(int i=0; i < daysOfWeek.length; i++) daysOfWeek[i] = true;
		
		//set all months to true
		for(int i=0; i < months.length; i++) months[i] = true;
	
		g = Grouping.DAYS_OF_WEEK;
	}
	

	//used by data
	public ArrayList<FileObject> filterFiles(ArrayList<FileObject> files)
	{
		
		
		ArrayList<FileObject> filtered = new ArrayList<FileObject>();
		
		for(int i = 0; i < files.size(); i++)
		{	
			FileObject file = files.get(i);
			
			//check if hour is valid
			if(!hours[file.getHour()]) continue;
			
			//check if day of week is valid
			if(!daysOfWeek[file.getDayOfWeek()-1]) continue;
			
			//check if month is valid
			if(!months[file.getMonth()-1]) continue;
			
			//if everything is valid, then add to filtered
			filtered.add(file);
		}
		

		
		return filtered;
	}

	//used by view
	public CategoryDataset getDataSet(ArrayList<FileObject> filtered)
	{
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		
		int t;
		String key;
		switch(g)
		{
			case HOURS:				
				
				for(int i = 0; i < filtered.size(); i++)
				{
					FileObject file = filtered.get(i);
					
					t = file.getHour();
					key = "" + t;
					if(map.containsKey(key)) 
					{
						map.put(key, map.get(key) + 1);
					}
					else map.put(key, 1);					
				}
				break;
			case DAYS_OF_WEEK:
				for(int i = 0; i < filtered.size(); i++)
				{
					FileObject file = filtered.get(i);
					
					t = file.getDayOfWeek()-1;
					key = DateRange.getDay(t);
					if(map.containsKey(key)) 
					{
						map.put(key, map.get(key) + 1);
					}
					else map.put(key, 1);	
					
					
				}
				break;
			case MONTHS:
				for(int i = 0; i < filtered.size(); i++)
				{
					FileObject file = filtered.get(i);
					
					t = file.getMonth()-1;
					key = DateRange.getMonth(t);
					
					if(map.containsKey(key)) 
					{
						map.put(key, map.get(key) + 1);
					}
					else map.put(key, 1);					
				}
		}
		
		
		DefaultKeyedValues objs = new DefaultKeyedValues();
		
		
		switch(g)
		{
			case HOURS:
				for(int i = 0; i < hours.length; i++)
				{
					String title = ""+i;
					if(hours[i])
					{
						if(map.containsKey(title)) objs.addValue(title, map.get(title));
						else objs.addValue(title, 0);
					}
				}
				break;
	
		
			case DAYS_OF_WEEK:
				for(int i = 0; i < daysOfWeek.length; i++)
				{
					String title = DateRange.getDay(i);
					if(daysOfWeek[i])
					{
						if(map.containsKey(title)) objs.addValue(title, map.get(title));
						else objs.addValue(title, 0);
					}
				}
				break;
				
			case MONTHS:
				for(int i = 0; i < months.length; i++)
				{
					String title = DateRange.getMonth(i);
					if(months[i])
					{
						if(map.containsKey(title)) objs.addValue(title, map.get(title));
						else objs.addValue(DateRange.getMonth(i), 0);
					}
				}
				break;
		
		}
		
		
		
		/*Set<String> s = map.keySet();		

		
		for(String title : s)
		{
			objs.addValue(title, map.get(title));
		}*/
		
		return DatasetUtilities.createCategoryDataset("XAXIS NAME", objs);
	}

	public static String getDay(int i)
	{
		switch(i)
		{
			case 0: return "Sunday";
			case 1: return "Monday";
			case 2: return "Tuesday";
			case 3: return "Wednesday";
			case 4: return "Thursday";
			case 5: return "Friday";
			case 6: return "Saturday";

		}
		
		return "";
	}

	public static String getMonth(int i)
	{
		switch(i)
		{
			case 0: return "January";
			case 1: return "February";
			case 2: return "March";
			case 3: return "April";
			case 4: return "May";
			case 5: return "June";
			case 6: return "July";
			case 7: return "August";
			case 8: return "September";
			case 9: return "October";
			case 10: return "November";
			case 11: return "December";				
		}
		
		return "";
	}
}
