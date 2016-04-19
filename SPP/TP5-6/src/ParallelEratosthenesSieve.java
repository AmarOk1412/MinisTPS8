import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ParallelEratosthenesSieve implements PrimeNumberFactory {

	public static ArrayList<Boolean> isPrime = null;
	static CyclicBarrier barrier = null;
	private int limitT = 0;
	
	public ParallelEratosthenesSieve(int limitThreads) {
		barrier = new CyclicBarrier(limitThreads+1);
		limitT = limitThreads;
	}
	
	public void setLimitThreads(int limitThreads) {
		barrier = new CyclicBarrier(limitThreads);
		limitT = limitThreads;
	}
	
	
	@Override
	public ArrayList<Integer> getPrimes(Integer limit) {
		isPrime = new ArrayList<>();
		ArrayList<Integer> primes = new ArrayList<>();
		ArrayList<Boolean> isPrimeInternal = eratosthenesSieve(limit);
		for(int i = 0; i < isPrimeInternal.size(); ++i)
		{
			if(isPrimeInternal.get(i))
			{
				primes.add(new Integer(2+i));
			}
		}
		System.out.println(primes.size());
		return primes;
	}
	

	public ArrayList<Boolean> eratosthenesSieve(Integer n) {
        barrier = new CyclicBarrier(limitT + 1);
        ArrayList<RunnableWorker> listWorker = new ArrayList<>();
        for (int i = 1; i < n; i++)
            isPrime.add(true);
        //On dispatch les threads
        int sqrtInt = (int) Math.sqrt(n+1);
        for (int i = 0; i < limitT; i++) {
        	int minRange = (i*sqrtInt/limitT)+1;
        	int maxRange = (i+1)*sqrtInt/limitT;
        	//System.out.println("Dispatch entre " + minRange + "-" + maxRange);
            RunnableWorker runnableWorker = new RunnableWorker(minRange, maxRange, n+1);
            listWorker.add(runnableWorker);
            //System.out.println("create a worker" + i);
            runnableWorker.start();
        }
        
        //On Démarre les Threads
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
        
        //System.out.println("Running");
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
        
        return isPrime;
	}

}
