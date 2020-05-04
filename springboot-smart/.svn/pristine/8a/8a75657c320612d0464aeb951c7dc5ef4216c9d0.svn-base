package com.cignacmb.smart.base.annotation.excel;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface Excel {
	
	public Mapping mapping();
	
	public String excelId();

	/**
	 * <ul>
	 * <li>{@code field}:按照字段名进行匹配</li>
	 * <li>{@code index}:按照列下标进行匹配</li>
	 * <li>{@code title}:按照列标题进行匹配</li>
	 * </ul>
	 * @author r9wuxx
	 *
	 */
	enum Mapping {
		field,
		index,
		title
	}
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
