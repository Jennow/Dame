package com.example.jenif.dameapp.Controller;

import android.widget.Button;
import android.widget.ImageButton;

import static com.example.jenif.dameapp.R.mipmap.ds;
import static com.example.jenif.dameapp.R.mipmap.dw;
import static com.example.jenif.dameapp.R.mipmap.es;
import static com.example.jenif.dameapp.R.mipmap.ew;

/**
 * Ein Feld ist eines der 64 K�stchen, die auf dem Spielbrett zu finden sind. Es ist anklickbar und erbt daher von JButton.
 * @author jenif
 *
 */
public class Feld { // Jedes Feld ist ein JButton

	private Spielbrett brett;
	private boolean istSchwarz;
	private Stein stein = null; // Nicht �berall d�rfen Steine sein (zB. auf wei�en Feldern nicht)
	private int zeile;
	private int spalte;
	GamePlay gp;
	ImageButton btn;

	// Bilder, die momentan die Steine symbolisieren
	//Image einfachSchwarz = new ImageIcon(getClass().getResource("EinfachSchwarz.png")).getImage();
	//ImageIcon eS = new ImageIcon(einfachSchwarz.getScaledInstance(40,40,Image.SCALE_FAST ));
	//Image einfachWei� = new ImageIcon(getClass().getResource("EinfachWei�.png")).getImage();
	//ImageIcon eW = new ImageIcon(einfachWei�.getScaledInstance(40,40,Image.SCALE_FAST ));
	//Image dameSchwarz = new ImageIcon(getClass().getResource("DameSchwarz.png")).getImage();
	//ImageIcon dS = new ImageIcon(dameSchwarz.getScaledInstance(40,40,Image.SCALE_FAST ));
	//Image dameWei� = new ImageIcon(getClass().getResource("DameWei�.png")).getImage();
	//ImageIcon dW = new ImageIcon(dameWei�.getScaledInstance(40,40,Image.SCALE_FAST ));

	/**
	 * Ein Fald wird erzeugt
	 * @param brett ist das Spielbrett auf dem sich das Feld befindet
	 * @param istSchwarz legt fest, ob ein Feld schwarz oder wei� ist
	 * @param zeile Position des Feldes in y Richtung
	 * @param spalte Position des Feldes in x Richtung
	 */
	public Feld(GamePlay gp, Spielbrett brett,ImageButton btn, boolean istSchwarz, int zeile, int spalte){
		this.brett = brett;
		this.istSchwarz = istSchwarz;
		this.zeile = zeile;
		this.spalte = spalte;
		this.gp = gp;
		this.btn = btn;
	}

	/**
	 * Setter, der die Methode setStein(Stein stein, boolean init) mit dem boolean Wert false aufruft. Dies bedeutet, dass das Feld gerade nicht mehr dabei ist, sich zu initialisieren
	 * @param stein der auf das Feld gelegt wird
	 */
	public void setStein(Stein stein, ImageButton btn) {
		if(stein.getFeld().getZeile() == 0 || stein.getFeld().getZeile() == 7){ // Wenn man einen Einfachen STein aus diesen Reihen anklickt und gleich wieder ablegt, sollen sie nicht zu einer Dame werden. Daher wird der Wert init auf True gesetzt.
			setStein(stein, true, btn);
		}
		else setStein(stein, false, btn);
	}

	/**
	 * Setter, der einen Stein auf das entsprechende Feld legt. Au�erdem wird sich gemerkt, dass der Zug zuende ist und je nach Farbe und Steintyp wird ein anderer Icon genutzt.
	 * @param stein der auf das Feld gesetzt werden soll
	 * @param init ist dieser Wert true, befindet sich das Spielfeld in der initialisierungsphase und es werden noch keine Dame-Steine auf die letzten Reihen gelegt
	 */
	public void setStein(Stein stein, boolean init, ImageButton btn) {

		if( !init && stein.getClass().getCanonicalName().equals("Model.Einfach") && (zeile == 0 || zeile == 7)){
			stein = new Dame (this, stein.getIstSchwarz());
		}
		this.stein = stein;
		gp.merkeZugEnde(stein);
		stein.setFeld(this);
		try{
			if(stein.getClass().getCanonicalName().equals("Model.Einfach") && stein.getIstSchwarz()){
				btn.setImageResource(es);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Einfach") && !stein.getIstSchwarz()){
				btn.setImageResource(ew);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Dame") && stein.getIstSchwarz()){
				btn.setImageResource(ds);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Dame") && !stein.getIstSchwarz()){
				btn.setImageResource(dw);
			}
		} catch ( Exception e){};

	}
	/**
	 * Getter f�r das Spielbrett, auf dem das Feld liegt
	 * @return Spielbrett
	 */
	public Spielbrett getBrett() {
		return brett;
	}
	
	/**
	 * Getter f�r den Stein, der auf dem Feld liegt.
	 * @return Stein
	 */
	public Stein getStein() {
		return stein;
	}
	
	/**
	 * Ein Stein wird gel�scht. Ein neuer Zug beginnt
	 */
	public void steinWeg(ImageButton btn){
		stein = null;
		gp.merkeZugBeginn();
		btn.setImageResource(android.R.color.transparent);
	}
	
	/**
	 * Getter f�r die Zeile eines Feldes
	 * @return
	 */
	public int getZeile() {
		return zeile;
	}
	
	/**
	 * Getter f�r die Spalte eines Faldes
	 * @return
	 */
	public int getSpalte() {
		return spalte;
	}

	public ImageButton getBtn() {
		return btn;
	}
}
