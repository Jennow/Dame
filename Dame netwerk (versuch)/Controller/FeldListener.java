package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.Feld;
import GUI.Spielbrett;
import Model.Stein;

public class FeldListener implements ActionListener{


	public final int amZugWEISS = 1;
	public final int amZugSCHWARZ = 2;
	int amZug = amZugWEISS;
	Stein st = null;
	Feld clicked;
	int weiter =5;
	
	Spielbrett sf;
	public Spielregeln regeln;
	GamePlay gp;
	
	public FeldListener(GamePlay gp){
		this.gp = gp;
		this.sf = gp.sf;
		//regeln = gp.regeln;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		clicked = (Feld) event.getSource();
		System.out.println("" + clicked.getZeile() + " x "+ clicked.getSpalte());
		int amZugMem = amZug; // merken, wer am Zug ist
		
		if(gp.getZugBeginn()){ // Stein "In die Hand nehmen"
			if ((st = clicked.getStein()) != null){
				System.out.println("stein: " + st.toString());
				if(st.getIstSchwarz() && (amZug == amZugSCHWARZ) ){
					clicked.steinWeg();
				}
				else if(!st.getIstSchwarz() && (amZug == amZugWEISS) ){
					clicked.steinWeg();
				}
			}
		} 
		else {
			if(st.istOk(clicked) && regeln.istOk(st, clicked)){
				clicked.setStein(st); // Stein absetzen
				if(regeln.canJump(clicked.getStein(), sf.felder) && clicked.getClass().getCanonicalName().equals("Model.Dame")){
					amZug = amZug;
				}
				else if(amZug == amZugSCHWARZ ){
					amZug = amZugWEISS;
				}
				else amZug = amZugSCHWARZ;
				if(st.getClass().getCanonicalName().equals("Model.Dame")){
					
				}
			}
			else {
				st.getFeld().setStein(st); // Stein auf Ausgangsfeld zur�cksetzen 
				amZug = amZugMem; // Zug ist noch nicht zuende.
				// JDialog mit Warnung ausgeben :"Dieser Zug ist nicht m�glich"
			}
			if (gp.hasWon()==0){
				weiter = JOptionPane.showConfirmDialog(null, " Spieler Weiss hat gewonnen! juhiuuuu");
			}
			else if(gp.hasWon() == 1){
				weiter = JOptionPane.showConfirmDialog(null, "Spieler Schwarz hat gewonnen! juhiuuuu");
			}
			if (weiter == 0){
				new GamePlay();
			}	
		}
	}
	
}