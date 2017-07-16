package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Model.Dame;
import Model.Stein;

public class Feld extends JButton { // Jedes Feld ist ein JButton

	private Spielbrett brett;
	private boolean istSchwarz; 
	private Stein stein = null; // Nicht überall dürfen Steine sein (zB. auf weißen Feldern nicht)
	private int zeile;
	private int spalte;
	
	
	public Feld( Spielbrett brett, boolean istSchwarz, int zeile, int spalte){
		this.brett = brett;
		this.istSchwarz = istSchwarz;
		this.zeile = zeile;
		this.spalte = spalte;
	}
	
	public void setStein(Stein stein) {
		setStein(stein, false);
	}
	
	public void setStein(Stein stein, boolean init) {
		if( !init && stein.getClass().getCanonicalName().equals("Model.Einfach") && (zeile == 0 || zeile == 9)){
			stein = new Dame (this, stein.getIstSchwarz());
		}
		this.stein = stein;
		brett.merkeZugEnde(stein);
		stein.setFeld(this);
		if(stein.getIstSchwarz()){
		setForeground(Color.black);	
		}
		else setForeground(Color.white);
		
		setText(stein.toString());
	}
	public Spielbrett getBrett() {
		return brett;
	}
	
	public Stein getStein() {
		return stein;
	}
	
	public void steinWeg(){
		stein = null;
		brett.merkeZugBeginn();
		setText("");
	}
	
	public int getZeile() {
		return zeile;
	}
	
	public int getSpalte() {
		return spalte;
	}
	
}
