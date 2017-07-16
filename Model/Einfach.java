package Model;

import GUI.Feld;

public class Einfach extends Stein{

	public Einfach(Feld feld, boolean istSchwarz) {
		super(feld, istSchwarz);
	}

	@Override
	public boolean istOk(Feld feld) {
	
		int dX = feld.getSpalte() - getFeld().getSpalte(); // Neues X - Ausgang-X= Anz der �bersprungenen Spalten
		int dY = feld.getZeile() - getFeld().getZeile(); // Neues Y - Ausgang-Y= Anz der �bersprungenen Zeilen
		
		if (Math.abs(dX) != Math.abs(dY)){ // Pr�fen, ob es sich um eine Diagonale handelt -> |dX| muss = |dY| sein
			return false;
		}
		else if (Math.abs(dX) == 0 || Math.abs(dX) >2){ // Man darf nur 1 oder 2 Schritte machen
			return false;
		}
		else if ((getIstSchwarz() && dY > 0)  || (!getIstSchwarz() && dY < 0)){ // Schwarze Steine d�rfen nur nach oben. Wei�e Steine  nur nach unten
			return false;
		}

		return true;
	}
	
	@Override
	public String toString(){
		return "\u25ce";
	}
}

