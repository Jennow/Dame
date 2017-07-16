package Model;

import GUI.Feld;

public class Dame extends Stein{

	public Dame(Feld feld, boolean istSchwarz) {
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
