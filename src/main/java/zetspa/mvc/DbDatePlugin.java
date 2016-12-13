package zetspa.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import zetspa.core.annotation.Plugin;
import zetspa.core.data.DataSet;
import zetspa.core.data.DefaultDataBase;
import zetspa.core.data.SqlCommand;
import zetspa.core.interfaces.IPlugin;

import java.sql.Connection;

/**
 * Created by huangshengtao on 2016-12-12.
 */
@Plugin
public class DbDatePlugin implements IPlugin {


    @Autowired
    DefaultDataBase defaultDataBase;

    @Override
    public void before() throws Exception {

    }

    @Override
    public void after(boolean pass) throws Exception {

    }

    @Override
    public Object execute(Object inParams) throws Exception {
        SqlCommand cmd = new SqlCommand("Select SYSDATETIME() as DT");
        cmd.setConnection(defaultDataBase.getConnection());
        DataSet ds = defaultDataBase.queryEx(cmd);
        return ds;
    }
}
