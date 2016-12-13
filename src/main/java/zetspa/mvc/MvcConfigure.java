package zetspa.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

/**
 * Created by huangshengtao on 2016-12-09.
 */
@Configuration
@EnableWebMvc
public class MvcConfigure extends WebMvcConfigurerAdapter {

    @Autowired
    DefaultInterceptor defaultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultInterceptor);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new DefaultHandlerExceptionResolver());
    }
}
