package com.cignacmb.smart.base.annotation.excel;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ColumnMapFields {
	
	public String[] mappings();
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
