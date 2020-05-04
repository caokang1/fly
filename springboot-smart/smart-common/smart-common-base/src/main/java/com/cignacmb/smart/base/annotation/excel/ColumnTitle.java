package com.cignacmb.smart.base.annotation.excel;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

/**
 * Excel列标题
 * @author r9wuxx
 *
 */
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
@Inherited
@Documented
public @interface ColumnTitle {
	
	public String value();
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
