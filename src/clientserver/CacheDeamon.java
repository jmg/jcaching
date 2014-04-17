package clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CacheDeamon {
	
	Integer defaultPort = 22122;
	Integer port;
	ServerSocket server;
	Socket client;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public CacheDeamon() {
		
		this.port = defaultPort;
	}
	
	public CacheDeamon(Integer port) {
		
		this.port = port;			   
	}
	
	public void serverForever() {
				
		try {
			System.out.print("Cache server running on: " + port.toString());
	    	server = new ServerSocket(port);
	    	client = server.accept();
	    	System.out.println("Connection received from " + client.getInetAddress().getHostName());
	    	client.getOutputStream();
	    	
	    	out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
            sendMessage("Connection successful");
		}
		catch (IOException e) {
			
		   System.out.println(e);
		}
		finally{
			
            try{
                in.close();
                out.close();
                client.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
	}
	
	void sendMessage(String msg) {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}
