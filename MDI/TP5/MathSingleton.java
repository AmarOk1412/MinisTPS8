public class MathSingleton  {	
	private static volatile MathSingleton instance = null;
	
	private MathSingleton() {
	}
	
	public static MathSingleton getInstance() {
		if(instance == null) {
			synchronized (Collector.class) {
				instance = new MathSingleton();
			}
		}
		return instance;
	}

}
