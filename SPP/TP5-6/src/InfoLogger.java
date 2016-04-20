import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoLogger {
	
	private static Logger logger = null;
	
	private InfoLogger()
	{
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.FINER);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.FINER);
        logger.addHandler(handler);
	}
	
	public static Logger getLogger(){
	    if(logger == null){
	        new InfoLogger();
	    }
	    return logger;
	}
	public static void log(Level level, String msg){
	    getLogger().log(level, msg);
	}
	public static void log(Level level, String msg, Object o){
	    getLogger().log(level, msg, o);
	}

}
