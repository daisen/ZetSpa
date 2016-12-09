package zetspa.core.data;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huangshengtao on 2016-9-23.
 */
public interface IDataBase {
    Connection getConnection() throws SQLException;

    void closeConnection(Connection conn) throws SQLException;

    DataSet queryEx(SqlCommand cmd) throws Exception;

    RowSet query(SqlCommand cmd) throws Exception;

    Boolean executeCommand(SqlCommand cmd);

    DataSet queryProcedureEx(SqlCommand cmd) throws SQLException;

    RowSet queryProcedure(SqlCommand cmd) throws SQLException;

    void executeProcedure(SqlCommand cmd) throws SQLException;

    Object executeFunction(SqlCommand cmd) throws SQLException;

    int getSqlType(DataType dataType);
}
