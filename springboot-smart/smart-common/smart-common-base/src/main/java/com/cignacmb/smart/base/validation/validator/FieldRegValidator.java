package com.cignacmb.smart.base.validation.validator;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.cignacmb.smart.base.annotation.FieldReg;
import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.basic.RegexUtils;
import org.apache.commons.lang3.StringUtils;

public class FieldRegValidator implements ConstraintValidator<FieldReg, Object> {
	
	private String regexp;
	private boolean canbeNull;
	
	@Override
	public void initialize(FieldReg fieldReg) {
		 this.regexp = fieldReg.regexp();
		 this.canbeNull = fieldReg.canbeNull();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		String val = "";
		
		if("class java.util.Date".equals(value.getClass().getName())) {
			val = DateUtils.getDateStr((Date) value);
		}
		else {
			val = value.toString();
		}
		
		if(StringUtils.isEmpty(val) && this.canbeNull) {
			return true;
		}
		
		return RegexUtils.isValid(val, this.regexp);
	}

}
