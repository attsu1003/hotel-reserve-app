package com.example.demo.web.form;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
public class ChangePasswordFormTest {

	@Autowired
	Validator validator;

	private ChangePasswordForm changePasswordForm = new ChangePasswordForm();
	private BindingResult bindingResult = new BindException(changePasswordForm, "changePasswordForm");

	@BeforeEach
	public void setup() {
		this.changePasswordForm.setPassword("password");
		this.changePasswordForm.setNewPassword("newPassword");
		this.changePasswordForm.setNewConfirmPassword("newPassword");
	}

	@Nested
	public class 正常系 {
		@Test
		public void パスワードと新パスワードと新パスワード確認用パスワードがすべて8文字以上16文字以下かつ入力したパスワードと新パスワードの値が異なる場合() {
			validator.validate(changePasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError(), is(nullValue()));
		}
	}

	@Nested
	public class 異常系 {
		@Test
		public void パスワードと新パスワードと新パスワード確認用パスワードがすべて8文字以上16文字以下かつ入力したパスワードと新パスワードの値が同じ場合() {
			changePasswordForm.setNewPassword("password");
			validator.validate(changePasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("password"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("現在のパスワードと新パスワードが同じです。"));
		}

		@Test
		public void パスワードが8文字以上16文字以下でない場合() {
			changePasswordForm.setPassword("error");
			validator.validate(changePasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("password"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("パスワードは8文字以上16文字以下で入力してください。"));
		}

		@Test
		public void 新パスワードが8文字以上16文字以下でない場合() {
			changePasswordForm.setNewPassword("error");
			validator.validate(changePasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("newPassword"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("パスワードは8文字以上16文字以下で入力してください。"));
		}

		@Test
		public void 新パスワード確認用パスワードが8文字以上16文字以下でない場合() {
			changePasswordForm.setNewConfirmPassword("error");
			validator.validate(changePasswordForm, bindingResult);
			assertThat(bindingResult.getFieldError().getField(), is("newConfirmPassword"));
			assertThat(bindingResult.getFieldError().getDefaultMessage(), is("パスワードは8文字以上16文字以下で入力してください。"));
		}
	}
}
