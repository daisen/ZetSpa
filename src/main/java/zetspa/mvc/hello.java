package zetspa.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zetspa.core.data.DataSet;
import zetspa.core.data.IDataBase;
import zetspa.core.data.SqlCommand;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huangshengtao on 2016-11-15.
 */
@RestController
public class hello {

    @Autowired
    public IDataBase defaultDataBase;

    @RequestMapping("/hello")
    public Object hello(@RequestParam(value = "user", required = true, defaultValue = "world")String name) throws Exception {
        Connection connection = defaultDataBase.getConnection();
        SqlCommand sqlCommand = new SqlCommand("Select SYSDATETIME() as SysDate");
        sqlCommand.setConnection(connection);
        DataSet ds = defaultDataBase.queryEx(sqlCommand);
        connection.close();
        return ds;
    }
}
