package zetspa.core.data;

import com.sun.rowset.CachedRowSetImpl;
import zetspa.core.exception.SysException;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Created by huangshengtao on 2016-9-23.
 */
public class DefaultDataBase implements IDataBase {

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeConnection(Connection conn) {

    }

    @Override
    public DataSet queryEx(SqlCommand cmd) throws Exception {
        return new DataSet(query(cmd));
    }

    public static void fillStatement(PreparedStatement stmt, Object[] params) throws Exception {
        if (params == null) {
            return;
        }

        for (int index = 0; index < params.length; index++) {
            Object p = params[index];
            if (p != null) {
                if (p.getClass().isAssignableFrom(java.util.Date.class)) {
                    // 没有时分秒
                    stmt.setTimestamp(index, new java.sql.Timestamp(
                            ((java.util.Date) p).getTime()));
                } else if (p.getClass().isAssignableFrom(java.sql.Clob.class)) {
                    stmt.setClob(index, (java.sql.Clob) p);
                } else
                    stmt.setObject(index, p);
            } else {
                stmt.setNull(index, Types.VARCHAR);
            }
        }
    }

    @Override
    public RowSet query(SqlCommand cmd) throws Exception {
        CachedRowSetImpl rowSet = new CachedRowSetImpl();
        try {
            PreparedStatement stmt = cmd.getConnection().prepareStatement(cmd.getRealSql());
            fillStatement(stmt, cmd.getParams());
            ResultSet rs = stmt.executeQuery();
            rowSet.populate(rs);
        } catch (Exception ex) {
            SysException.throwException("查询数据失败", ex);
        } finally {
            rs.close();
        }

        return rowSet;
    }

    @Override
    public Boolean executeCommand(SqlCommand cmd) {
        return null;
    }

    @Override
    public DataSet queryProcedureEx(SqlCommand cmd) {
        return null;
    }

    @Override
    public RowSet queryProcedure(SqlCommand cmd) {
        return null;
    }

    @Override
    public void executeProcedure(SqlCommand cmd) {

    }
}
