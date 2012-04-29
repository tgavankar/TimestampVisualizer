package visualize;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextAction implements ActionListener{

	JTextField field;
	
	public TextAction(JTextField s) 
	{
		field = s;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("text: " + field.getText());
	}
	
	

}
