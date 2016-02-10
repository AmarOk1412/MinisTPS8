/*
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
*/
import Ex2.MyRWLock;

public class ReadThread extends Thread {
	
	/*Q2
	private ReentrantLock _lock;
	private String _name;
	
	public ReadThread(String name, ReentrantLock lock)
	{
		this._name = name;
		this._lock = lock;
	}

	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			_lock.lock();
			long actualCounter = Exercice1.counter;
			_lock.unlock();
			if(i % 20000 == 0)
				System.out.println(_name + ":" + actualCounter);
		}
	}
	*/
	
	/*Q3
	private ReadWriteLock _lock;
	private String _name;
	
	public ReadThread(String name, ReadWriteLock lock)
	{
		this._name = name;
		this._lock = lock;
	}

	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			_lock.readLock().lock();
			long actualCounter = Exercice1.counter;
			_lock.readLock().unlock();
			if(i % 20000 == 0)
				System.out.println(_name + ":" + actualCounter);
		}
	}
	*/
	
	private MyRWLock _lock;
	private String _name;
	
	public ReadThread(String name, MyRWLock lock)
	{
		this._name = name;
		this._lock = lock;
	}

	public void run() {
		for(int i = 0; i < 100000; ++i)
		{
			try {
				_lock.lockRead();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long actualCounter = Exercice1.counter;
			try {
				_lock.unlockRead();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i % 20000 == 0)
				System.out.println(_name + ":" + actualCounter);
		}
	}
}
