package Controller;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.Feld;
import GUI.SpielFrame;
import GUI.Spielbrett;
import Model.Einfach;
import Model.Stein;

public class GamePlay {

	public final int amZugWEISS = 1;
	public final int amZugSCHWARZ = 2;
	/**
	 * Enthï¿½lt einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	public int amZug = amZugSCHWARZ;
	private boolean istZugBeginn = true; // CONTROLLER??
	Spielbrett sf;
	public Spielregeln regeln;
	Stein st;
	int weiter =5;

	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enthï¿½lt.
	 */
	public GamePlay(){
		sf = new Spielbrett(this);
		regeln = new Spielregeln(this);
	}
	
	/**
	 * Getter fï¿½r den zugBeginn. Ist dieser boolean true, ist gerade der Beginn des Zuges. Ist sie False, dann nicht. 
	 * @return
	 * @see com.GUI.Feld.steinWeg() 
	 */
	public boolean getZugBeginn(){
		return istZugBeginn;
	}
	
	/**
	 * Nach Schritt 1 des Zuges, also dem Auswï¿½hen des zu verschiebenden Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass jetzt der 
	 * Zugbeginn vorbei ist. 
	 */
	public void merkeZugBeginn(){ 
		istZugBeginn = false;
	}
	
	/**
	 * Nach Schritt 2 des Zuges, also dem Auswï¿½hen der gewï¿½nschten neuen Posotion des Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass nun der Zug zuende ist und beim
	 * nï¿½chsten Klick der nï¿½chste Spieler mit seinem Zug beginnt.
	 * @param stein 
	 */
	public void merkeZugEnde(Stein stein){ 
		if(stein.getIstSchwarz()){
			amZug = amZugWEISS; // Spieler nach Zug Ende ï¿½ndern
		}
		else amZug = amZugSCHWARZ;
		istZugBeginn = true;
	}
	
	
	
	
	/**
	 * Diese Methode stellt fest, ob ein Spieler keine Steine mehr, und somit verloren hat. Die Integers können später auch durch Spieler ersetzt werden.
	 * @return int ist bei Spieler Weiß 0, bei Schwarz 1 und wenn noch niemand gewonnen hat 3
	 */
	public int hasWon(){
		int playerWeiss = 0;
		int playerSchwarz = 1;
		int noOne = 2;
		boolean weissLeer = true;
		boolean schwarzLeer = true;
		
		for(int z =0; z < sf.felder.length; z++){
			for(int s = 0; s< sf.felder[z].length; s++){
				if(sf.felder[z][s].getStein() != null && sf.felder[z][s].getStein().getIstSchwarz()){
					 schwarzLeer = false;
				}
				else weissLeer = false;
			}
		}
		if(schwarzLeer){
			return playerWeiss;
		}
		if(weissLeer){
			return playerSchwarz;
		}
		return noOne;
	}
	
	
	
	/**
	 * Main Methode, die Ein neues Spielbrett erzeugt.
	 * @param args
	 */
	public static void main(String[] args) {
		GamePlay gp = new GamePlay();
	}
}
