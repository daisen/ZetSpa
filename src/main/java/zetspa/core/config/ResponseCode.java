package zetspa.core.config;

/**
 * Created by huangshengtao on 2016-12-12.
 */
public enum  ResponseCode {
    NO_BEAN(1001),ERR_BEAN(1002);


    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    private String getString() {
        return null;
    }

    @Override
    public String toString() {
        if (getString() == null) {
            return Integer.toString(this.code);
        }

        return getString();
    }
}
