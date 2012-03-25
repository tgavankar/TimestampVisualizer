import org.jfree.ui.RefineryUtilities;

public class Main {
	 public static void main(String[] args) {
		 	TimeFilter tf = new TimeFilter();
		 	tf.resetHours();
		 	int[] h = { 12, 13, 14, 15, 16 };
		 	tf.setHours(h);
			BarChart chart = new BarChart("Hours Chart", LSParser.parseFile("src/ls.txt"), tf);
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
	    }
}