package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DummyAction implements ActionListener{


	int count = 0;
	
	public DummyAction()
	{

	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("LALALA" + (count++));
	}
}
