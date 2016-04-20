import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;

public class ParallelEratosthenesSieve implements PrimeNumberFactory {
	public static ArrayList<Boolean> isPrime = null;
	static CyclicBarrier barrier = null;
	private int limitT = 0;
	
	public ParallelEratosthenesSieve(int limitThreads) {
		//Ici le +1 sert à pouvoir débloquer la barrière depuis le thread principal
		barrier = new CyclicBarrier(limitThreads+1);
		limitT = limitThreads;
	}
	
	public void setLimitThreads(int limitThreads) {
		barrier = new CyclicBarrier(limitThreads);
		limitT = limitThreads;
	}
	
	
	@Override
	public ArrayList<Integer> getPrimes(Integer limit) {
		//On récupère le tableau de booleen
		isPrime = new ArrayList<>();
		ArrayList<Integer> primes = new ArrayList<>();
		ArrayList<Boolean> isPrimeInternal = eratosthenesSieve(limit);
		//On le transforme en tableau d'entier pour le comparer lors des tests
		for(int i = 0; i < isPrimeInternal.size(); ++i)
		{
			if(isPrimeInternal.get(i))
			{
				primes.add(new Integer(2+i));
			}
		}
		return primes;
	}
	

	public ArrayList<Boolean> eratosthenesSieve(Integer n) {
		//AU cas où
        barrier = new CyclicBarrier(limitT + 1);
		//Initialisation du tableau de booléen entre 1 et n
        ArrayList<RunnableWorker> listWorker = new ArrayList<>();
        for (int i = 1; i < n; i++)
            isPrime.add(true);
        //On dispatch les threads
        int sqrtInt = (int) Math.sqrt(n+1);
        for (int i = 0; i < limitT; i++) {
        	int minRange = (i*sqrtInt/limitT)+1;
        	int maxRange = (i+1)*sqrtInt/limitT;
        	InfoLogger.getLogger().log(Level.FINE, "Thread {0} Dispatch between {1} and {2}", new Object[]{i, minRange, maxRange});//TODO
        	RunnableWorker runnableWorker = new RunnableWorker(minRange, maxRange, n+1);
            listWorker.add(runnableWorker);
            InfoLogger.log(Level.FINE, "Thread {0} run", i);
            runnableWorker.start();
        }
        
        //On Démarre les Threads
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

        InfoLogger.log(Level.FINE, "Threads is running");
        //On bloque le temps que les threads se finissent
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
        
        return isPrime;
	}

}
