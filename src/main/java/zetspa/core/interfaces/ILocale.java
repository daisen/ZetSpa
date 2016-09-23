/**
 * 本地资源接口
 *
 * @author : hst
 * @Version :
 * @Date :
 */
package zetspa.core.interfaces;

public interface ILocale {

    String getValue(String key);

    String getValue(String key, String langId);

    String getValue(String key, String langId, String defValue);

    String getDateTimeFormat();

    String getDateFormat();
}
