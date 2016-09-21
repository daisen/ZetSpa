package zetspa.core.data;

import org.apache.commons.lang.StringUtils;
import zetspa.core.exception.SysException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangshengtao on 2016-6-13.
 */
public class SqlCommand {

    private String sql;
    private Connection connection;
    private final ArrayList<Map> objMaps = new ArrayList<>();
    private final StringBuffer where = new StringBuffer();
    private final ArrayList<String> orderList = new ArrayList<>();
    private Integer pageSize = -1;
    private Integer pageIndex = -1;
    private Integer totalRows = -1;
    private Integer objIndex = 0;

    public SqlCommand(String sql) {
        this.sql = sql;
        init();
    }

    //catch  ":xx"
    private static Pattern patterns = Pattern.compile(":([_A-Za-z0-9]+)");

    public SqlCommand() {
        init();
    }

    private void init() {
        objMaps.add(new HashMap());
    }

    public SqlCommand setObject(String key, Object value) {
        if (objMaps.get(objIndex).containsKey(key)) {
            throw new SysException("SqlCommand find same param");
        }

        objMaps.get(objIndex).put(key, value);
        return this;
    }

    public SqlCommand next() {
        objIndex++;

        if (objIndex >= objMaps.size()) {
            objMaps.add(new HashMap<String, Object>());
        }

        return this;
    }

    public String getSql() {
        return sql;
    }

    public SqlCommand setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public Object[] getParams() {
        return getParamList(objMaps.get(objIndex)).toArray();
    }

    private List getParamList(Map mapParam) {
        List<Object> params = new ArrayList<>();
        String str = combineSql();
        Matcher m = patterns.matcher(str);
        while (m.find()) {
            if (mapParam.containsKey(m.group(1))) {
                Object temp = mapParam.get(m.group(1));
                params.add(temp);
            } else {
                throw new SysException(m.group(1) + " 缺失参数值");
            }
        }
        return params;
    }

    public List<List> getParamsList() {
        List<List> paramsList = new ArrayList<>();
        for (Map it : this.objMaps) {
            paramsList.add(getParamList(it));
        }

        return paramsList;
    }

    public String getWhere() {
        return where.toString();
    }

    public SqlCommand appendWhere(String sql) {
        return appendWhere(sql, false);
    }

    public SqlCommand appendWhere(String sql, Boolean useOrOperator) {
        if (StringUtils.isEmpty(sql)) {
            return this;
        }

        if (where.length() != 0) {
            if (useOrOperator) {
                where.append(" OR ");
            } else {
                where.append(" AND ");
            }
        }

        where.append(sql);
        return this;
    }

    public String getOrder() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderList.size(); i++) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(orderList.get(i));
        }

        return sb.toString();
    }

    public SqlCommand addOrder(String order) {
        orderList.add(order);
        return this;
    }

    public SqlCommand appendOrderString(String strOrder) {
        String[] strOrders = strOrder.split(",");

        for (int i = 0; i < strOrders.length; i++) {
            this.orderList.add(strOrders[i]);
        }
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SqlCommand setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public SqlCommand setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    private String combineSql() {
        StringBuffer desSb = new StringBuffer();
        desSb.append(sql);

        String wh = getWhere();
        if (!StringUtils.isEmpty(wh)) {
            desSb.append(" WHERE ");
            desSb.append(wh);
        }

        String orderStr = getOrder();
        if (!StringUtils.isEmpty(orderStr)) {
            desSb.append(" ORDER BY ");
            desSb.append(orderStr);
        }
        return desSb.toString();
    }


    public String getRealSql() {
        String str = combineSql();
        Matcher m = patterns.matcher(str);
        while (m.find()) {
            str = str.replaceFirst(m.group(), "?");
        }
        return str;
    }

    public SqlCommand append(SqlCommand whereCmd, Boolean useOrOperator) {
        if (whereCmd == null) return this;

        this.appendWhere(whereCmd.getWhere(), useOrOperator);
        this.objMaps.get(objIndex).putAll(whereCmd.objMaps.get(objIndex));
        this.orderList.addAll(whereCmd.orderList);
        return this;
    }

    public SqlCommand append(SqlCommand whereCmd) {
        return append(whereCmd, false);
    }

    public Integer getBeginIndex() {
        return this.pageIndex * this.pageSize;
    }

    public Integer getEndIndex() {
        return (this.pageIndex + 1) * this.pageSize - 1;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
