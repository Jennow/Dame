package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import Controller.GamePlay;
import GUI.Spielbrett;

public class User {
	
	private NetworkService networkService;
	private ReaderService readerService;
	private WriterService writerService; 
	
	private Socket socket;
	
	public User(ServerSocket serverSocket, int port) {
		networkService = new NetworkService("localhost", port);
		socket = networkService.createSocket(serverSocket);
		
		System.out.println("Connection established: " + socket.getInetAddress().getHostAddress());
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); 
			
			DataOutputStream outTest = new DataOutputStream(socket.getOutputStream()); 
			DataInputStream inTest = new DataInputStream(socket.getInputStream()); 
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					readerService = new ReaderService(in, inTest);	
					writerService = new WriterService(out, outTest);
					readerService.start();
					writerService.start();
			
				}
			}).start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
