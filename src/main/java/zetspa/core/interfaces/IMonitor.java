/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年5月10日 下午5:57:26
 */
package zetspa.core.interfaces;

import com.hisense.frame.core.exception.DataBaseException;
import com.hisense.frame.monitor.pojo.RequestMonitor;
import com.hisense.frame.monitor.pojo.SqlMonitor;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;


public interface IMonitor {

    String getMonitorId();

    void setMonitorId(String monitorId);

    String getOccurrenceTime();

    void setOccurrenceTime();

    BigInteger getReqCount();

    BigInteger getReqError();

    ILogon getLogon();

    void setLogon(ILogon logon);

    void setRequestMonitor(HttpServletRequest request) throws Exception;

    RequestMonitor getRequestMonitor() throws Exception;


    void setSqlMonitor(Exception e) throws Exception;

    SqlMonitor getSqlMonitor() throws Exception;

    void saveLog() throws Exception;
     Object[] getLogParams()  throws Exception;
}
