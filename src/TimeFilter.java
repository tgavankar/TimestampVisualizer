import java.util.ArrayList;
import java.util.HashSet;



public class TimeFilter {
	public enum Grouping {
		SECONDS,
		MINUTES,
		HOURS,
		DAYS,
		DAYSOFWEEK,
		MONTHS,
		YEARS
	}
	
	private int[] secondsO = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
	private int[] minutesO = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };
	private int[] hoursO = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
	private int[] datesO = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
	private int[] daysOfWeekO = { 0, 1, 2, 3, 4, 5, 6, 7 };
	private int[] monthsO = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	
	private HashSet<Integer> seconds;
	private HashSet<Integer> minutes;
	private HashSet<Integer> hours;
	private HashSet<Integer> dates;
	private HashSet<Integer> daysOfWeek;
	private HashSet<Integer> months;
	
	//If years is empty, that means include all years
	private HashSet<Integer> years;
	
	private Grouping groupBy;
	
	public TimeFilter() {
		seconds = new HashSet<Integer>();
		minutes = new HashSet<Integer>();
		hours = new HashSet<Integer>();
		dates = new HashSet<Integer>();
		daysOfWeek = new HashSet<Integer>();
		months = new HashSet<Integer>();
		years = new HashSet<Integer>();
		resetSeconds();
		resetMinutes();
		resetHours();
		resetDates();
		resetDaysOfWeek();
		resetMonths();
		resetYears();
		groupBy = Grouping.HOURS;
	}
	
	public void setGrouping(Grouping g) {
		groupBy = g;
	}
	
	public Grouping getGrouping() {
		return groupBy;
	}
	
	public boolean hasYear(int y) {
		if(years.size() == 0) {
			return true;
		}
		return years.contains(y);
	}
	
	public void setYears(int[] y) {
		years.clear();
		for(int i=0; i<y.length; i++) {
			years.add(y[i]);
		}
	}
	
	public void resetYears() {
		years.clear();
	}
	
	public void setSeconds(int[] s) {
		seconds.clear();
		for(int i=0; i<s.length; i++) {
			seconds.add(s[i]);
		}
	}
	
	public void resetSeconds() {
		seconds.clear();
		setSeconds(secondsO);
	}
	
	public boolean hasSeconds(int s) {
		return seconds.contains(s);
	}
	
	public void setMinutes(int[] m) {
		minutes.clear();
		for(int i=0; i<m.length; i++) {
			minutes.add(m[i]);
		}
	}
	
	public void resetMinutes() {
		minutes.clear();
		setMinutes(minutesO);
	}
	
	public boolean hasMinute(int m) {
		return minutes.contains(m);
	}
	
	public void setHours(int[] h) {
		hours.clear();
		for(int i=0; i<h.length; i++) {
			hours.add(h[i]);
		}
	}
	
	public void resetHours() {
		hours.clear();
		setHours(hoursO);
	}
	
	public boolean hasHour(int h) {
		return hours.contains(h);
	}
	
	public void setDates(int[] d) {
		dates.clear();
		for(int i=0; i<d.length; i++) {
			dates.add(d[i]);
		}
	}
	
	public void resetDates() {
		dates.clear();
		setDates(datesO);
	}
	
	public boolean hasDate(int d) {
		return dates.contains(d);
	}
	
	public void setDaysOfWeek(int[] d) {
		daysOfWeek.clear();
		for(int i=0; i<d.length; i++) {
			daysOfWeek.add(d[i]);
		}
	}
	
	public void resetDaysOfWeek() {
		daysOfWeek.clear();
		setDaysOfWeek(daysOfWeekO);
	}
	
	public boolean hasDayOfWeek(int d) {
		return daysOfWeek.contains(d);
	}
	
	public void setMonths(int[] m) {
		months.clear();
		for(int i=0; i<m.length; i++) {
			months.add(m[i]);
		}
	}
	
	public void resetMonths() {
		months.clear();
		setMonths(monthsO);
	}
	
	public boolean hasMonth(int m) {
		return months.contains(m);
	}
}
