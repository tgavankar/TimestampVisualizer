import org.jfree.ui.RefineryUtilities;

public class Main {
	 public static void main(String[] args) {
		 	TimeFilter tf = new TimeFilter();
		 	tf.resetHours();
		 	int[] h = { 12, 13, 14, 15, 16 };
		 	tf.setHours(h);
//		 	tf.resetMinutes();
//		 	int[] m = { 1, 2, 3, 5, 8, 13, 21, 34, 55 };
//		 	tf.setMinutes(m);	
		 	
//		 	tf.resetDaysOfWeek();
//		 	int [] dow = { 1, 5, 7 };
//		 	tf.setDaysOfWeek(dow);
		 	
			BarChart chart = new BarChart("Hours Chart", LSParser.parseFile("src/ls.txt"), tf);
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
	    }
}