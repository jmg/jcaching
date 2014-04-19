package server;

import java.io.IOException;

import utils.Logger;
import utils.ServerLogger;

public class RunServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		int debug = 1;
		int production = 0;
		
		ServerLogger.getInstance().setLevel(debug);
		
		CacheDeamon deamon = CacheDeamon.getInstance();
		deamon.serverForever();
	}

}
