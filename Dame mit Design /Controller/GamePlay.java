package Controller;

import GUI.EndScreen;
import GUI.LoadingNetwork;
import GUI.MainView;
import GUI.SpielScreen;
import GUI.Spielbrett;
import GUI.StartScreen;
import Model.Stein;

public class GamePlay {
	
	private StartScreen startScreen;
	private LoadingNetwork loadingScreen; 
	private EndScreen endScreen;
	private SpielScreen board; 
	private MainView mainView; 

	

	public final int amZugWEISS = 1;
	public final int amZugSCHWARZ = 2;
	/**
	 * Enth�lt einen int Wert, der festlegt, welcher Spieler aktuell an der Reihe ist.
	 */
	public int amZug = amZugSCHWARZ;
	private boolean istZugBeginn = true; // CONTROLLER??
	Spielbrett sf;
	public Spielregeln regeln;
	Stein st;
	int weiter =5;

	/**
	 * Ein Spielbrett wird erzeugt. Darin ist aktuell das JPanel, welches die Komponenten enth�lt.
	 */
	public GamePlay(){
		
		regeln = new Spielregeln(this);
		//sf = new Spielbrett(this);
		
		startScreen = new StartScreen(); 
		endScreen = new EndScreen();

		mainView = new MainView(startScreen);

		/*
		
		
		String ipAddr = "localhost";
		int port = 12345;
		NetworkService networkService = new NetworkService(ipAddr, port);
		Socket socket = networkService.connectToSocket();
		System.out.println("Connection zum Server hergestellt");
		
		try {
			inTest = new DataInputStream(socket.getInputStream());
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			outTest = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 

		
		
		*/
		
		addListener();
		

		/*
		startScreen.getStartOn().addActionListener(e -> {
			System.out.println("Online");
			
			loadingScreen = new LoadingNetwork(); 
			
			mainView.changeScreen(loadingScreen);
			
		});
		
		
		startScreen.getStartOff().addActionListener(e -> {
			System.out.println("Offline");
			sf = new Spielbrett(this);
			board = new SpielScreen(sf); 


			mainView.changeScreen(board);
			
			board.getAufgeben().addActionListener(l -> {
				System.out.println("aufgeben");
				mainView.changeScreen(endScreen);;
			});
			
		});

		
		endScreen.getRestart().addActionListener(e ->{
			System.out.println("Restart");
			sf = new Spielbrett(this); 
			board = new SpielScreen(sf);
			mainView.changeScreen(board);
		});
		
		endScreen.getMenu().addActionListener(e ->{
			System.out.println("back to menu");
			//sf = new Spielbrett(this);	
			mainView.changeScreen(startScreen);
		});
		
		*/
		
		
	}
	
	/**
	 * Getter f�r den zugBeginn. Ist dieser boolean true, ist gerade der Beginn des Zuges. Ist sie False, dann nicht. 
	 * @return
	 * @see com.GUI.Feld.steinWeg() 
	 */
	public boolean getZugBeginn(){
		return istZugBeginn;
	}
	
	/**
	 * Nach Schritt 1 des Zuges, also dem Ausw�hen des zu verschiebenden Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass jetzt der 
	 * Zugbeginn vorbei ist. 
	 */
	public void merkeZugBeginn(){ 
		istZugBeginn = false;
	}
	
	/**
	 * Nach Schritt 2 des Zuges, also dem Ausw�hen der gew�nschten neuen Posotion des Steins,
	 * wird diese Methode aufgerufen, um sich zu 'merken', dass nun der Zug zuende ist und beim
	 * n�chsten Klick der n�chste Spieler mit seinem Zug beginnt.
	 * @param stein 
	 */
	public void merkeZugEnde(Stein stein){ 
		if(stein.getIstSchwarz()){
			amZug = amZugWEISS; // Spieler nach Zug Ende �ndern
		}
		else amZug = amZugSCHWARZ;
		istZugBeginn = true;
	}
	
	
	
	
	/**
	 * Diese Methode stellt fest, ob ein Spieler keine Steine mehr, und somit verloren hat. Die Integers k�nnen sp�ter auch durch Spieler ersetzt werden.
	 * @return int ist bei Spieler Wei� 0, bei Schwarz 1 und wenn noch niemand gewonnen hat 3
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
	
	
	public Spielbrett getSf() {
		return sf;
	}
	
	public void setSf(Spielbrett sf) {
		this.sf = sf;
	}
	
	private void addListener(){
		startScreen.getStartOn().addActionListener(e -> {
			System.out.println("Online");
			
			loadingScreen = new LoadingNetwork(); 
			
			mainView.changeScreen(loadingScreen);
			
			addListener();
			
		});
		
		
		startScreen.getStartOff().addActionListener(e -> {
			System.out.println("Offline");
			sf = new Spielbrett(this);
			board = new SpielScreen(sf); 


			mainView.changeScreen(board);
			
			board.getAufgeben().addActionListener(l -> {
				System.out.println("aufgeben");
				mainView.changeScreen(endScreen);;
			});
			
		});

		
		endScreen.getRestart().addActionListener(e ->{
			System.out.println("Restart");
			sf = new Spielbrett(this); 
			board = new SpielScreen(sf);
			mainView.changeScreen(board);
			
		});
		
		endScreen.getMenu().addActionListener(e ->{
			System.out.println("back to menu");
			//sf = new Spielbrett(this);	
			mainView.changeScreen(startScreen);

		});
	}
	
	
	/**
	 * Main Methode, die Ein neues Spielbrett erzeugt.
	 * @param args
	 */
	
	public static void main(String[] args) {
		new GamePlay();
	}
	
	
}
