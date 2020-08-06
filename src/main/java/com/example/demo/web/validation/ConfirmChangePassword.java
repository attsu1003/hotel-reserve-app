package com.example.demo.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmChangePasswordValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
public @interface ConfirmChangePassword {

	String message() default "現在のパスワードと新パスワードが同じです。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String password();

	String newPassword();

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		ConfirmChangePassword[] value();
	}

}
