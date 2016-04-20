import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Level;

public class RunnableWorker extends Thread implements Runnable {
	
	private int _min;
	private int _max;
	private int _limit;

	//On lui donne l'intervalle
    public RunnableWorker() {
    }
    
    public void setParameters(int minRange, int maxRange, int limit) {
    	_min = minRange;
    	_max = maxRange;
    	_limit = limit;
    }

    @Override
    public void run() {
    	//Première barrière pour attendre le démarrage des autres threads
	    try {
	    	InfoLogger.log(Level.FINE, "Thread {0} wait others", this.getName());
	        ParallelEratosthenesSieve.barrier.await();
	        InfoLogger.log(Level.FINE, "Thread {0} is Running", this.getName());
	    } catch (InterruptedException ex) {
	        return;
	    } catch (BrokenBarrierException ex) {
	        return;
	    }   

	    //Réalisation du crible pour l'intervalle donné
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
	    
        //Seconde barrière pour attendre les autres threads
	    try {
	    	InfoLogger.log(Level.FINE, "Thread {0} wait others", this.getName());
	        ParallelEratosthenesSieve.barrier.await();
	        InfoLogger.log(Level.FINE, "Thread {0} is finish", this.getName());
	    } catch (InterruptedException ex) {
	        return;
	    } catch (BrokenBarrierException ex) {
	        return;
	    }
    }
}
