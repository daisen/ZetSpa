package zetspa.core.impl;

import zetspa.core.interfaces.ILocale;

/**
 * Created by huangshengtao on 2016-9-22.
 */
public class LocaleImpl implements ILocale {

    @Override
    public String getString(String key) {
        return key;
    }

    @Override
    public String getString(String key, String langId) {
        return key;
    }

    @Override
    public String getString(String key, String langId, String defValue) {
        return key;
    }

    @Override
    public String getDateTimeFormat() {
        return "yyyy-MM-dd HH:mm:ss";
    }

    @Override
    public String getDateFormat() {
        return "yyyy-MM-dd";
    }
}
