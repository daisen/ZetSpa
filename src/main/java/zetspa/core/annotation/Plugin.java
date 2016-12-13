package zetspa.core.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Created by huangshengtao on 2016-12-12.
 */
@Component
@Scope("prototype")
public @interface Plugin {

    @AliasFor(annotation = Component.class, attribute = "value")
    String name() default  "";
}
