package zetspa.mvc;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zetspa.core.config.ResponseCode;
import zetspa.core.interfaces.IPlugin;

/**
 * Created by huangshengtao on 2016-12-09.
 */
@RestController
@RequestMapping(value = "/api")
public class PluginController {

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/plugin")
    public Object execute(String plugin, String plugin_data) throws Exception {
        if (StringUtils.isEmpty(plugin)) {
            throw new NullArgumentException("plugin");
        }

        Object pluginObject =  BeanFactory.getBean(plugin);
        //未能找到满足的插件
        if (pluginObject == null) {
            return ResponseCode.NO_BEAN.toString();
        }

        Object result = null;
        //检查类型是否合适
        if (pluginObject instanceof IPlugin) {
            IPlugin pCall = (IPlugin) pluginObject;
            boolean pass = false;
            try {
                pCall.before();
                result = pCall.execute(plugin_data);
                pass = true;
            } catch (Exception ex) {
                throw ex;
            } finally {
                pCall.after(pass);
            }
        } else {
            result = ResponseCode.ERR_BEAN.toString();
        }

        return result;
    }
}
