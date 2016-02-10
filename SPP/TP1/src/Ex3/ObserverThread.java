package Ex3;

public class ObserverThread extends Thread {
	
	public void run() {
		
		while(true)
		{
			System.out.println(Exercice3.sharedList.size());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
