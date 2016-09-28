package zetspa.core.exception;

/**
 * Created by huangshengtao on 2016-9-21.
 */
public class SysException extends RuntimeException {

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(String message) {
        super(message);
    }

    public static void throwException(String message, Throwable cause) {
        throw new SysException(message, cause);
    }
}
