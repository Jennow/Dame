package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Model.Dame;
import Model.Stein;
/**
 * Ein Feld ist eines der 100 K�stchen, die auf dem Spielbrett zu finden sind. Es ist anklickbar und erbt daher von JButton.
 * @author jenif
 *
 */
public class Feld extends JButton { // Jedes Feld ist ein JButton

	private Spielbrett brett;
	private boolean istSchwarz; 
	private Stein stein = null; // Nicht �berall d�rfen Steine sein (zB. auf wei�en Feldern nicht)
	private int zeile;
	private int spalte;
	
	/**
	 * Ein Fald wird erzeugt
	 * @param brett ist das Spielbrett auf dem sich das Feld befindet
	 * @param istSchwarz legt fest, ob ein Feld schwarz oder wei� ist
	 * @param zeile Position des Feldes in y Richtung
	 * @param spalte Position des Feldes in x Richtung
	 */
	public Feld( Spielbrett brett, boolean istSchwarz, int zeile, int spalte){
		this.brett = brett;
		this.istSchwarz = istSchwarz;
		this.zeile = zeile;
		this.spalte = spalte;
	}
	
	/**
	 * Setter, der die Methode setStein(Stein stein, boolean init) mit dem boolean Wert false aufruft. Dies bedeutet, dass das Feld gerade nicht mehr dabei ist, sich zu initialisieren
	 * @param stein der auf das Feld gelegt wird
	 */
	public void setStein(Stein stein) {
		setStein(stein, false);
	}
	
	/**
	 * Setter, der einen Stein auf das entsprechende Feld legt. Au�erdem wird sich gemerkt, dass der Zug zuende ist und die Farbe des Steines wird beibehalten
	 * @param stein der auf das Feld gesetzt werden soll
	 * @param init ist dieser Wert true, befindet sich das Spielfeld in der initialisierungsphase und es werden noch keine Dame-Steine auf die letzten Reihen gelegt
	 */
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
	public void steinWeg(){
		stein = null;
		brett.merkeZugBeginn();
		setText("");
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
	
}
