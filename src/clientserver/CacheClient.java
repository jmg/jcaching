package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CacheClient {
	
	Integer defaultPort = 22122;
	String host = "localhost";
	Integer port;
	Socket client;
	DataOutputStream os;
	DataInputStream is;
	
	public CacheClient() {
		
		this.port = defaultPort;
	}
	
	public CacheClient(int port) {
		
		this.port = port;
	}
	
	public String getData(String key) {
		
		try {
            client = new Socket(host, port);
            os = new DataOutputStream(client.getOutputStream());
            is = new DataInputStream(client.getInputStream());
            
            os.writeBytes("GET\n");
            os.writeBytes(key);
            os.writeBytes("END\n");
            
            String responseLine;
            String response = "";
			while ((responseLine = is.readUTF()) != null) {
                response += responseLine;                
            }
						
            return response;
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:" + host);
        }
		finally {
			try{
                os.close();
                is.close();
                client.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
		}
		
		return null;
	}
	
	public boolean setData(String key, String data) {
		return true;
	}

	public boolean deleteData(String key) { 
		return true;
	}
}
