package com.example.demo.web.form;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.example.demo.web.form.SetPasswordForm;

@SpringBootTest
class SetPasswordFormTest {

	@Autowired
	Validator validator;

	private SetPasswordForm setPasswordForm = new SetPasswordForm();
	private BindingResult bindingResult = new BindException(setPasswordForm, "setPasswordForm");

	@BeforeEach
	public void setup() {
		this.setPasswordForm.setPassword("password");
		this.setPasswordForm.setConfirmPassword("password");
	}

	@Nested
	public class 正常系 {
		@Test
		public void パスワード及び確認用パスワードが8文字以上16文字以下の場合() {
			validator.validate(setPasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError(), is(nullValue()));
		}
	}

	@Nested
	public class 異常系 {
		@Test
		public void パスワードが8文字以上16文字以下出ない場合() {
			setPasswordForm.setPassword("error");
			validator.validate(setPasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("password"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("パスワードは8文字以上16文字以下で入力してください。"));
		}

		@Test
		public void 確認用パスワードが8文字以上16文字以下出ない場合() {
			setPasswordForm.setConfirmPassword("error");
			validator.validate(setPasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("confirmPassword"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("パスワードは8文字以上16文字以下で入力してください。"));
		}
	}

}
