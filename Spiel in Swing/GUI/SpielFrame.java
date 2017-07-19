package GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.GamePlay;

public class SpielFrame extends JFrame {

	GamePlay gP;
	public Spielbrett sb;
	
	public SpielFrame(GamePlay gP) {
		// TODO Auto-generated constructor stub
		this.gP = gP;
		sb = new Spielbrett(gP);
		
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(new JLabel("Du bist dran"));
		box.add(sb);
		box.add(new JLabel("Weiße Steine zerstört: 2"));
		box.add(new JLabel("Schwarze Steine zerstört: 4"));
		add(box);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
