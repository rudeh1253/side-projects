package personal.nsl.termlist.util;

public interface NLogger {

    public static NLogger getLogger() {
        return SysoLogger.getInstance();
    }

    public void log(String msg);

    public void log();

    public void log(Object msg);

    public void log(boolean msg);

    public void log(int msg);

    public void log(char msg);

    public void log(double msg);

    public void log(String msg, Class<?> classInfo);

    public void log(Class<?> classInfo);

    public void log(Object msg, Class<?> classInfo);

    public void log(boolean msg, Class<?> classInfo);

    public void log(int msg, Class<?> classInfo);

    public void log(char msg, Class<?> classInfo);

    public void log(double msg, Class<?> classInfo);
}
