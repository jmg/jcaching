package org.jcaching.server;

import java.io.IOException;

import utils.Logger;
import utils.ServerLogger;

public class RunServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ServerLogger.setLevel(Logger.PRODUCTION);
		
		CacheDeamon deamon = CacheDeamon.getInstance();
		deamon.serverForever();
	}

}
