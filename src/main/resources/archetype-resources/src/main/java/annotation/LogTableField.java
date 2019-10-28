package ${package}.annotation;

import java.lang.annotation.*;

/**
 * @author liusy
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTableField {

    String fieldName() default "字段中文名称";

    String fieldPropertyName() default "字段属性名称";

    String fieldSqlName() default "字段sql名称";

}
