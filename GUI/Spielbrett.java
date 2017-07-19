package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.GamePlay;
import Model.Einfach;
import Model.Stein;

/**
 * Das Spielbrett erbt von JFrame (Sp�ter �ndern in JPanel, um Graphics zu verwenden?). Es enth�lt 10x10 Felder, die in einem Schachbrettmuster angeordnet sind.
 * Momentan k�mmert es sich um den Spielverlauf, das soll aber sp�ter im Controller geschehen.
 * @author jenif
 *
 */
public class Spielbrett extends JFrame{
	
	
	Icon einfachSchwarz = new ImageIcon(getClass().getResource("EinfachSchwarz.png"));
	Icon einfachWei� = new ImageIcon(getClass().getResource("EinfachWei�.png"));
	
	
	/**
	 * Enth�lt einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	private int amZug;
	private boolean istZugBeginn = true; // CONTROLLER??
	public Feld [][] felder = new Feld [8][8];
	private Stein st = null;
	public GamePlay gp;
	int weiter =5;
	
	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enth�lt.
	 */
	public Spielbrett(GamePlay gp){
		
		this.gp = gp;
		amZug = gp.amZugWEISS;
		
		
		setLayout(new GridLayout(8,8));
		boolean schwarz = false;
		for(int z = 0; z<felder.length ; z++){ //zeile
			for(int s =0; s<felder[z].length ; s++){ // spalte
				Feld feld = new Feld( gp, this, schwarz, z, s);
				
				feld.addActionListener( event -> {
					Feld clicked = null;
					for(int z1 = 0; z1<felder.length ; z1++){
						for(int s1 =0; s1<felder[z1].length ; s1++){
							if(event.getSource() == felder[z1][s1]){ // Event Source mit allen Feldern vergleichen, um das geklickte Feld zu ermitteln
								clicked = felder[z1][s1]; // Feld merken
								break; // Nach Finden des Feldes kann Schleifendurchlauf abgebrochen werden.
							}
						}//zeile
					}
					int amZugMem = amZug; // merken, wer am Zug ist
					if(gp.getZugBeginn()){ // Stein "In die Hand nehmen"
						
						if (( st = clicked.getStein()) != null){
							if(st.getIstSchwarz() && (amZug == gp.amZugSCHWARZ) ){
							clicked.steinWeg();
							}
							else if(!st.getIstSchwarz() && (amZug == gp.amZugWEISS) ){
								clicked.steinWeg();
							}
						}
					} 
					else {
						if(st.istOk(clicked) && gp.regeln.istOk(st, clicked)){
							clicked.setStein(st); // Stein absetzen
							if(gp.regeln.canJump(clicked.getStein(), felder) && clicked.getClass().getCanonicalName().equals("Model.Dame")){
								amZug = amZug;
							}
							else if(amZug == gp.amZugSCHWARZ ){
								amZug = gp.amZugWEISS;
							}
							else amZug = gp.amZugSCHWARZ;
							if(st.getClass().getCanonicalName().equals("Model.Dame")){
								
							}
						}
						else {
							st.getFeld().setStein(st); // Stein auf Ausgangsfeld zur�cksetzen 
							amZug = amZugMem; // Zug ist noch nicht zuende.
							// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht m�glich"
						}
						if (gp.hasWon()==0){
							weiter = JOptionPane.showConfirmDialog(null, "Spieler Wei� hat gewonnen! juhiuuuu");
						}
						else if(gp.hasWon() == 1){
							weiter = JOptionPane.showConfirmDialog(null, "Spieler Wei� hat gewonnen! juhiuuuu");
						}
						if (weiter == 0){
							new GamePlay();
						}		
					}
					
				
				});
				felder[z][s] =  feld;// gerade erstelltes Feld an der jeweiligen Position einsetzen
				if(schwarz){ // Felder einf�rben
					feld.setBackground(Color.black);
					if(z <=2 ){ // die oberen 4 Reihen bekommen wei�e, einfache Steine
						feld.setStein( new Einfach(feld, false), true);
						new Einfach(feld, false);
						feld.setForeground(Color.white);
					}
					else if( z>=5 ){ //Die unteren 4 Reihen bekommen schwarze, einfache Steine
						feld.setStein( new Einfach(feld, true) , true);
						new Einfach(feld, true); 
						feld.setForeground(Color.white);
					}
					
				}
				else feld.setBackground(Color.white);
				add(feld); // Dieses neue Feld hinzuf�gen
				schwarz = !schwarz; // wechsel der Feldfarbe von Feld zu Feld zum Erstellen des Schachbrett-Musters
			}
			schwarz = !schwarz; // bei Zeilenwechsel soll ebenfalls die Feldfarbe ge�ndert werden
		}
		setVisible(true);
		setSize(420,420); // Gr��e noch anpassen
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

