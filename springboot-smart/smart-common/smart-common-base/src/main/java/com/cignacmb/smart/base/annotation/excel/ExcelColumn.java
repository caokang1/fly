package com.cignacmb.smart.base.annotation.excel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;

/**
 * Excel列属性
 * @author r9wuxx
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Inherited
@Constraint(validatedBy = { })
@ColumnIndex(value = -1)
@ColumnType(value = ColumnType.Type.normal)
@ColumnTitle(value = "")
@Documented
public @interface ExcelColumn {
	
	/**
	 * 列下标
	 * @return
	 */
	@OverridesAttribute(constraint = ColumnIndex.class, name = "value")
	public int index() default -1;
	
	/**
	 * 列标题
	 * @return
	 */
	@OverridesAttribute(constraint = ColumnTitle.class, name = "value")
	public String title() default "";
	
	/**
	 * 列类型
	 * @return
	 */
	@OverridesAttribute(constraint = ColumnType.class, name = "value")
	public ColumnType.Type type() default ColumnType.Type.normal;
	
	public String message() default "";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
