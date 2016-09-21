package zetspa.core.interfaces;

/**
 * Created by tommy on 2016/8/13.
 */
public interface IFingerPrint {

    String getVendorCode();


    void setVendorCode(String vendorCode) ;

    /*
     * 设置指纹
     */
    void setFingerPrint(int type);

    /*
     * 读取机器指纹
     */
    String getFingerPrint();

    /*
     *  从锁中读取内置指纹
     */
    String getFingerPrintFromKey();

    /*
     *指纹校验
     */
    Boolean checkFingerPrint(String fingerPrint);
}
