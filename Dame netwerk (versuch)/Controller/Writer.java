package Controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Writer extends Thread{
	
	private boolean isRunning = true;
	private ObjectOutputStream out; 
	private GamePlay game; 
	
	public Writer(GamePlay game) {
		this.game = game; 
	
	}
	
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				out.writeObject(game.getSf());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
