package Ex3;

import java.util.Random;

public class DeleteThread extends Thread {
	
	public void run() {
		for(int i = 0; i < 10000; ++i)
		{
		    synchronized (Exercice3.sharedList) {
				Random r = new Random();
				int range = Exercice3.sharedList.size();
				if(range > 0)
				{
					int randomNum = r.nextInt(range);
					Exercice3.sharedList.remove(randomNum);
				}
		    }
		}
	}
}
