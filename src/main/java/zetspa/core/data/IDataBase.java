package zetspa.core.data;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huangshengtao on 2016-9-23.
 */
public interface IDataBase {
    Connection getConnection();
    void closeConnection(Connection conn);

    DataSet queryEx(SqlCommand cmd) throws Exception;
    RowSet query(SqlCommand cmd) throws SQLException, Exception;

    Boolean executeCommand(SqlCommand cmd);

    DataSet queryProcedureEx(SqlCommand cmd);
    RowSet queryProcedure(SqlCommand cmd);
    void executeProcedure(SqlCommand cmd);
}
