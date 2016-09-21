/**
 * 数据库访问接口
 *
 * @author : hst
 * @Version :
 * @Date :
 */
package zetspa.core.interfaces;

import zetspa.core.data.SqlCommand;
import zetspa.core.data.StoreProcParam;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDataBase {



    //根据Datasource构建
    //  void build();

    // void build(String dataSourceName);
    // 获取事务连接(有连接池提供，包括打开关闭等管理方法)


    // commmon jdbc
    RowSet getSqlQuery(Connection conn, String sql, Object... params) throws Exception;

    List<Map<String, Object>> query(SqlCommand cmd) throws SQLException, Exception;

    //分页查询
    List<Map<String, Object>> queryEx(Integer firstRow, Integer endRow, Connection connection, String sql,
                                      Object... params) throws Exception;

    // select listBean
    List<Object> queryEx(Connection connection, String sql, Class cls, Object... params)
            throws Exception;

    //select map listmap string
    Object queryEx(Connection conn, String sql, Integer retype, Object... params)
            throws Exception;

    // insert delete update common
    int executeUpdate(Connection conn, String sql, Object... params) throws Exception;

    // insert delete update batch update
    int[] batchUpdate(Connection connection, String sql, List<List> params) throws Exception;

    // 存储过程和函数
    List<Map<String, Object>> exeProcedureQuery(Connection conn, String procName, Object... params)
            throws Exception;

    Map<String, Object> getProcedureRowSet(Connection conn, String storedProcName, Map<String, StoreProcParam> paramMap) throws Exception;
    Map<String, Object> executeProcedure(Connection conn, String storedProcName, Map<String, StoreProcParam> paramMap)
            throws Exception;

    Map<String, Object> executeFunction(Connection conn, String storedProcName, Map<String, StoreProcParam> paramMap)
            throws Exception;


}