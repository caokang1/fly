package com.cignacmb.smart.base.annotation.excel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cignacmb.smart.base.validation.validator.excel.ColumnTypeValidator;

/**
 * Excel列类型
 * @author r9wuxx
 *
 */
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = { ColumnTypeValidator.class })
@Inherited
@Documented
public @interface ColumnType {
	
	public Type value();
	
	public String message() default "内容的格式与字段格式不匹配";

	/**
	 * 列类型
	 * <ul>
	 * <li>{@code normal}: 普通类型，不做处理</li>
	 * <li>{@code date}: 日期类型，对错误格式的日期转换成9999-09-09</li>
	 * <li>{@code number}: 数字类型，对错误格式的数字转换成1234567890</li>
	 * <li>{@code enums}: 枚举类型，对形如“01-XX”类型的值进行转换变成“01”</li>
	 * </ul>
	 * @author r9wuxx
	 *
	 */
	enum Type {
		normal,
		date,
		number,
		enums
	}
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
