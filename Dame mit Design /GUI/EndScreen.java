package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class EndScreen extends JPanel{
	
	private JButton restart; 
	private JButton menu; 
	private JLabel text; 
	public EndScreen() {
		
		restart = new JButton("play again");
		menu = new JButton("menu");
		
		//Grid Layout für die Anordnung der Komponenten
		setLayout(new FlowLayout());		
		
		
	
		//Farben initialisieren
		
		Color backgroundColor = new Color(82, 146, 146); 
		Color textColor = new Color(186, 219, 219);
		Color buttonBackground = new Color(50, 122, 122); 
		Color borderColor = new Color(130, 182, 182); 
		
		setBackground(backgroundColor);
		
		//Header-Logo initialisieren
		text = new JLabel("Game Over"); 
		text.setPreferredSize(new Dimension(3000, 300));
		text.setForeground(textColor);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		
		Border border = new LineBorder(borderColor, 15);
		
		restart.setBorder(border);
		restart.setForeground(textColor);		
		restart.setBackground(buttonBackground);
		restart.setContentAreaFilled(false);
        restart.setOpaque(true);
		restart.setPreferredSize(new Dimension(300, 150));
		
		menu.setBorder(border);
		menu.setForeground(textColor);		
		menu.setBackground(buttonBackground);
		menu.setContentAreaFilled(false);
		menu.setOpaque(true);
		menu.setPreferredSize(new Dimension(300, 150));
		
		try {
			URL url = getClass().getResource("PermanentMarker.ttf");
			Font headerFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(url.getPath()))).deriveFont(Font.PLAIN, 100);
			Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(url.getPath()))).deriveFont(Font.PLAIN, 30);  

			
			text.setFont(headerFont);
			menu.setFont(buttonFont);
			restart.setFont(buttonFont);
			

			
		} catch (Exception e) {
		     System.out.println(e);
		}

        
        add(text); 
        add(restart); 
        add(menu);
	}
	
	public JButton getRestart() {
		return restart;
	}
	
	public JButton getMenu() {
		return menu;
	}

}
