package Model;

import GUI.Feld;

/**
 * in istOk sind hier die Laufregeln des Dame Steins festgelegt. Sie darf sowohl nach oben als auch nach unten laufen. Allerdings immer in einer diagonalen. Man darf mit der Dame in einem Zug beliebig viele
 * Felder überspringen. 
 * @author Simon und Jenny
 *
 */
public class Dame extends Stein{
	
	/**Ein Dame Stein wird erzeugt. Die Parameter werden von der Superklasse Stein übernommen.
	 * @param feld bezeichnet das Feld auf welchem sich der Stein befindet
	 * @param istSchwarz zeigt an, ob der Stein schwarz(true) oder weiß(false) ist
	 */public Dame(Feld feld, boolean istSchwarz) {
		super(feld, istSchwarz);
	}

	@Override
	public boolean istOk(Feld feld) {
	
		int dX = feld.getSpalte() - getFeld().getSpalte(); // Neues X - Ausgang-X= Anz der übersprungenen Spalten
		int dY = feld.getZeile() - getFeld().getZeile(); // Neues Y - Ausgang-Y= Anz der übersprungenen Zeilen
		
		if (Math.abs(dX) != Math.abs(dY)){ // Prüfen, ob es sich um eine Diagonale handelt -> |dX| muss = |dY| sein
			return false;
		}
		else if (Math.abs(dX) == 0){ // Man darf nur mehr als 0 Schritte machen
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return "\u25c9";
	}
}
