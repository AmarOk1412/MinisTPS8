import java.net.URL;

public class Q1 {
	public URL toURL(String path) {
		URL url;
		try { url = new URL(path); }
		catch(Exception e) {
			url = null;
			Collector.getInstance().collect(e);
		}
		return url;
	}
}