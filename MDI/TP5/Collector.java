import java.util.ArrayList;
import java.util.List;

public class Collector {
	
	private static volatile Collector instance = null;
	private List<Exception> exceptions;
	
	private Collector() {
		exceptions = new ArrayList<>();
	}
	
	public static Collector getInstance() {
		if(instance == null) {
			synchronized (Collector.class) {
				instance = new Collector();
			}
		}
		return instance;
	}
	
	public void collect(Exception e)
	{
		exceptions.add(e);
	}

}
