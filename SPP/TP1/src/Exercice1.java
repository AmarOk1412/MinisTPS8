import java.util.ArrayList;
import java.util.List;
/*
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
*/

import Ex2.MyRWLock;

public class Exercice1 {
	
	static public long counter = 0;

	public static void main(String[] args) {
		
		List<Thread> _myThreads = new ArrayList<Thread>();
		//Q2 : ReentrantLock lock = new ReentrantLock();
		//Q3 : ReadWriteLock lock = new ReentrantReadWriteLock();
		MyRWLock lock = new MyRWLock();
		
		for(int i = 0; i < 5; ++i)
		{
			_myThreads.add(new IncrementThread(lock));
		}
		for(int i = 0; i < 10; ++i)
		{
			_myThreads.add(new ReadThread("t_" + i, lock));
		}
		

		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 15; ++i)
		{
			_myThreads.get(i).start();
		}
		while(Thread.activeCount() > 1)
		{
			
		}
		System.out.println("Final counter: " + counter);
		long endTime = System.currentTimeMillis();
		long elTime = endTime - startTime; 
		System.out.println("Elapsed time: " + elTime);
	}

}
