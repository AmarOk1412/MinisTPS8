package PingPong;

import java.util.concurrent.Exchanger;

public class Entry {

	public static Exchanger<String> exchanger = new Exchanger<String>();
	
	public static void main(String[] args) {
		   new Thread(new PingPongThread("ping"),"Alice").start();
		   new Thread(new PingPongThread("pong"),"Bob").start();
	}

}
