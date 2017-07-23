package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Controller.GamePlay;

public class WriterService extends Thread{
	
	private boolean isRunning = true;
	private ObjectOutputStream out; 
	private DataOutputStream outTest; 
	private GamePlay game;

	public WriterService(ObjectOutputStream out, DataOutputStream outTest) {
		this.out = out;
		this.outTest = outTest; 
	}
	
	@Override
	public void run() {
		
		System.out.println("WriterService auf Server gestartet");

		
		while(isRunning){
			try {
				out.writeObject(ServerApp.game);
				outTest.writeUTF(ServerApp.test);
				System.out.println("Neues GameField in writeByte in WriterService");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
