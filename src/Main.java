import org.jfree.ui.RefineryUtilities;

public class Main {
	 public static void main(String[] args) {
			BarChart chart = new BarChart("Hours Chart", LSParser.parseFile("src/ls.txt"), TimeResolution.HOURS);
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
	    }
}