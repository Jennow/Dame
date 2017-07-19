package com.example.jenif.dameapp.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jenif.dameapp.SpielActivity;


import static com.example.jenif.dameapp.SpielActivity.buttons;

/**
 * Das Spielbrett erbt von JFrame (Sp�ter �ndern in JPanel, um Graphics zu verwenden?). Es enth�lt 10x10 Felder, die in einem Schachbrettmuster angeordnet sind.
 * Momentan k�mmert es sich um den Spielverlauf, das soll aber sp�ter im Controller geschehen.
 * @author jenif
 *
 */
public class Spielbrett implements View.OnClickListener {

	/**
	 * Enth�lt einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	private int amZug;
	private boolean istZugBeginn = true; // CONTROLLER??
	public Feld [][] felder = new Feld [8][8];
	private Stein st = null;
	public GamePlay gp;
	int weiter =5;
	 int z;
	 int s;
	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enth�lt.
	 */
	public Spielbrett(final GamePlay gp){
		
		this.gp = gp;
		amZug = gp.amZugWEISS;

		boolean schwarz = false;
		for( z = 0; z<felder.length ; z++){ //zeile
			for( s =0; s<felder[z].length ; s++){ // spalte
				ImageButton btn = buttons[z][s];
				Feld feld = new Feld( gp, this,btn,  schwarz, z, s);



					//if ((gp.hasWon()==0 || gp.hasWon()==1) && gp.getZugBeginn()){}

				felder[z][s] =  feld;// gerade erstelltes Feld an der jeweiligen Position einsetzen
			}
		}
	}

	@Override
	public void onClick(View view) {
		int zeile = ; // Hier muss irgendwie die Position des geklickten Buttons im GridLayout bzw im buttons Array ermittelt werden...
		int spalte = ;
		Feld clicked = felder[zeile][spalte];
		int amZugMem = amZug; // merken, wer am Zug ist
		if (gp.getZugBeginn()) { // Stein "In die Hand nehmen"

			if ((st = clicked.getStein()) != null) {
				if (st.getIstSchwarz() && (amZug == gp.amZugSCHWARZ)) {
					clicked.steinWeg(buttons[zeile][spalte]);
				} else if (!st.getIstSchwarz() && (amZug == gp.amZugWEISS)) {
					clicked.steinWeg(buttons[zeile][spalte]);
				}
			}
		} else {
			if (st.istOk(clicked) && gp.regeln.istOk(st, clicked)) {
				clicked.setStein(st, buttons[zeile][spalte]); // Stein absetzen
				if (gp.regeln.canJump(clicked.getStein(), felder)) {
					amZug = amZug;
				} else if (amZug == gp.amZugSCHWARZ) {
					amZug = gp.amZugWEISS;
				} else amZug = gp.amZugSCHWARZ;
				if (st.getClass().getCanonicalName().equals("Model.Dame")) {

				}
			} else {
				st.getFeld().setStein(st, buttons[zeile][spalte]); // Stein auf Ausgangsfeld zur�cksetzen
				amZug = amZugMem; // Zug ist noch nicht zuende.
				// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht m�glich"
			}
		}
	}
}


