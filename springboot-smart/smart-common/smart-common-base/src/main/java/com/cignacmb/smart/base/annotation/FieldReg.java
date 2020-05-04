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

import com.cignacmb.smart.base.validation.validator.FieldRegValidator;

/**
 * 正则表达式，可控制null的输出
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Repeatable(FieldReg.FieldRegs.class)
@Constraint(validatedBy = FieldRegValidator.class)
public @interface FieldReg {
	
	public String regexp();
	
	public String message();
	
	public boolean canbeNull() default true;
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	@Retention(RUNTIME)
	@Target(FIELD)
	@Inherited
	@Documented
	@interface FieldRegs {
		
		FieldReg[] value();
		
	}
	
}
