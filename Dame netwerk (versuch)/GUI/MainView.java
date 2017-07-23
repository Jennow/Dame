package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel act; 
	
	public MainView(JPanel start) {
		
		act = start;
		add(act); 
		setSize(650, 750); 
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void changeScreen(JPanel panel){
		remove(act);
		act = panel; 
		add(panel); 
		setVisible(true); 

	}
	
	
}
