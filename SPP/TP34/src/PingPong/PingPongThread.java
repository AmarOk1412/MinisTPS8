package PingPong;

import java.util.Random;

public class PingPongThread implements Runnable {

	   private String current;
	   private int it = 3;
	   private Random rand = new Random();
	   
	   public PingPongThread(String ref) {
		   current = ref;
	}
	   
	   public void run() {
	       try {
	    	   for(int i = 0; i < it; ++i)
	    	   {
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " ready to exchange");
	    		   current = Entry.exchanger.exchange(current);
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " has " + current);
	    		   int randomNum = rand.nextInt(5001);
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName()+" is now going to sleep: " + randomNum + "ms");
	    		   Thread.sleep(randomNum);
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " exchange complete");
	    	   }
	       } catch (InterruptedException ex) { 
	    	   System.out.println(ex);
	       }
	   }
}
