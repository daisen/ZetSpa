/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年6月3日 下午9:09:54
 */
package zetspa.core.interfaces;

import javax.servlet.http.HttpServletResponse;

public interface ILogon {

    void setToken(String token);

    String getToken();

    void setLogon(IUser user,ICurState curState);

    IUser getUser();

    ICurState getCurState();

    Boolean checkLogon(HttpServletResponse response);

    String getProperty(String name);


    //兼容IUser
    String getUserId();

    String getUserCode();

    String getUserName();

    String getOrgId();

    String getUserType();

    String getOrgCode();

    String getOrgName();

    String getOrgSort();

    String getPassword();

    String getRightCtrlOrgCode();

    String getIsDeveloper();

    //ICurState

    String getLangId();

    Long getLastTime();

    String getLogonId();

    String getComputName();

    String getIpAddr();

    String getBizDate();





}
