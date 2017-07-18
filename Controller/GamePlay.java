package Controller;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import GUI.Feld;
import GUI.SpielFrame;
import GUI.Spielbrett;
import Model.Einfach;
import Model.Stein;

public class GamePlay {

	public final int amZugWEISS = 1;
	public final int amZugSCHWARZ = 2;
	/**
	 * Enth�lt einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	public int amZug = amZugSCHWARZ;
	private boolean istZugBeginn = true; // CONTROLLER??
	private Feld [][] felder = new Feld [8][8];
	private Stein st = null;
	Spielbrett sf;
	public Spielregeln regeln;
	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enth�lt.
	 */
	public GamePlay(){
		sf = new Spielbrett(this);
		regeln = new Spielregeln(this);
	}
	
	/**
	 * Getter f�r den zugBeginn. Ist dieser boolean true, ist gerade der Beginn des Zuges. Ist sie False, dann nicht. 
	 * @return
	 * @see com.GUI.Feld.steinWeg() 
	 */
	public boolean getZugBeginn(){
		return istZugBeginn;
	}
	
	/**
	 * Nach Schritt 1 des Zuges, also dem Ausw�hen des zu verschiebenden Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass jetzt der 
	 * Zugbeginn vorbei ist. 
	 */
	public void merkeZugBeginn(){ 
		istZugBeginn = false;
	}
	
	/**
	 * Nach Schritt 2 des Zuges, also dem Ausw�hen der gew�nschten neuen Posotion des Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass nun der Zug zuende ist und beim
	 * n�chsten Klick der n�chste Spieler mit seinem Zug beginnt.
	 * @param stein 
	 */
	public void merkeZugEnde(Stein stein){ 
		if(stein.getIstSchwarz()){
			amZug = amZugWEISS; // Spieler nach Zug Ende �ndern
		}
		else amZug = amZugSCHWARZ;
		istZugBeginn = true;
	}
	
	/**
	 * Diese Methode deklariert die Spielregeln und wird wohl noch ins Model oder in den Controller umgesetzt. Wird eine Spielregel nicht befolgt, wird durch diese Methode der Spielzug f�r ung�ltig erkl�rt
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
		int x2 = ziel.getSpalte(); // gew�nschtes x
		int y2 = ziel.getZeile(); // gew�nschtes y
		
		int dX = x2 - x1; // Abstand zwischen bisherigem und gew�nschtem x
		int dY = y2 - y1; // Abstand zwischen bisherigem und gew�nschtem y
		
		int stepX = dX > 0 ? 1 : -1; // ist dX positiv, soll stepX=1 sein. Ist dX negativ, soll stepX= -1 sein
		int stepY = dY > 0 ? 1 : -1; // ***** Verk�rzte Schreibweise f�r if Schleife. *****
				
		
		
		
		if(Math.abs(dX) > 1){ // Wenn der Zug l�nger als 1 Feld ist:
			
			
			// Letztes Feld vor dem ziel-Stein: 
			Feld letztesFeld = felder[y2 - stepY][x2 - stepX];
			Stein letzterStein = letztesFeld.getStein();
			
			if (letzterStein != null){
				// Stein eigener Farbe kann nichht �bersprungen werden
				if(letzterStein.getIstSchwarz() == stein.getIstSchwarz()){
					return false;	
				}
			}
		
			// Beim Einfachen Stein darf der �bersprungene Stein nicht leer sein
				if(stein.getClass().getCanonicalName().equals("Model.Einfach") && letzterStein == null){
						return false;	
				} 
			// Zug l�nger als 2 Felder(Dame)
			if(Math.abs(dX) > 2){
				int z = y1 + stepY; // Zeile
				int s = x1 + stepX; // Spalte
				
			// Erstes bis VORletztes Feld m�ssen leer sein:
				for( int i = 0; i< Math.abs(dX) - 2; i++){
					if(felder[z][s].getStein() != null){
						
						return false;
					}
					z+= stepY;
					s += stepX;
				}	
			}
			letztesFeld.steinWeg(); // Den stein des zuletzt �bersprungenen Feldes entfernen
			
			// Der Spielzug der Dame muss beendet werden, wenn keine M�glichkeit zum schlagen eines Steins mehr vorhanden ist
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
		GamePlay gp = new GamePlay();
	}
}
