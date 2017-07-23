package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import GUI.Spielbrett;

public class Reader extends Thread{

	private boolean isRunning = true;
	private ObjectInputStream in; 
	private GamePlay game; 
	
	public Reader(GamePlay game) {
		
		this.game = game; 
		
	}
	
	@Override
	public void run() {
		while (isRunning) {
			
			try {
				Spielbrett msg = (Spielbrett) in.readObject();
				game.setSf(msg);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
