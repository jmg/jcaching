package clientserver;

public class RunServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CacheDeamon deamon = new CacheDeamon();
		deamon.serverForever();
	}

}
