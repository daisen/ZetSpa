/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年6月7日 下午8:05:00
 */
package zetspa.core.interfaces;

public interface IUser {


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

    void setIsDeveloper(String isDeveloper);

    void setRightCtrlOrgCode(String rightCtrlOrgCode);

    void setUserId(String userId);

    void setUserCode(String userCode);

    void setUserName(String userName);

    void setUserType(String userType);

    void setOrgId(String orgId);

    void setOrgCode(String orgCode);

    void setOrgName(String orgName);

    void setOrgSort(String orgSort);

    void setPassword(String password);
}
