package personal.nsl.termlist.util;

public interface NLogger {
    
    public static NLogger getLogger() {
        return SysoLogger.getInstance();
    }
    
    public void log(String msg);
    
    public void log(Object msg);
}
