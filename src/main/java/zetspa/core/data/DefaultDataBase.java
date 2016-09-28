package zetspa.core.data;

import com.sun.rowset.CachedRowSetImpl;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import zetspa.core.exception.SysException;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by huangshengtao on 2016-9-23.
 */
@Component("defaultDataBase")
@Scope("prototype")
public class DefaultDataBase implements IDataBase {

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
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeConnection(Connection conn) throws SQLException {
        this.close(conn);
    }

    @Override
    public DataSet queryEx(SqlCommand cmd) throws Exception {
        return new DataSet(query(cmd));
    }

    @Override
    public RowSet query(SqlCommand cmd) throws Exception {
        CachedRowSetImpl rowSet = new CachedRowSetImpl();
        try {
            PreparedStatement stmt = cmd.getConnection().prepareStatement(cmd.getRealSql());
            fillStatement(stmt, cmd.getParams());
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery();
                rowSet.populate(rs);
            } finally {
                try {
                    this.close(rs);
                } finally {
                    this.close(stmt);
                }
            }

        } catch (Exception ex) {
            SysException.throwException("查询数据失败", ex);
        }
        return rowSet;
    }

    @Override
    public Boolean executeCommand(SqlCommand cmd) {
        try {
            PreparedStatement stmt = cmd.getConnection().prepareStatement(cmd.getRealSql());
            fillStatement(stmt, cmd.getParams());
            try {
                return stmt.execute();
            } finally {
                this.close(stmt);
            }

        } catch (Exception ex) {
            SysException.throwException("查询数据失败", ex);
        }

        return true;
    }

    @Override
    public DataSet queryProcedureEx(SqlCommand cmd) throws SQLException {
        return new DataSet(queryProcedure(cmd));
    }

    @Override
    public RowSet queryProcedure(SqlCommand cmd) throws SQLException {
        String strCaller = "";
        ArrayList<ProcedureParam> params = cmd.getStoreProcParams();
        CachedRowSetImpl rowSet = new CachedRowSetImpl();
        for (int e = 0; e < params.size(); ++e) {
            if (strCaller.length() > 0) {
                strCaller += ",";
            }
            strCaller += "?";
        }

        String storedProcName = cmd.getSql();
        strCaller = "{call " + storedProcName + "(" + strCaller + ")}";
        CallableStatement cs = cmd.getConnection().prepareCall(strCaller);

        try {

            for (int e = 0; e < params.size(); ++e) {
                ProcedureParam p = params.get(e);
                if (ProcParamType.FUNCRESULT == p.getParamIoType()) {
                    throw new SysException("执行函数请使用executeFunction");
                }

                //输出参数
                if (p.getParamIoType() != ProcParamType.INPUT
                        && p.getParamIoType() != ProcParamType.NONE) {
                    cs.registerOutParameter(e + 1, this.getSqlType(p.getParamDataType()));
                }

                //输入参数
                if (p.getParamIoType() == ProcParamType.INPUT
                        || p.getParamIoType() == ProcParamType.INOUT) {
                    Object value = p.getValue();
                    if (value != null) {
                        cs.setObject(e + 1, value);
                    } else {
                        int pType = this.getSqlType(p.getParamDataType());
                        cs.setNull(e + 1, pType);
                    }
                }
            }

            cs.execute();

            for (int i = 0; i < params.size(); ++i) {
                ProcedureParam param = params.get(i);
                if (param.getParamIoType() != ProcParamType.INPUT
                        && param.getParamIoType() != ProcParamType.NONE) {
                    if (DataType.CURSOR == param.getParamDataType()) {
                        try {
                            ResultSet csValue = (ResultSet) cs.getObject(i + 1);
                            if (null != csValue) {
                                try {
                                    rowSet.populate(csValue);
                                    param.setValue(rowSet);
                                } finally {
                                    DbUtils.closeQuietly(csValue);
                                }

                            }
                        } catch (SQLException sqlEx) {
                            if (!sqlEx.getMessage().contains("Cursor is closed")) {
                                throw sqlEx;
                            }
                        }
                    } else {
                        Object csValue = cs.getObject(i + 1);
                        param.setValue(csValue);
                    }
                }
            }

        } finally {
            cs.close();
        }
        return rowSet;
    }

    @Override
    public void executeProcedure(SqlCommand cmd) throws SQLException {
        String strCaller = "";
        ArrayList<ProcedureParam> params = cmd.getStoreProcParams();
        for (int e = 0; e < params.size(); ++e) {
            if (strCaller.length() > 0) {
                strCaller += ",";
            }
            strCaller += "?";
        }

        String storedProcName = cmd.getSql();
        strCaller = "{call " + storedProcName + "(" + strCaller + ")}";
        CallableStatement cs = cmd.getConnection().prepareCall(strCaller);

        try {

            for (int e = 0; e < params.size(); ++e) {
                ProcedureParam p = params.get(e);
                if (ProcParamType.FUNCRESULT == p.getParamIoType()) {
                    throw new SysException("执行函数请使用executeFunction");
                }

                //输出参数
                if (p.getParamIoType() != ProcParamType.INPUT
                        && p.getParamIoType() != ProcParamType.NONE) {
                    cs.registerOutParameter(e + 1, this.getSqlType(p.getParamDataType()));
                }

                //输入参数
                if (p.getParamIoType() == ProcParamType.INPUT
                        || p.getParamIoType() == ProcParamType.INOUT) {
                    Object value = p.getValue();
                    if (value != null) {
                        cs.setObject(e + 1, value);
                    } else {
                        int pType = this.getSqlType(p.getParamDataType());
                        cs.setNull(e + 1, pType);
                    }
                }
            }

            cs.execute();

            for (int i = 0; i < params.size(); ++i) {
                ProcedureParam param = params.get(i);
                if (param.getParamIoType() != ProcParamType.INPUT
                        && param.getParamIoType() != ProcParamType.NONE
                        && DataType.CURSOR != param.getParamDataType()) {
                    Object csValue = cs.getObject(i + 1);
                    param.setValue(csValue);

                }
            }

        } finally {
            cs.close();
        }
    }

    public Object executeFunction(SqlCommand cmd) throws SQLException{
        String strCaller = "";
        ArrayList<ProcedureParam> params = cmd.getStoreProcParams();
        for (int e = 0; e < params.size(); ++e) {
            ProcedureParam p = params.get(e);
            if (p.getParamIoType() == ProcParamType.FUNCRESULT) {
                continue;
            }

            if (ProcParamType.FUNCRESULT == p.getParamIoType() && e != 0) {
                throw new SysException("函数返回值应在参数列表第一位");
            }

            if (strCaller.length() > 0) {
                strCaller += ",";
            }
            strCaller += "?";
        }

        if (ProcParamType.FUNCRESULT != params.get(0).getParamIoType()) {
            params.add(0, new ProcedureParam("funcResult", DataType.STRING, 0, ProcParamType.FUNCRESULT, null));
        }

        String storedProcName = cmd.getSql();
        strCaller = "?={call " + storedProcName + "(" + strCaller + ")}";
        CallableStatement cs = cmd.getConnection().prepareCall(strCaller);

        try {

            for (int e = 0; e < params.size(); ++e) {
                ProcedureParam p = params.get(e);

                //输出参数
                if (p.getParamIoType() != ProcParamType.INPUT
                        && p.getParamIoType() != ProcParamType.NONE) {
                    cs.registerOutParameter(e + 1, this.getSqlType(p.getParamDataType()));
                }

                //输入参数
                if (p.getParamIoType() == ProcParamType.INPUT
                        || p.getParamIoType() == ProcParamType.INOUT) {
                    Object value = p.getValue();
                    if (value != null) {
                        cs.setObject(e + 1, value);
                    } else {
                        int pType = this.getSqlType(p.getParamDataType());
                        cs.setNull(e + 1, pType);
                    }
                }
            }

            cs.execute();

            for (int i = 0; i < params.size(); ++i) {
                ProcedureParam param = params.get(i);
                if (param.getParamIoType() != ProcParamType.INPUT
                        && param.getParamIoType() != ProcParamType.NONE
                        && DataType.CURSOR != param.getParamDataType()) {
                    Object csValue = cs.getObject(i + 1);
                    param.setValue(csValue);
                }
            }

        } finally {
            cs.close();
        }

        return params.get(0).getValue();
    }

    protected void close(Connection conn) throws SQLException {
        DbUtils.close(conn);
    }

    protected void close(Statement stmt) throws SQLException {
        DbUtils.close(stmt);
    }

    protected void close(ResultSet rs) throws SQLException {
        DbUtils.close(rs);
    }

    @Override
    public int getSqlType(DataType dataType) {
        switch (dataType) {
            case NUMBER:
                return Types.NUMERIC;
            case DATETIME:
                return Types.DATE;
            case STRING:
                return Types.VARCHAR;
            case CLOB:
                return Types.CLOB;
            case TIMESTAMP:
                return Types.NUMERIC;
            case CURSOR:
                return Types.OTHER;
            case BLOB:
                return Types.BLOB;
            default:
                return Types.VARCHAR;
        }
    }
}
