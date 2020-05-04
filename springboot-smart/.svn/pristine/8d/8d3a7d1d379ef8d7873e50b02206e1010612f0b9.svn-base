package com.cignacmb.smart.base.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cignacmb.smart.base.validation.validator.FieldEnumValidator;

/**
 * 字段枚举值
 * @author r9wuxx
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {FieldEnumValidator.class})
@Repeatable(FieldEnum.FieldEnums.class)
@Inherited
public @interface FieldEnum {
	
	public String[] value();
	
	public String message();
	
	public boolean canbeNull() default false;
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	@Retention(RUNTIME)
	@Target(FIELD)
	@Inherited
	@Documented
	@interface FieldEnums {
		
		FieldEnum[] value();
		
	}
	
}
