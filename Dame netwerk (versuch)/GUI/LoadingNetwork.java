package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoadingNetwork extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel text; 
	
	public LoadingNetwork() {
		
		setLayout(new FlowLayout());	
		
		Color backgroundColor = new Color(82, 146, 146); 
		Color textColor = new Color(186, 219, 219);
		
		setBackground(backgroundColor);
		
		text = new JLabel("Loading..."); 
		text.setPreferredSize(new Dimension(3000, 600));
		text.setForeground(textColor);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		try {
			Font headerFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("/Users/simon/Documents/Java/DameTest/src/GUI/PermanentMarker.ttf"))).deriveFont(Font.PLAIN, 100);
			Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("/Users/simon/Documents/Java/DameTest/src/GUI/PermanentMarker.ttf"))).deriveFont(Font.PLAIN, 30);  

			
			text.setFont(headerFont);
			

			
		} catch (Exception e) {
		     System.out.println(e);
		}
		
		add(text); 
		
	}
	
	

}
