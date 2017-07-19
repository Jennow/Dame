package com.example.jenif.dameapp.Controller;


import static com.example.jenif.dameapp.SpielActivity.buttons;

public class Spielregeln {
	
GamePlay gp;
 
 public Spielregeln(GamePlay gp) {
	 this.gp = gp;
 
 }
 /**Legt fest, auf welche Felder man seinen Stein legen darf.
  * 
  * @param stein
  * @param ziel
  * @return
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
		int letztesX;
		int letztesY;
		
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
			letztesX = letztesFeld.getSpalte();
			letztesY = letztesFeld.getZeile();
			letztesFeld.steinWeg(buttons[letztesY][letztesX]);
		}
		return true;
	}

/**
 * �berpr�ft, ob der Stein einen weiteren Sprung machen kann
 * @param stein - Der Stein, der eben seinen Zug vollendet hat
 * @param felder - alle Felder auf dem Spielfeld
 * @return
 */
public boolean canJump(Stein stein, Feld[][] felder) // Wirft oft eine Nullpointer Ecxeption und dann ist es Zufall, ob nun true oder false herauskommt...
{
	int steinX = stein.getFeld().getSpalte();
	int steinY = stein.getFeld().getZeile();
	try{
		if(stein.getIstSchwarz() && stein.getClass().getCanonicalName().equals("Model.Dame")){ // Wenn der Stein einfach und wei� ist
		
			if(steinX >1 && steinY>1 && !felder[steinY-1][steinX-1].getStein().getIstSchwarz() && felder[steinY-2][steinX-2].getStein()==null){ // oben links
				return true;
			}
			if(steinX <6 && steinY>1 && !felder[steinY+1][steinX-1].getStein().getIstSchwarz() && felder[steinY+2][steinX-2].getStein()==null){ // unten links
				return true;
			}
		}
		else if(!stein.getIstSchwarz() && stein.getClass().getCanonicalName().equals("Model.Dame")){ // Ernn der Stein einfach uns schwarz ist
			if(steinX >1 && steinY<6 && felder[steinY-1][steinX+1].getStein().getIstSchwarz() && felder[steinY-2][steinX+2].getStein()==null){ // oben rechts
				return true;
			}
			if(steinX <6 && steinY<6 && felder[steinY+1][steinX+1].getStein().getIstSchwarz() && felder[steinY+2][steinX+2].getStein()==null){ // unten rechts
				return true;
			}	
		}
		
		 
	}catch (Exception e){
		System.out.println("NullPointer");
	}
	return false;
}
}
