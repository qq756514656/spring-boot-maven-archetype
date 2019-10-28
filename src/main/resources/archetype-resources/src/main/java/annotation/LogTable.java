package ${package}.annotation;

import java.lang.annotation.*;

/**
 * @author liusy
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTable {

    String modelName() default "表中文名称";

    String propertyName() default "表属性名称";

    String tableSqlName() default "表数据库名称";

}
