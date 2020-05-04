package com.cignacmb.smart.base.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cignacmb.smart.base.validation.validator.FieldRelyValidator;

/**
 * 列依赖配置，通过Java bean中的fieldname进行关联
 * @author r9wuxx
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = FieldRelyValidator.class)
@Repeatable(FieldRely.FieldRelies.class)
@Inherited
@Documented
public @interface FieldRely {

	/**
	 * <ul>
	 * <li>{@code null2notnull}: 依赖字段值为null，当前字段为非null</li>
	 * <li>{@code notnull2null}: 依赖字段值为非null，当前字段可为null</li>
	 * <li>{@code notnull2notnull}: 依赖字段为非null，当前字段为非null</li>
	 * <li>{@code value2null}: 依赖字段为某个值时，当前字段可为空</li>
	 * <li>{@code value2notnull}: 依赖字段为某个值时，当前字段不可为空</li>
	 * <li>{@code value2value}: 当依赖字段为某个值时，当前字段需为某个值</li>
	 * <li>{@code valueltvalue}: 依赖字段小于当前字段</li>
	 * <li>{@code valuelqvalue}: 依赖字段小于等于当前字段</li>
	 * <li>{@code valuegtvalue}: 依赖字段大于当前字段</li>
	 * <li>{@code valuegqvalue}: 依赖字段大于等于当前字段</li>
	 * <li>{@code valueeqvalue}: 依赖字段值等于当前字段</li>
	 * <li>{@code value2regex}: 依赖字段为某个值时，当前字段要符合正则</li>
	 * <li>{@code regex2value}: 依赖字段符合正则时，当前字段需为某个值</li>
	 * <li>{@code regex2regex}: 依赖字段符合正则时，当前字段也需要符合</li>
	 * </ul>
	 *
	 * @author r9wuxx
	 *
	 */
	enum RelyType {
		null2notnull,
		notnull2null,
		notnull2notnull,
		value2null,
		value2notnull,
		value2value,
		valueltvalue,
		valuelqvalue,
		valuegtvalue,
		valuegqvalue,
		valueeqvalue,
		value2regex,
		regex2value,
		regex2regex
	};

	/**
	 * 依赖field
	 * @return
	 */
	public String relyField();

	/**
	 * 当前field
	 * @return
	 */
	public String currentField();

	/**
	 * 依赖类型，枚举
	 * @return
	 */
	public RelyType relyType();
	
	public String message();

	/**
	 * 如果为value类型，则relyValues[0]为依赖字段的值，relyValues[1]为当前字段应该的值
	 * @return
	 */
	public String[] relyValues() default {};
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	@Retention(RUNTIME)
	@Target(TYPE)
	@Inherited
	@Documented
	@interface FieldRelies {
		
		FieldRely[] value();
		
	}
}
