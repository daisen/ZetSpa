package zetspa.core;

        import zetspa.core.interfaces.ILogon;

/**
 * Created by huangshengtao on 2016-9-22.
*/
public class Global {
    static ThreadLocal<ILogon> logonThreadLocal = new ThreadLocal<>();

    public static ILogon getLogon() {
        return logonThreadLocal.get();
    }

    public static void setLogon(ILogon logon) {
        logonThreadLocal.set(logon);
    }
}
