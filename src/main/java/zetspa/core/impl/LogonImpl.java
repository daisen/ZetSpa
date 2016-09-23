package zetspa.core.impl;

import zetspa.core.interfaces.ILocale;
import zetspa.core.interfaces.ILogon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by huangshengtao on 2016-9-22.
 */
public class LogonImpl implements ILogon {

    private Map<String, String> logon = new HashMap<>();
    private ILocale locale;

    @Override
    public void setToken(String token) {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String get(String name) {
        if (logon.containsKey(name)) {
            return logon.get(name);
        }
        return null;
    }

    @Override
    public void set(String name, String value) {
        logon.put(name, value);
    }

    @Override
    public Set<String> getProperties() {
        return logon.keySet();
    }

    @Override
    public ILocale getLocale() {
        if (locale == null) {
            synchronized (locale) {
                locale = new LocaleImpl();
            }
        }
        return locale;
    }
}
