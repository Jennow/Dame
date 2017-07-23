package server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import Controller.GamePlay;
import GUI.Spielbrett;

public class ReaderService extends Thread{
	
	private boolean isRunning = true;
	private ObjectInputStream in; 
	private DataInputStream inTest; 
	

	public ReaderService(ObjectInputStream in, DataInputStream inTest) {
		this.in = in;
		this.inTest = inTest; 
	}
	
	@Override
	public void run() {
		
		System.out.println("ReaderService auf Server gestartet");
		
		while(isRunning){
			Object gameField = null;
			String test = null; 
			try {
				try {
					gameField = in.readObject();
					test = inTest.readUTF(); 
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				ServerApp.game = gameField; 
				ServerApp.test = test; 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Neues Testnachricht erhalten: " + test);
		}
	}
	
	
}
