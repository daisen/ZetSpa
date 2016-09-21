package zetspa.core.interfaces;

/**
 * Created by tommy on 2016/8/17.
 */
public interface IService {

    void before() throws Exception;

    Object service(Object obj) throws Exception;

    void after() throws Exception;

    Object exeService(Object obj) throws Exception;
}
