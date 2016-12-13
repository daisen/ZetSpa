package zetspa.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import zetspa.core.Global;
import zetspa.core.interfaces.ILogon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangshengtao on 2016-12-09.
 */
@Component
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        initLogon(request);
        return true;
    }

    private void initLogon(HttpServletRequest request) {
        String token = request.getParameter("token");
        ILogon logon = (ILogon) context.getBean("logOn");
        logon.setToken(token);
        Global.setLogon(logon);
    }
}
