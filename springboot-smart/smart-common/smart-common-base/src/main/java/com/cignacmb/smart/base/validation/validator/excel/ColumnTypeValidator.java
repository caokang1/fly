package com.cignacmb.smart.base.validation.validator.excel;

import com.cignacmb.smart.base.annotation.excel.ColumnType;
import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.excel.ExcelConst;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ColumnTypeValidator implements ConstraintValidator<ColumnType, Object> {
	
	private ColumnType.Type columnType;
	
	@Override
	public void initialize(ColumnType columnType) {
		 this.columnType = columnType.value();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean flag = true;

		//null则直接返回TRUE
		if(value == null) {
			return flag;
		}
		
		switch(this.columnType) {
		
			case date:
				
				String dateStr = DateUtils.getDateStr((Date) value);
				
				flag = !dateStr.equals(ExcelConst.VALID_DATE_FALSE.getValue());

				//禁用默认的错误信息值
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("日期格式有误").addConstraintViolation();
				
				break;
			
			case number:
				
				flag = !(value.toString().indexOf(ExcelConst.VALID_NUMBER_FALSE.getValue()) >= 0);

				//禁用默认的错误信息值
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("数字格式有误").addConstraintViolation();
				
				break;
				
			default:
				
				break;
		
		}
		
		return flag;
		
	}

}
