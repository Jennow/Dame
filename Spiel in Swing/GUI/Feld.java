package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Controller.GamePlay;
import Model.Dame;
import Model.Stein;
/**
 * Ein Feld ist eines der 64 Kästchen, die auf dem Spielbrett zu finden sind. Es ist anklickbar und erbt daher von JButton.
 * @author jenif
 *
 */
public class Feld extends JButton { // Jedes Feld ist ein JButton

	private Spielbrett brett;
	private boolean istSchwarz; 
	private Stein stein = null; // Nicht überall dürfen Steine sein (zB. auf weißen Feldern nicht)
	private int zeile;
	private int spalte;
	GamePlay gp;
	
	// Bilder, die momentan die Steine symbolisieren
	Image einfachSchwarz = new ImageIcon(getClass().getResource("EinfachSchwarz.png")).getImage(); 
	ImageIcon eS = new ImageIcon(einfachSchwarz.getScaledInstance(40,40,Image.SCALE_FAST ));
	Image einfachWeiß = new ImageIcon(getClass().getResource("EinfachWeiß.png")).getImage();
	ImageIcon eW = new ImageIcon(einfachWeiß.getScaledInstance(40,40,Image.SCALE_FAST ));
	Image dameSchwarz = new ImageIcon(getClass().getResource("DameSchwarz.png")).getImage(); 
	ImageIcon dS = new ImageIcon(dameSchwarz.getScaledInstance(40,40,Image.SCALE_FAST ));
	Image dameWeiß = new ImageIcon(getClass().getResource("DameWeiß.png")).getImage();
	ImageIcon dW = new ImageIcon(dameWeiß.getScaledInstance(40,40,Image.SCALE_FAST ));
	
	/**
	 * Ein Fald wird erzeugt
	 * @param brett ist das Spielbrett auf dem sich das Feld befindet
	 * @param istSchwarz legt fest, ob ein Feld schwarz oder weiß ist
	 * @param zeile Position des Feldes in y Richtung
	 * @param spalte Position des Feldes in x Richtung
	 */
	public Feld( GamePlay gp, Spielbrett brett, boolean istSchwarz, int zeile, int spalte){
		this.brett = brett;
		this.istSchwarz = istSchwarz;
		this.zeile = zeile;
		this.spalte = spalte;
		this.gp = gp;
	}
	
	/**
	 * Setter, der die Methode setStein(Stein stein, boolean init) mit dem boolean Wert false aufruft. Dies bedeutet, dass das Feld gerade nicht mehr dabei ist, sich zu initialisieren
	 * @param stein der auf das Feld gelegt wird
	 */
	public void setStein(Stein stein) {
		if(stein.getFeld().getZeile() == 0 || stein.getFeld().getZeile() == 7){ // Wenn man einen Einfachen STein aus diesen Reihen anklickt und gleich wieder ablegt, sollen sie nicht zu einer Dame werden. Daher wird der Wert init auf True gesetzt.
			setStein(stein, true);
		}
		else setStein(stein, false);
	}
	
	/**
	 * Setter, der einen Stein auf das entsprechende Feld legt. Außerdem wird sich gemerkt, dass der Zug zuende ist und je nach Farbe und Steintyp wird ein anderer Icon genutzt.
	 * @param stein der auf das Feld gesetzt werden soll
	 * @param init ist dieser Wert true, befindet sich das Spielfeld in der initialisierungsphase und es werden noch keine Dame-Steine auf die letzten Reihen gelegt
	 */
	public void setStein(Stein stein, boolean init) {
		if( !init && stein.getClass().getCanonicalName().equals("Model.Einfach") && (zeile == 0 || zeile == 7)){
			stein = new Dame (this, stein.getIstSchwarz());
		}
		this.stein = stein;
		gp.merkeZugEnde(stein);
		stein.setFeld(this);
		try{
			if(stein.getClass().getCanonicalName().equals("Model.Einfach") && stein.getIstSchwarz()){
			setIcon(eS);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Einfach") && !stein.getIstSchwarz()){
				setIcon(eW);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Dame") && stein.getIstSchwarz()){
				setIcon(dS);
			}
			else if (stein.getClass().getCanonicalName().equals("Model.Dame") && !stein.getIstSchwarz()){
				setIcon(dW);
			}
		} catch ( Exception e){};	
		
	}
	/**
	 * Getter für das Spielbrett, auf dem das Feld liegt
	 * @return Spielbrett
	 */
	public Spielbrett getBrett() {
		return brett;
	}
	
	/**
	 * Getter für den Stein, der auf dem Feld liegt.
	 * @return Stein
	 */
	public Stein getStein() {
		return stein;
	}
	
	/**
	 * Ein Stein wird gelöscht. Ein neuer Zug beginnt
	 */
	public void steinWeg(){
		stein = null;
		gp.merkeZugBeginn();
		setIcon(null);
	}
	
	/**
	 * Getter für die Zeile eines Feldes
	 * @return
	 */
	public int getZeile() {
		return zeile;
	}
	
	/**
	 * Getter für die Spalte eines Faldes
	 * @return
	 */
	public int getSpalte() {
		return spalte;
	}
	
}
