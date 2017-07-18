package Controller;

import GUI.Feld;
import GUI.Spielbrett;
import Model.Stein;

public class Spielregeln {
	
GamePlay gp;
 
 public Spielregeln(GamePlay gp) {
	 this.gp = gp;
 
 }
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
			Feld letztesFeld = gp.sf.felder[y2 - stepY][x2 - stepX];
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
					if(gp.sf.felder[z][s].getStein() != null){
						
						return false;
					}
					z+= stepY;
					s += stepX;
				}	
			}
			letztesFeld.steinWeg(); // Den stein des zuletzt �bersprungenen Feldes entfernen
			
//			// Der Spielzug der Dame muss beendet werden, wenn keine M�glichkeit zum schlagen eines Steins mehr vorhanden ist
//			try{ // Wegen der Out of Bound exception
//				if(Math.abs(dX) > 2){
//			
//					if(stepX==-1){ // Funktioniert noch nicht richtig
//						for(int z = y1; z >=0; z--){ // Die Diagonalen nach oben absuchen
//								if( felder[z][x1-(y1-z)].getStein() == null){ //Diagonale nach links oben
//									if (felder[z-1][x1-(y1-z)-1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
//									else gp.merkeZugEnde(stein);
//								}
//								else if (felder[z][x1+(y1-z)].getStein() == null){ // Diagonale nach rechts oben
//									if(felder[z-1][x1+(y1-z)-1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
//									else gp.merkeZugEnde(stein);
//								}
//						}
//					}
//					else if(stepX == 1){
//						for(int z = y1; z <=9; z++){ // Die diagonalen nach unten absuchen
//							if( felder[z][x1-(y1-z)].getStein() == null){ //Diagonale nach links unten
//								if (felder[z+1][x1-(y1-z)+1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
//								else gp.merkeZugEnde(stein);
//							}
//							else if (felder[z][x1+(y1-z)].getStein() == null){ // Diagonale nach rechts unten
//								if(felder[z+1][x1+(y1-z)+1].getStein() != null){} // Nichts Passiert. Der Spieler ist noch am Zug
//								else gp.merkeZugEnde(stein);
//							}
//						}
//					}
//				} 
//			} catch (Exception e){}
		}
		return true;
	}
}
