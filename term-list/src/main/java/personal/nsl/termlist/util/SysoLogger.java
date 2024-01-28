package personal.nsl.termlist.util;

import java.time.LocalDateTime;

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
        LocalDateTime currentTime = LocalDateTime.now();
        String currentThreadName = Thread.currentThread().getName();
        String completeMessage = String.format("[%s - %s]\n\t%s",
                                       currentTime,
                                       currentThreadName,
                                       msg);
        print(completeMessage);
    }
    
    public void log() {
        this.log("");
    }
    
    public void log(Object msg) {
        this.log(msg.toString());
    }
    
    public void log(boolean msg) {
        this.log(String.valueOf(msg));
    }
    
    public void log(int msg) {
        this.log(String.valueOf(msg));
    }
    
    public void log(char msg) {
        this.log(String.valueOf(msg));
    }
    
    public void log(double msg) {
        this.log(String.valueOf(msg));
    }

    @Override
    public void log(String msg, Class<?> classInfo) {
        LocalDateTime currentTime = LocalDateTime.now();
        String currentThreadName = Thread.currentThread().getName();
        String completeMessage = String.format("[%s - %s - %s]\n\t%s",
                                       currentTime,
                                       currentThreadName,
                                       classInfo.getName(),
                                       msg);
        print(completeMessage);
    }

    @Override
    public void log(Class<?> classInfo) {
        this.log("", classInfo);
    }

    @Override
    public void log(Object msg, Class<?> classInfo) {
        this.log(msg.toString(), classInfo);
    }

    @Override
    public void log(boolean msg, Class<?> classInfo) {
        this.log(String.valueOf(msg), classInfo);
    }

    @Override
    public void log(int msg, Class<?> classInfo) {
        this.log(String.valueOf(msg), classInfo);
    }

    @Override
    public void log(char msg, Class<?> classInfo) {
        this.log(String.valueOf(msg), classInfo);
    }

    @Override
    public void log(double msg, Class<?> classInfo) {
        this.log(String.valueOf(msg), classInfo);
    }
}
