package zetspa.core.data;

import zetspa.core.utils.Converter;

import javax.sql.RowSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangshengtao on 2016-9-23.
 */
public class DataSet extends ArrayList<HashMap<String, String>> {

    public DataSet() {

    }

    public DataSet(RowSet rs) throws SQLException {
        while (rs.next()) {
            HashMap<String, String> resultMap = new HashMap<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++) {// count i
                String fieldName = rsmd.getColumnName(i).toLowerCase();
                String fieldClassName = rsmd.getColumnClassName(i);
                resultMap.put(fieldName, formatValue(rs, fieldClassName, fieldName));
            }
            this.add(resultMap);
        }
    }

    public static String formatValue(RowSet rs, String fieldClassName, String fieldName) {
        try {
            if (null == rs.getObject(fieldName)) {
                return null;
            }
            if (fieldClassName.equals("java.sql.Timestamp")
                    || fieldClassName.equals("java.sql.Time")
                    || fieldClassName.equals("java.sql.Date")
                    || fieldClassName.equals("java.util.Date")) {
                java.util.Date date = rs.getTimestamp(fieldName);
                return Converter.toString(date);
            }

            if (fieldClassName.contains("CLOB")) {// "java.sql.Clob"

                java.sql.Clob clob = rs.getClob(fieldName);
                if (clob != null) {
                    return clob.getSubString(1, (int) clob.length());
                } else {
                    return null;
                }
            }
            if (fieldClassName.contains("BLOB")) {// java.sql.Blob
                java.sql.Blob blob = rs.getBlob(fieldName);
                if (blob != null) {
                    byte[] bytes = blob.getBytes(1, (int) blob.length());
                    return new String(bytes);
                } else {
                    return null;
                }
            }

            if (fieldClassName.equals("[B") || fieldClassName.equals("byte[]")) {
                byte[] s = rs.getBytes(fieldName);
                return new String(s);
            }
            return rs.getObject(fieldName).toString();
        } catch (Exception ex) {
            return null;
        }
    }

}
