package zetspa.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by huangshengtao on 2016-12-12.
 */
@Component
public class BeanFactory {
    static ApplicationContext context;

    @Autowired
    public BeanFactory(ApplicationContext ctx) {
        context = ctx;
    }

    public static Object getBean(String name) {
        if (context == null) {
            throw new IllegalArgumentException("context没有初始化，请勿使用zetspa.mvc.BeanFactory");
        }

        try {
            return context.getBean(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
