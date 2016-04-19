import java.util.concurrent.BrokenBarrierException;

/**
 * Created by Thomas on 11/04/2016.
 */

public class RunnableWorker extends Thread implements Runnable {
	
	private int _min;
	private int _max;
	private int _limit;

    public RunnableWorker(int minRange, int maxRange, int limit) {
    	_min = minRange;
    	_max = maxRange;
    	_limit = limit;
    }

    @Override
    public void run() {
	    try {
	        //System.out.println("Thread " + this.getName() + " calling await()");
	        ParallelEratosthenesSieve.barrier.await();
	        //System.out.println("Thread " + this.getName() + " stop await()");
	    } catch (InterruptedException ex) {
	        return;
	    } catch (BrokenBarrierException ex) {
	        return;
	    }   

        //System.out.println("Working");
        for(int i = _min; i <= _max; ++i)
        {
	        if(i > 1 && i < _limit && ParallelEratosthenesSieve.isPrime.get(i-2))
			{
				int iPow2 = (int) Math.pow(i, 2);
				int step = (i==2) ? i : 2*i; //To speed up the inner loop
				for(int j = iPow2; j < _limit; j += step)
				{
					 ParallelEratosthenesSieve.isPrime.set(j-2, false);
				}
			}
        }
	    
	    try {
	        //System.out.println("Thread " + this.getName() + " calling await()");
	        ParallelEratosthenesSieve.barrier.await();
	        //System.out.println("Thread " + this.getName() + " stop await()");
	    } catch (InterruptedException ex) {
	        return;
	    } catch (BrokenBarrierException ex) {
	        return;
	    }
    }
}
