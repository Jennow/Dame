package server;

import java.net.ServerSocket;
import java.util.ArrayList;

import Controller.GamePlay;
import GUI.Spielbrett;

public class ServerApp {
	
	
	public static Object game = null;
	public static String test = null; 
	
	
	public static void main(String[] args) {
		String ipAddr = "141.22.50.74";
		int port = 12345;
		
		NetworkService networkService = new NetworkService(ipAddr, port);

		ServerSocket serverSocket = networkService.createServerSocket();
		
		ArrayList<User> connections = new ArrayList<>();
		
		
		
		
		
		int max = 2;
		for (int i = 0; i < max; i++) {
			connections.add(new User(serverSocket, port));
		}	

	}
	
	public Object getGame() {
		return game;
	}
	
	public void setGame(Object game) {
		ServerApp.game = game;
	}
	
	public static String getTest() {
		return test;
	}
	
	public static void setTest(String test) {
		ServerApp.test = test;
	}
	
	
	
}
