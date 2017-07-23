package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Stephi on 20.01.2017.
 */

public class NetworkService {

    private boolean isConnected;
    private Socket socket;
    private String ipAddr;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;
//    private BufferedReader in;
    public NetworkService (String ipAddr, int port) {
        this.port = port;
        this.ipAddr = ipAddr;
    }

    public NetworkService(int port) {
        this.port = port;
	}

    public ServerSocket createServerSocket () {
        try {
			return new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    	
    }
    
    public Socket createSocket (ServerSocket serverSocket){

        try {
//            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Warte auf Client connection");
            socket = serverSocket.accept();
//            socket = new Socket(InetAddress.getByName(ipAddr), port);
            System.out.println("Client ist verbunden");

            
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hallo Client, you are connected to " + socket.getLocalAddress());
            out.flush();

            in = new DataInputStream(socket.getInputStream());
            



        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return socket;
    }


    public Socket connectToSocket (){


        try {
            socket = new Socket(InetAddress.getByName(ipAddr), port);

            
            
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            //System.out.println(in.nextLine());

            out.writeUTF("Hallo Server, ich bin der Client mit der Addresse " + socket.getInetAddress());
            out.flush();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return socket;
    }



    public void closeConnection (){
        try {
            in.close();
            out.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    	/*
    public void sendMessage (String message){
    	try{
    		System.out.println("write msg " + message);
    	        out.println(message);
    	        out.flush();
    	        Thread.sleep(1000);
    	        out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
    	
    	
    	
    }
    
    */

    
    public void sendMessageToAllSockets (String message, ArrayList<Socket> sockets) {
    	
    	System.out.println("soll zu allen sockets : " + sockets.size() + ", nachricht: " + message + " schicken");
    	
    	for (Socket socket : sockets) {
			if(!socket.isClosed()) {

				try {
					System.out.println(socket.getInetAddress());
					
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());

					out.writeUTF(message);;
			        out.flush();

			        Thread.sleep(1000);
			        out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	}
    }

    public String receiveBoard (){
	    	try {
	    		String msg = in.readUTF();
	    		System.out.println(msg);
	    	    return msg;			
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return null;
    }

//    public InputStream getInStream () {
//        try {
//            return socket.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }




}

