/**
 * 本地资源接口
 *
 * @author : hst
 * @Version :
 * @Date :
 */
package zetspa.core.interfaces;

public interface ILocale {

    String getString(String key);

    String getString(String key, String langId);

    String getString(String key, String langId, String defValue);

    String getDateTimeFormat();

    String getDateFormat();
}
