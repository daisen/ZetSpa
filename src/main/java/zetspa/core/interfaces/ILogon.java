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

    String getToken();

    void setToken(String token);

    String get(String name);

    void set(String name, String value);

    Set<String> getProperties();

    ILocale getLocale();
}
