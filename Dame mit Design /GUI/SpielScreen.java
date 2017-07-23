package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SpielScreen extends JPanel{
	
	JButton aufgeben; 
	
	
	public SpielScreen(Spielbrett board) {
		
		
		setLayout(new FlowLayout());		

		
		//Farben initialisieren
		
		Color backgroundColor = new Color(82, 146, 146); 
		Color textColor = new Color(186, 219, 219);
		Color buttonBackground = new Color(50, 122, 122); 
		Color borderColor = new Color(130, 182, 182);
		
		aufgeben = new JButton("aufgeben"); 
		aufgeben.setForeground(textColor);
		aufgeben.setBackground(buttonBackground);
	    aufgeben.setContentAreaFilled(false);
	    aufgeben.setOpaque(true);
	    
	    aufgeben.setPreferredSize(new Dimension(300, 100));
	    board.setPreferredSize(new Dimension(600, 600));
	    
	    Border border = new LineBorder(borderColor, 15);
		aufgeben.setBorder(border);
		board.setBorder(border);

		setBackground(backgroundColor);
		
		try {
			
			
			URL url = getClass().getResource("PermanentMarker.ttf");
			Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(url.getPath()))).deriveFont(Font.PLAIN, 20);  

			
			aufgeben.setFont(buttonFont);
			
			

			
		} catch (Exception e) {
		     System.out.println(e);
		}

		//board.setPreferredSize(new Dimension (500, 500));
		
		
		add(board); 
		add(aufgeben); 


	}
	
	public JButton getAufgeben() {
		return aufgeben;
	}

}
