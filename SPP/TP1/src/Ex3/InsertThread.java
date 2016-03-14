package Ex3;

import java.util.Random;

public class InsertThread extends Thread {
	
	public void run() {
		long range = 1234567L;
		
		for(int i = 0; i < 10000; ++i)
		{
		    synchronized (Exercice3.sharedList) {
				Random r = new Random();
				long number = (long)(r.nextDouble()*range);
				Exercice3.sharedList.add(number);
		    }
		}
	}
}
