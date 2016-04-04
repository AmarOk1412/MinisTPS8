public interface SemaphoreInterface {

	/**
	 * Release Semaphore
	 */
	public void up();
	
	/**
	 * Acquire Semaphore
	 */
	public void down();
	
	/**
	 * Release all Threads
	 * @return
	 */
	public int  releaseAll();
}
