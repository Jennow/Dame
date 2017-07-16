package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Einfach;
import Model.Stein;

/**
 * Das Spielbrett erbt von JFrame (Später ändern in JPanel, um Graphics zu verwenden?). Es enthält 10x10 Felder, die in einem Schachbrettmuster angeordnet sind.
 * Momentan kümmert es sich um den Spielverlauf, das soll aber später im Controller geschehen.
 * @author jenif
 *
 */
public class Spielbrett extends JFrame{
	
	private final int amZugWEISS = 1;
	private final int amZugSCHWARZ = 2;
	/**
	 * Enthält einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	private int amZug = amZugWEISS;
	private boolean istZugBeginn = true; // CONTROLLER??
	private Feld [][] felder = new Feld [10][10];
	private Stein st = null;
	
	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enthält.
	 */
	public Spielbrett(){
		
		JPanel square = new JPanel(); //Quadratisches JPanel erstellen
		square.setSize(300,300); // Erstmal dem JPanel Maße zuweisen
		square.setLayout(new GridLayout(10,10));
		boolean schwarz = true;
		for(int z = 0; z<felder.length ; z++){ //zeile
			for(int s =0; s<felder[z].length ; s++){ // spalte
				Feld feld = new Feld( this, schwarz, z, s);
				
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
					if(getZugBeginn()){ // Stein "In die Hand nehmen"
						
						if (( st = clicked.getStein()) != null){
							if(st.getIstSchwarz() && (amZug == amZugSCHWARZ) ){
							clicked.steinWeg();
							}
							else if(!st.getIstSchwarz() && (amZug == amZugWEISS) ){
								clicked.steinWeg();
							}
						}
					} 
					else {
						if(st.istOk(clicked) && istOk(st, clicked)){
							clicked.setStein(st); // Stein absetzen
							if(st.getClass().getCanonicalName().equals("Model.Dame")){
								
							}
						}
						else {
							st.getFeld().setStein(st); // Stein auf Ausgangsfeld zurücksetzen 
							amZug = amZugMem; // Zug ist noch nicht zuende.
							// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht möglich"
						}
					}
				});
				felder[z][s] =  feld;// gerade erstelltes Feld an der jeweiligen Position einsetzen
				if(schwarz){ // Felder einfärben
					feld.setBackground(Color.darkGray);
					if(z <=3 ){ // die oberen 4 Reihen bekommen weiße, einfache Steine
						feld.setStein( new Einfach(feld, false), true);
						new Einfach(feld, false);
						feld.setForeground(Color.white);
					}
					else if( z>=6 ){ //Die unteren 4 Reihen bekommen schwarze, einfache Steine
						feld.setStein( new Einfach(feld, true) , true);
						new Einfach(feld, true); 
						feld.setForeground(Color.black);
					}
					
				}
				else feld.setBackground(Color.white);
				square.add(feld); // Dieses neue Feld hinzufügen
				schwarz = !schwarz; // wechsel der Feldfarbe von Feld zu Feld zum Erstellen des Schachbrett-Musters
			}
			schwarz = !schwarz; // bei Zeilenwechsel soll ebenfalls die Feldfarbe geändert werden
		}
		add(square);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Getter für den zugBeginn. Ist dieser boolean true, ist gerade der Beginn des Zuges. Ist sie False, dann nicht. 
	 * @return
	 * @see com.GUI.Feld.steinWeg() 
	 */
	public boolean getZugBeginn(){
		return istZugBeginn;
	}
	
	/**
	 * Nach Schritt 1 des Zuges, also dem Auswähen des zu verschiebenden Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass jetzt der 
	 * Zugbeginn vorbei ist. 
	 */
	public void merkeZugBeginn(){ 
		istZugBeginn = false;
	}
	
	/**
	 * Nach Schritt 2 des Zuges, also dem Auswähen der gewünschten neuen Posotion des Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass nun der Zug zuende ist und beim
	 * nächsten Klick der nächste Spieler mit seinem Zug beginnt.
	 * @param stein 
	 */
	public void merkeZugEnde(Stein stein){ 
		if(stein.getIstSchwarz()){
			amZug = amZugWEISS; // Spieler nach Zug Ende ändern
		}
		else amZug = amZugSCHWARZ;
		istZugBeginn = true;
	}
	
	/**
	 * Diese Methode deklariert die Spielregeln und wird wohl noch ins Model oder in den Controller umgesetzt. Wird eine Spielregel nicht befolgt, wird durch diese Methode der Spielzug für ungültig erklärt
	 * @param stein
	 * @param ziel
	 * @return boolean, der dann true ist, wenn alle Regeln befolgt wurden.
	 */
	public boolean istOk (Stein stein, Feld ziel){
		
		// Zielfeld darf nicht schon besetzt sein
		if (ziel.getStein() !=null ){
			return false;
		}
		
		int x1 = stein.getFeld().getSpalte(); // bisheriges x
		int y1 = stein.getFeld().getZeile(); // bisheriges y
		int x2 = ziel.getSpalte(); // gewünschtes x
		int y2 = ziel.getZeile(); // gewünschtes y
		
		int dX = x2 - x1; // Abstand zwischen bisherigem und gewünschtem x
		int dY = y2 - y1; // Abstand zwischen bisherigem und gewünschtem y
		
		int stepX = dX > 0 ? 1 : -1; // ist dX positiv, soll stepX=1 sein. Ist dX negativ, soll stepX= -1 sein
		int stepY = dY > 0 ? 1 : -1; // ***** Verkürzte Schreibweise für if Schleife. *****
				
		
		
		
		if(Math.abs(dX) > 1){ // Wenn der Zug länger als 1 Feld ist:
			
			
			// Letztes Feld vor dem ziel-Stein: 
			Feld letztesFeld = felder[y2 - stepY][x2 - stepX];
			Stein letzterStein = letztesFeld.getStein();
			
			if (letzterStein != null){
				// Stein eigener Farbe kann nichht übersprungen werden
				if(letzterStein.getIstSchwarz() == stein.getIstSchwarz()){
					return false;	
				}
			}
		
			// Beim Einfachen Stein darf der übersprungene Stein nicht leer sein
				if(stein.getClass().getCanonicalName().equals("Model.Einfach") && letzterStein == null){
						return false;	
				} 
			// Zug länger als 2 Felder(Dame)
			if(Math.abs(dX) > 2){
				int z = y1 + stepY; // Zeile
				int s = x1 + stepX; // Spalte
				
			// Erstes bis VORletztes Feld müssen leer sein:
				for( int i = 0; i< Math.abs(dX) - 2; i++){
					if(felder[z][s].getStein() != null){
						
						return false;
					}
					z+= stepY;
					s += stepX;
				}	
			}
			letztesFeld.steinWeg(); // Den stein des zuletzt übersprungenen Feldes entfernen
			
			// Der Spielzug der Dame muss beendet werden, wenn keine Möglichkeit zum schlagen eines Steins mehr vorhanden ist
			try{ // Wegen der Out of Bound exception
				if(Math.abs(dX) > 2){
			
					if(stepX==-1){ // Funktioniert noch nicht richtig
						for(int z = y1; z >=0; z--){ // Die Diagonalen nach oben absuchen
								if( felder[z][x1-(y1-z)].getStein() == null){ //Diagonale nach links oben
									if (felder[z-1][x1-(y1-z)-1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
									else merkeZugEnde(stein);
								}
								else if (felder[z][x1+(y1-z)].getStein() == null){ // Diagonale nach rechts oben
									if(felder[z-1][x1+(y1-z)-1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
									else merkeZugEnde(stein);
								}
						}
					}
					else if(stepX == 1){
						for(int z = y1; z <=9; z++){ // Die diagonalen nach unten absuchen
							if( felder[z][x1-(y1-z)].getStein() == null){ //Diagonale nach links unten
								if (felder[z+1][x1-(y1-z)+1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
								else merkeZugEnde(stein);
							}
							else if (felder[z][x1+(y1-z)].getStein() == null){ // Diagonale nach rechts unten
								if(felder[z+1][x1+(y1-z)+1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
								else merkeZugEnde(stein);
							}
						}
					}
				} 
			} catch (Exception e){}
		}
		
		return true;
	}
	
	/**
	 * Main Methode, die Ein neues Spielbrett erzeugt.
	 * @param args
	 */
	public static void main(String[] args) {
		Spielbrett s = new Spielbrett();
	}

}

