package com.cignacmb.smart.base.validation.validator;

import com.cignacmb.smart.base.annotation.FieldEnum;
import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.basic.StringUtils;

import java.util.Arrays;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldEnumValidator implements ConstraintValidator<FieldEnum, Object> {
	
	private String[] enums;
	private boolean canbeNull;
	
	@Override
	public void initialize(FieldEnum fieldEnum) {
		this.enums = fieldEnum.value();
		this.canbeNull = fieldEnum.canbeNull();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean flag = true;
		
		if(this.enums == null || this.enums.length == 0) {
			return flag;
		}
		
		if(value == null) {
			return flag;
		}
		
		String val = "";
		String enumstr = Arrays.toString(this.enums);
		
		if("class java.util.Date".equals(value.getClass().getName())) {
			val = DateUtils.getDateStr((Date) value);
		}
		else {
			val = value.toString();
		}
		
		if(StringUtils.isEmpty(val)) {
			if(this.canbeNull) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		else {
			
			if(!enumstr.contains("["+val) && !enumstr.contains(", " +val+",") && !enumstr.contains(val+"]")) {
				flag = false;
			}
			
		}
		
		if(!flag) {
			StringBuilder sb = new StringBuilder();
			sb.append(context.getDefaultConstraintMessageTemplate());
			sb.append("枚举值需要存在").append(enumstr).append("中");
			//禁用默认的错误信息值
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(sb.toString()).addConstraintViolation();
		}
		
		return flag;
	}
	
}
