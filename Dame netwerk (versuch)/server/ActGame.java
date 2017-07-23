package server;

import Controller.GamePlay;
import GUI.Spielbrett;

public class ActGame {
	
	private Spielbrett actGame; 
	private int test; 
	
	public ActGame() {		
		
	}
	
	public void setActGame(Spielbrett actGame) {
		this.actGame = actGame;
	}
	
	public Spielbrett getActGame() {
		return actGame;
	}
	
	public int getTest() {
		return test;
	}
	
	public void setTest(int test) {
		this.test = test;
		System.out.println("ActGame test set: " + test);
	}

}
