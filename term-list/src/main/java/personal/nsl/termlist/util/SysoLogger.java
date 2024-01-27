package personal.nsl.termlist.util;

public class SysoLogger implements NLogger {
    private static final SysoLogger log = new SysoLogger();
    
    private SysoLogger() {
    }
    
    public static SysoLogger getInstance() {
        return SysoLogger.log;
    }
    
    private void print(String msg) {
        System.out.println(msg);
    }
    
    public void log(String msg) {
        print(msg);
    }
    
    public void log(Object msg) {
        print(msg.toString());
    }
}
