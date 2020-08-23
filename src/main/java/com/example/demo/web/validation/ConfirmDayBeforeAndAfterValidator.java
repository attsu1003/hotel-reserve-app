package com.example.demo.web.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class ConfirmDayBeforeAndAfterValidator implements ConstraintValidator<ConfirmDayBeforeAndAfter, Object> {

	private String checkInDay;
	private String checkOutDay;
	private String message;
	private String message2;

	@Override
	public void initialize(ConfirmDayBeforeAndAfter constraintAnnotation) {
		this.checkInDay = constraintAnnotation.checkInDay();
		this.checkOutDay = constraintAnnotation.checkOutDay();
		this.message = constraintAnnotation.message();
		this.message2 = constraintAnnotation.message2();
	}

	@Override
	public boolean isValid(Object form, ConstraintValidatorContext context) {
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(form);
		Date checkInDayValue = (Date) beanWrapper.getPropertyValue(checkInDay);
		Date checkOutDayValue = (Date) beanWrapper.getPropertyValue(checkOutDay);
		if (checkInDayValue.before(checkOutDayValue)) {
			return true;
		}
		if (checkInDayValue.equals(checkOutDayValue)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(checkOutDay).addConstraintViolation();
			return false;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message2).addPropertyNode(checkOutDay).addConstraintViolation();
		return false;
	}
}
