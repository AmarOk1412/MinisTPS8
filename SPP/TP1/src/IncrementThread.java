/*
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
*/

import Ex2.MyRWLock;

public class IncrementThread extends Thread {
	
	/*2 private ReentrantLock _lock;
	
	public IncrementThread(ReentrantLock lock)
	{
		_lock = lock;
	}
	
	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			_lock.lock();
			Exercice1.counter++;
			_lock.unlock();
		}
	}*/
	
	/*Q3
	private ReadWriteLock _lock;
	public IncrementThread(ReadWriteLock lock)
	{
		_lock = lock;
	}

	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			_lock.writeLock().lock();;
			Exercice1.counter++;
			_lock.writeLock().unlock();
		}
	}
	*/
	
	private MyRWLock _lock;
	public IncrementThread(MyRWLock lock)
	{
		_lock = lock;
	}

	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			try {
				_lock.lockWrite();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Exercice1.counter++;
			try {
				_lock.unlockWrite();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
