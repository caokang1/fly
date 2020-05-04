package com.cignacmb.smart.base.annotation;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 列默认值
 * @author r9wuxx
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
@Inherited
public @interface FieldDefaultValue {
    public String value();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
