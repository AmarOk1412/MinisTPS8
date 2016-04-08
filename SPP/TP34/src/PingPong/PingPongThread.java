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
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " has " + current);
    		   
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " ready to exchange");
	    		   //Give the ref to the exchanger and get the current ref in the exchanger
	    		   current = Entry.exchanger.exchange(current);
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " has " + current);
	    		   //Sleep <= 5000ms
	    		   //int randomNum = rand.nextInt(5001);
	    		   //System.out.println("Iteration " + i + ": " + Thread.currentThread().getName()+" is now going to sleep: " + randomNum + "ms");
	    		   //Thread.sleep(randomNum);
	    		   //Finish Exchange
	    		   System.out.println("Iteration " + i + ": " + Thread.currentThread().getName() + " exchange complete");
	    	   }
	       } catch (InterruptedException ex) { 
	    	   System.out.println(ex);
	       }
	   }
}
