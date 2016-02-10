package Ex3;

import java.util.Random;

public class DeleteThread extends Thread {
	
	public void run() {
		Random r = new Random();
		int randomNum = r.nextInt((Exercice3.sharedList.size()) + 1);
		Exercice3.sharedList.remove(randomNum);
	}
}
