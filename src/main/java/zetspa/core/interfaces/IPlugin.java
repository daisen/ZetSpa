/**
 * everything will be ok
 *
 * @author : huangxianhui
 * @Version :
 * @Date : 2016年5月10日 下午9:07:45
 */
package zetspa.core.interfaces;

public interface IPlugin {

    void before() throws Exception;

    void after() throws Exception;

    Object execute(Object inParams) throws Exception;
}