package PingPong;

import java.util.concurrent.Exchanger;

public class Entry {

	//Share exchanger
	public static Exchanger<String> exchanger = new Exchanger<String>();
	
	public static void main(String[] args) {
		   //Give ping to Alice
		   new Thread(new PingPongThread("ping"),"Alice").start();
		   //Give pong to Bob
		   new Thread(new PingPongThread("pong"),"Bob").start();
	}

}
