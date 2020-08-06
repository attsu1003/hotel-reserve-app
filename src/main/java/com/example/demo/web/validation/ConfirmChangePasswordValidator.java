package com.example.demo.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class ConfirmChangePasswordValidator implements ConstraintValidator<ConfirmChangePassword, Object> {

	private String password;
	private String newPassword;
	private String message;

	public void initialize(ConfirmChangePassword confirmChangePassword) {
		this.password = confirmChangePassword.password();
		this.newPassword = confirmChangePassword.newPassword();
		this.message = confirmChangePassword.message();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
		Object passwordValue = beanWrapper.getPropertyValue(password);
		Object newPasswordValue = beanWrapper.getPropertyValue(newPassword);
		boolean matched = ObjectUtils.nullSafeEquals(passwordValue, newPasswordValue);
		if (!matched) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(password).addConstraintViolation();
			return false;
		}
	}
}
