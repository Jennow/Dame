package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class StartScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton startOff; 
	private JButton startOn; 
	private JLabel welcome; 
	
	
	
	public StartScreen() {
		
		//Grid Layout für die Anordnung der Komponenten
		setLayout(new FlowLayout());		
		
		
	
		//Farben initialisieren
		
		Color backgroundColor = new Color(82, 146, 146); 
		Color textColor = new Color(186, 219, 219);
		Color buttonBackground = new Color(50, 122, 122); 
		Color borderColor = new Color(130, 182, 182); 
		
		setBackground(backgroundColor);
		
		//Header-Logo initialisieren
		welcome = new JLabel("Dame-It"); 
		welcome.setPreferredSize(new Dimension(3000, 300));
		welcome.setForeground(textColor);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);


		
		//Button initialisieren. Jeder Button kommt in ein Panel
		
		
		startOff = new JButton("Offline Game");		
		startOn = new JButton("Online Game"); 
		
		
		startOff.setForeground(textColor);
		startOn.setForeground(textColor);
		
		startOff.setBackground(buttonBackground);
        startOff.setContentAreaFilled(false);
        startOff.setOpaque(true);

        startOn.setBackground(buttonBackground);
        startOn.setContentAreaFilled(false);
        startOn.setOpaque(true);
		
		
		

		
		
		startOn.setPreferredSize(new Dimension(300, 150));
		startOff.setPreferredSize(new Dimension(300, 150));
		
		Border border = new LineBorder(borderColor, 15);
		startOn.setBorder(border);
		startOff.setBorder(border);

		
				
		try {
			URL url = getClass().getResource("PermanentMarker.ttf");
			Font headerFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(url.getPath()))).deriveFont(Font.PLAIN, 100);
			Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(url.getPath()))).deriveFont(Font.PLAIN, 30);  

			
			welcome.setFont(headerFont);
			startOff.setFont(buttonFont);
			startOn.setFont(buttonFont); 		
			

			
		} catch (Exception e) {
		     System.out.println(e);
		}
		
		
		add(welcome);
		add(startOn);
		add(startOff); 
		
		
		
		
	}
	
	public JButton getStartOff() {
		return startOff;
	}
	
	public JButton getStartOn() {
		return startOn;
	}
	

	
	

}
