package zetspa.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by huangshengtao on 2016-9-28.
 */
public final class DbUtils {
    public DbUtils() {
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }

    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }

    }

    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }

    }

    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (SQLException var2) {
            ;
        }

    }

    public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
        try {
            closeQuietly(rs);
        } finally {
            try {
                closeQuietly(stmt);
            } finally {
                closeQuietly(conn);
            }
        }

    }

    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (SQLException var2) {
            ;
        }

    }

    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (SQLException var2) {
            ;
        }

    }

    public static void commitAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.commit();
            } finally {
                conn.close();
            }
        }

    }

    public static void commitAndCloseQuietly(Connection conn) {
        try {
            commitAndClose(conn);
        } catch (SQLException var2) {
            ;
        }

    }

    public static void rollback(Connection conn) throws SQLException {
        if (conn != null) {
            conn.rollback();
        }

    }

    public static void rollbackAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.rollback();
            } finally {
                conn.close();
            }
        }

    }

    public static void rollbackAndCloseQuietly(Connection conn) {
        try {
            rollbackAndClose(conn);
        } catch (SQLException var2) {
            ;
        }

    }

    private static final class DriverProxy implements Driver {
        private final Driver adapted;
        private boolean parentLoggerSupported = true;

        public DriverProxy(Driver adapted) {
            this.adapted = adapted;
        }

        public boolean acceptsURL(String url) throws SQLException {
            return this.adapted.acceptsURL(url);
        }

        public Connection connect(String url, Properties info) throws SQLException {
            return this.adapted.connect(url, info);
        }

        public int getMajorVersion() {
            return this.adapted.getMajorVersion();
        }

        public int getMinorVersion() {
            return this.adapted.getMinorVersion();
        }

        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return this.adapted.getPropertyInfo(url, info);
        }

        public boolean jdbcCompliant() {
            return this.adapted.jdbcCompliant();
        }

        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            if (this.parentLoggerSupported) {
                try {
                    Method e = this.adapted.getClass().getMethod("getParentLogger", new Class[0]);
                    return (Logger) e.invoke(this.adapted, new Object[0]);
                } catch (NoSuchMethodException var2) {
                    this.parentLoggerSupported = false;
                    throw new SQLFeatureNotSupportedException(var2);
                } catch (IllegalAccessException var3) {
                    this.parentLoggerSupported = false;
                    throw new SQLFeatureNotSupportedException(var3);
                } catch (InvocationTargetException var4) {
                    this.parentLoggerSupported = false;
                    throw new SQLFeatureNotSupportedException(var4);
                }
            } else {
                throw new SQLFeatureNotSupportedException();
            }
        }
    }
}

