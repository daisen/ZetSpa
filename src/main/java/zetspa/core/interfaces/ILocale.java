/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年5月10日 下午5:58:09
 */
package zetspa.core.interfaces;

import java.sql.Connection;


/*
 * 多属性文件读取并合并
 * 时间等符号本地化处理
 */

public interface ILocale {

    String getValue(String key);

    String getValue(String key, String langId);

    String getValue(String key, String langId, String defValue);


    String getDateTimeFormat();

    String getDateFormat();

}
