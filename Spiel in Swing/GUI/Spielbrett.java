package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.GamePlay;
import Model.Einfach;
import Model.Stein;

/**
 * Das Spielbrett erbt von JFrame (Später ändern in JPanel, um Graphics zu verwenden?). Es enthält 10x10 Felder, die in einem Schachbrettmuster angeordnet sind.
 * Momentan kümmert es sich um den Spielverlauf, das soll aber später im Controller geschehen.
 * @author jenif
 *
 */
public class Spielbrett extends JFrame{
	
	
	Icon einfachSchwarz = new ImageIcon(getClass().getResource("EinfachSchwarz.png"));
	Icon einfachWeiß = new ImageIcon(getClass().getResource("EinfachWeiß.png"));
	
	
	/**
	 * Enthält einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	private int amZug;
	private boolean istZugBeginn = true; // CONTROLLER??
	public Feld [][] felder = new Feld [8][8];
	private Stein st = null;
	public GamePlay gp;
	int weiter =5;
	
	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enthält.
	 */
	public Spielbrett(GamePlay gp){
		
		this.gp = gp;
		amZug = gp.amZugWEISS;
		
		
		setLayout(new GridLayout(8,8));
		boolean schwarz = false;
		for(int z = 0; z<felder.length ; z++){ //zeile
			for(int s =0; s<felder[z].length ; s++){ // spalte
				Feld feld = new Feld( gp, this, schwarz, z, s);
				
//				feld.addActionListener(new FeldListener());
				feld.addActionListener( new ActionListener () {
					
					@Override
					public void actionPerformed(ActionEvent event){
					Feld clicked = (Feld) event.getSource();
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
							if(gp.regeln.canJump(clicked.getStein(), felder)){
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
							st.getFeld().setStein(st); // Stein auf Ausgangsfeld zurücksetzen 
							amZug = amZugMem; // Zug ist noch nicht zuende.
							// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht möglich"
						}
					}
					if (gp.hasWon()==0 && gp.getZugBeginn()){
							weiter = JOptionPane.showConfirmDialog(null, "Spieler Weiß hat gewonnen! Willst du nochmal spielen?");
						}
						else if(gp.hasWon() == 1 && gp.getZugBeginn()){
							weiter = JOptionPane.showConfirmDialog(null, "Spieler Weiß hat gewonnen! Willst du nochmal spielen?");
						}
						if (weiter == 0){
							new GamePlay();
							dispose();
						}		
						else if(weiter == 1){
							dispose();
						}
					}
				});
				felder[z][s] =  feld;// gerade erstelltes Feld an der jeweiligen Position einsetzen
				if(schwarz){ // Felder einfärben
					feld.setBackground(Color.black);
					if(z <=2 ){ // die oberen 4 Reihen bekommen weiße, einfache Steine
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
				add(feld); // Dieses neue Feld hinzufügen
				schwarz = !schwarz; // wechsel der Feldfarbe von Feld zu Feld zum Erstellen des Schachbrett-Musters
			}
			schwarz = !schwarz; // bei Zeilenwechsel soll ebenfalls die Feldfarbe geändert werden
		}
		setVisible(true);
		setSize(420,420); // Größe noch anpassen
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}		
}

