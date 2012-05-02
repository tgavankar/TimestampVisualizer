package visualize;


public class Main 
{	
	 public static void main(String[] args) 
	 {
		 if (args.length == 0)
		 	new Controller(null);
		 else new Controller(args[0]);
		 	
	 }
}