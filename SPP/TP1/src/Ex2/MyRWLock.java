package Ex2;

import java.util.concurrent.Semaphore;
//import java.util.concurrent.locks.ReentrantLock;

public class MyRWLock {

	/*private int readers = 0;
	private int writers = 0;
	private int writeRequests = 0;*/
	
	private Semaphore semRead;
	private Semaphore semWrite;
	
	public MyRWLock() {
		semRead = new Semaphore(1);
		semWrite = new Semaphore(1);
	}

	public synchronized void lockRead() throws InterruptedException {
		/*if(writeRequests > 0 || writers > 0) wait();
		readers++;*/
		semRead.release();
	}
	
	public synchronized void unlockRead() throws InterruptedException {
		/*readers--;
		if(readers == 0) notify();*/
		semRead.acquire();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		/*writeRequests++;
		if(readers > 0) wait();
		writeRequests--;
		writers++;*/
		semWrite.release();
	}
	
	public synchronized void unlockWrite() throws InterruptedException {
		/*writers--;
		if(writers == 0) notify();*/
		semWrite.acquire();
	}
}
