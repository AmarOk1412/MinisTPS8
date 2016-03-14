package Ex3;

import java.util.ArrayList;

public class Exercice3 {
	
	public static ArrayList<Long> sharedList = new ArrayList<Long>();

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		for(int nbThread = 3; nbThread <= 43; nbThread+=4)
		{
			long begin = System.currentTimeMillis();
			ObserverThread obs = new ObserverThread();
			obs.start();
			
			for(int i = 0; i < (nbThread-1)/2; ++i)
			{
				new InsertThread().start();
				new DeleteThread().start();
			}

			while(Thread.activeCount() > 2)
			{
			}
			
			obs.stop();

			long end = System.currentTimeMillis();
			System.out.println(nbThread + "," + (end-begin));
		}
	}

}
