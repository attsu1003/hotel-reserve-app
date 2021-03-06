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
@Constraint(validatedBy = ConfirmDayBeforeAndAfterValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
public @interface ConfirmDayBeforeAndAfter {

	String message() default "チェックイン日とチェックアウト日が同じ日付になっています。";

	String message2() default "チェックアウト日がチェックイン日より過去の日付となっています。";
	
	String message3() default "チェックアウト日が本日より過去の日付となっています。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String checkInDay();

	String checkOutDay();

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		ConfirmDayBeforeAndAfter[] value();
	}
}
