import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ParallelEratosthenesSieve implements PrimeNumberFactory {

	public static ArrayList<Boolean> isPrime = null;
	private CyclicBarrier barrier = null;
	private int limitT = 0;
	
	public ParallelEratosthenesSieve(int limitThreads) {
		barrier = new CyclicBarrier(limitThreads+1);
		limitT = limitThreads;
	}
	
	public void setLimitThreads(int limitThreads) {
		barrier = new CyclicBarrier(limitThreads+1);
		limitT = limitThreads;
	}
	
	
	@Override
	public ArrayList<Integer> getPrimes(Integer limit) {
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
	

	public ArrayList<Boolean> eratosthenesSieve(Integer limit) {

		isPrime = new ArrayList<>();
		for(int i = 0; i <= limit-2; ++i)
		{
			isPrime.add(true);
		}
		ArrayList<SetFalseThread> listeThreads = new ArrayList<>();
		//Create threads
		for(int i = 0; i < limitT; ++i)
		{
			SetFalseThread thread = new SetFalseThread(barrier, limit);
			listeThreads.add(thread);
			thread.start();
		}
		int i = 2;
		int baseThread = Thread.activeCount();
		//Dispatch work
		while(Math.pow(i, 2) <= limit)
		{
			if(isPrime.get(i-2))
			{
				listeThreads.get(i%limitT).addWork(i);
			}
			i+=1;
		}
		//Stop Barrier
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Difference with CountDownLatch
		//Wait for finished
		//TODO await
		while(Thread.activeCount() > baseThread)
		{
			
		}
		return isPrime;
	}

}
