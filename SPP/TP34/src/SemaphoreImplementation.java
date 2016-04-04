
public class SemaphoreImplementation implements SemaphoreInterface {

	//Count blocked Threads
	int _blocked = 0;
	
	@Override
	public synchronized void up()
	{
		//Avoid to deblock blocked threads if we release too much
		if(_blocked != Integer.MAX_VALUE)
			++_blocked;
		if(_blocked <= 0)//Deblock one Thread is blocked
			notify();
	}

	@Override
	public synchronized void down()
	{
		//Avoid to block Threads if we acquire too much
		if(_blocked != Integer.MIN_VALUE)
			--_blocked;
		
		if (_blocked < 0) //wait if not available
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public synchronized int releaseAll() {
		//Count blocked threads
		int blocked = _blocked > 0 ? 0 : -_blocked;
		//Deblock all
		if(_blocked < 0)
		{
			notifyAll();
			_blocked = 0; 
		}
		return blocked;
	}

}
