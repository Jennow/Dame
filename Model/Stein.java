package Model;

import GUI.Feld;

public abstract class Stein {

	private Feld feld;
	private boolean istSchwarz;
	
	public Stein (Feld feld, boolean istSchwarz){
		this.feld = feld;
		this.istSchwarz = istSchwarz;
	}
	
	public void setFeld(Feld feld) {
		this.feld = feld;
		feld.getBrett().merkeZugEnde( this );
	}

	public boolean getIstSchwarz() {
		return istSchwarz;
	}
	
	public Feld getFeld(){
		return feld;
	}
	
	abstract public boolean istOk (Feld feld);
	
	abstract public String toString();
}
