package Model;

import GUI.Feld;

/**
 * Ein Stein ist ein Objekt, welches ein zugeh�riges Feld und eine Farbe beinhaltet. Es gibt zwei Arten von Steinen: Einfach und Dame.
 * @author Simon und Jenny
 *
 */
public abstract class Stein {

	private Feld feld;
	private boolean istSchwarz;
	
	/**
	 * Ein Steon wird erzeugt. 
	 * @param feld Bezeichnet das Feld, auf dem der Stein sitzt
	 * @param istSchwarz bezeichnet die Farbe des Steins
	 */
	public Stein (Feld feld, boolean istSchwarz){
		this.feld = feld;
		this.istSchwarz = istSchwarz;
	}
	
	/**
	 * Nachdem ein Stein auf ein neues Feld gesetzt wurde, wird das neue Feld mit dieser Methode gesetzt. Au�erdem wird die Methode merkeZugEnde( this )
	 * im Spielbrett aufgerufen.
	 * @param feld
	 * @see com.GUI.Spielbrett#merkeZugEnde(Stein stein)
	 */
	public void setFeld(Feld feld) {
		this.feld = feld;
		feld.getBrett().gp.merkeZugEnde( this );
	}

	/**
	 * Getter f�r die Farbe des Steins (Schwarz = true, wei� = false)
	 * @return boolean
	 */
	public boolean getIstSchwarz() {
		return istSchwarz;
	}
	
	/**
	 * Getter f�r das Feld, auf dem sich der Stein aktuell befindet.
	 * @return Feld
	 */
	public Feld getFeld(){
		return feld;
	}
	
	/**
	 * In den Subklassen werden in dieser Methode die Laufregeln des jeweiligen Steins festgelegt
	 * @param feld
	 * @return boolean
	 * @see com.Model.Einfach
	 * @see com.Model.Dame
	 */
	abstract public boolean istOk (Feld feld);
}
