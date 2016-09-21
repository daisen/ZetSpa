/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年5月11日 上午11:27:20
 */
package zetspa.core.interfaces;

public interface IDataSource<T> {

    void setDataSource() throws Exception;

    void setDataSource(String name) throws Exception;

    T getDataSource() throws Exception;

    T getDataSource(String name) throws Exception;

}
