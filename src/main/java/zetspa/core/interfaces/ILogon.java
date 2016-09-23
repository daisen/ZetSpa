/**
 * 登录信息
 *
 * @author : hst
 * @Version :
 * @Date :
 */
package zetspa.core.interfaces;

import java.util.Set;

public interface ILogon {

    void setToken(String token);

    String getToken();

    String get(String name);

    void set(String name, String value);

    Set<String> getProperties();

    ILocale getLocale();
}
