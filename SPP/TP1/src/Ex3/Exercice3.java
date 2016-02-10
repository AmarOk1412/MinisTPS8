package Ex3;

import java.util.ArrayList;

public class Exercice3 {
	
	public static ArrayList<Long> sharedList = new ArrayList<Long>();

	public static void main(String[] args) {
		for(int i = 0; i < 10; ++i)
		{
			new InsertThread().start();
			new DeleteThread().start();
		}
		
		ObserverThread obs = new ObserverThread();
		obs.start();

		while(Thread.activeCount() > 2)
		{
			
		}
		obs.stop(null);

	}

}
