package zetspa.core.interfaces;

import zetspa.core.data.SqlCommand;

import java.util.List;
import java.util.Map;

/**
 * Created by huangshengtao on 2016-9-21.
 */
public interface IDao {
    List<Map<String, Object>> query(SqlCommand cmd) {

    }
}
