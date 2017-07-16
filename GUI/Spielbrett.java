package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Einfach;
import Model.Stein;

public class Spielbrett extends JFrame{
	
	private final int amZugWEISS = 1;
	private final int amZugSCHWARZ = 2;
	private int amZug = amZugWEISS;
	
	private boolean istZugBeginn = true; // CONTROLLER??
	private Feld [][] felder = new Feld [10][10];
	private Stein st = null;
	
	public Spielbrett(){
		
		JPanel square = new JPanel(); //Quadratisches JPanel erstellen
		square.setSize(300,300); // Erstmal dem JPanel Maﬂe zuweisen
		square.setLayout(new GridLayout(10,10));
		boolean schwarz = true;
		for(int z = 0; z<felder.length ; z++){ //zeile
			for(int s =0; s<felder[z].length ; s++){ // spalte
				Feld feld = new Feld( this, schwarz, z, s);
				feld.addActionListener( event -> {
					Feld merk = null;

					for(int z1 = 0; z1<felder.length ; z1++){
						for(int s1 =0; s1<felder[z1].length ; s1++){
							if(event.getSource() == felder[z1][s1]){ // Event Source mit allen Feldern vergleiche, um das geklickte Feld zu ermitteln
								merk = felder[z1][s1]; // Feld merken
								break; // Nach Finden des Feldes kann Schleifendurchlauf abgebrochen werden.
							}
						}//zeile
					}
					
					int amZugMem = amZug;
					if(getZugBeginn()){ // Stein "In die Hand nehmen"
						
						if (( st = merk.getStein()) != null){
							if(st.getIstSchwarz() && (amZug == amZugSCHWARZ) ){
							merk.steinWeg();
							}
							else if(!st.getIstSchwarz() && (amZug == amZugWEISS) ){
								merk.steinWeg();
							}
						}
						
					} 
					else {
						if(st.istOk(merk) && istOk(st, merk)){
							merk.setStein(st); // Stein absetzen
						}
						else {
							st.getFeld().setStein(st); // Stein auf Ausgangsfeld zur¸cksetzen 
							amZug = amZugMem; // Zug ist noch nicht zuende.
							// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht mˆglich"
						}
					}
				});
				felder[z][s] =  feld;// neues Feld an der jeweiligen Positio initialisieren
				if(schwarz){ // Buttons einf‰rben
					feld.setBackground(Color.darkGray);
					if(z <=3 ){
						feld.setStein( new Einfach(feld, false), true);
						new Einfach(feld, false); // Einfache weiﬂe Steine auf unterste 4 Reihen
						feld.setForeground(Color.white);
					}
					else if( z>=6 ){
						feld.setStein( new Einfach(feld, true) , true);
						new Einfach(feld, true); // Einfache schwarze Steine auf oberste 4 Reihen
						feld.setForeground(Color.black);
					}
					
				}
				else feld.setBackground(Color.white);
				square.add(feld); // Dieses neue Feld hinzuf¸gen
				schwarz = !schwarz; // wechsel der Feldfarbe von Feld zu Feld zum Erstellen des Schachbrett-Musters
			}
			schwarz = !schwarz; // bei Zeilenwechsel soll ebenfalls die Feldfarbe ge‰ndert werden
		}
		add(square);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public boolean getZugBeginn(){
		return istZugBeginn;
	}
	
	/**
	 * Nach Schritt 1 des Zuges, also dem Ausw‰hen des zu verschiebenden Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass jetzt kein 
	 * Zugbeginn mehr ist. 
	 */
	public void merkeZugBeginn(){ 
		istZugBeginn = false;
	}
	
	/**
	 * Nach Schritt 2 des Zuges, also dem Ausw‰hen der gew¸nschten neuen Posotion des Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass nun der Zug zuende ist und beim
	 * n‰chsten Klick der n‰chste Zug beginnt.
	 * @param stein 
	 */
	public void merkeZugEnde(Stein stein){ 
		
		if (stein.getClass().getCanonicalName().equals("Model.Einfach")){
			if(stein.getIstSchwarz()){
				amZug = amZugWEISS; // Spieler nach Zug Ende ‰ndern
			}
			else amZug = amZugSCHWARZ;
		}
		istZugBeginn = true;
	}
	
	public boolean istOk (Stein stein, Feld ziel){
		
		// Zielfeld besetzt
		if (ziel.getStein() !=null ){
			return false;
		}
		// Zug l‰nger als 1 Feld:
		int x1 = stein.getFeld().getSpalte();
		int y1 = stein.getFeld().getZeile();
		int x2 = ziel.getSpalte();
		int y2 = ziel.getZeile();
		
		int dX = x2 - x1;
		int dY = y2 - y1;
		
		if(Math.abs(dX) > 1){
			
		int stepX = dX > 0 ? 1 : -1; // ist dX positiv, soll stepX=1 sein. Ist dX negativ, soll stepX= -1 sein
		int stepY = dY > 0 ? 1 : -1; // Verk¸rzte Schreibweise f¸r if Schleife.
			
			// Letztes Feld: 
			Feld letztesFeld = felder[y2 - stepY][x2 - stepX];
			Stein letzterStein = letztesFeld.getStein();
			
			if (letzterStein != null){
				// Stein eigener Farbe
				if(letzterStein.getIstSchwarz() == stein.getIstSchwarz()){
					return false;	
				}
			}
			
			// Einfacher Stein: leer
				if(stein.getClass().getCanonicalName().equals("Model.Einfach") && letzterStein == null){
						return false;	
				} 
			// Zug l‰nger als 2 Felder(Dame)
			if(Math.abs(dX) > 2){
				int z = y1 + stepY;
				int s = x1 + stepX;
				
			// Erstes bis VORletztes Feld:
				for( int i = 0; i< Math.abs(dX) - 2; i++){
					
					// Feld besetzt
					if(felder[z][s].getStein() != null){
						return false;
					}
					
					z+= stepY;
					s += stepX;
				}	
			}
			letztesFeld.steinWeg();
		}
			
		
		return true;
		
	}
	
	public static void main(String[] args) {
		Spielbrett s = new Spielbrett();
	}

}

