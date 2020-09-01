package com.example.demo.web.form;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
class ReserveConfirmFormTest {

	@Autowired
	Validator validator;

	private ReserveConfirmForm reserveConfirmForm = new ReserveConfirmForm();
	private BindingResult bindingResult = new BindException(reserveConfirmForm, "reserveConfirmForm");

	@BeforeEach
	public void setup() {
		this.reserveConfirmForm.setMemberid("testID");
	}

	@Nested
	public class 正常系 {
		@SuppressWarnings("deprecation")
		@Test
		public void チェックイン日がチェックアウト日より過去日となっている場合() {
			reserveConfirmForm.setCheckInDay(new Date(122, 8, 15));
			reserveConfirmForm.setCheckOutDay(new Date(122, 8, 16));
			validator.validate(reserveConfirmForm, bindingResult);
			assertThat(bindingResult.getFieldError(), is(nullValue()));
		}
	}

	@Nested
	public class 異常系 {
		@SuppressWarnings("deprecation")
		@Test
		public void チェックイン日とチェックアウト日が同じ日になっている場合() {
			reserveConfirmForm.setCheckInDay(new Date(122, 8, 15));
			reserveConfirmForm.setCheckOutDay(new Date(122, 8, 15));
			validator.validate(reserveConfirmForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("checkOutDay"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("チェックイン日とチェックアウト日が同じ日付になっています。"));
		}

		@SuppressWarnings("deprecation")
		@Test
		public void チェックアウト日がチェックイン日より過去日となっている場合() {
			reserveConfirmForm.setCheckInDay(new Date(122, 8, 16));
			reserveConfirmForm.setCheckOutDay(new Date(122, 8, 15));
			validator.validate(reserveConfirmForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("checkOutDay"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("チェックアウト日がチェックイン日より過去の日付となっています。"));
		}

		@SuppressWarnings("deprecation")
		@Test
		public void チェックアウト日が本日より過去の日付となっている場合() {
			reserveConfirmForm.setCheckInDay(new Date(120, 7, 15));
			reserveConfirmForm.setCheckOutDay(new Date(120, 7, 16));
			validator.validate(reserveConfirmForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("checkOutDay"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("チェックアウト日が本日より過去の日付となっています。"));

		}
	}
}
