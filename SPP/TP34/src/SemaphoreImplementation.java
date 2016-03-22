
public class SemaphoreImplementation implements SemaphoreInterface {

	int _blocked = 0;
	@Override
	public synchronized void up()
	{
		if(_blocked != Integer.MAX_VALUE)
			++_blocked;
		if(_blocked <= 0)//Deblock if one is blocked
			notify();
	}

	@Override
	public synchronized void down()
	{
		if(_blocked != Integer.MIN_VALUE)
			--_blocked;       //Block one
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
