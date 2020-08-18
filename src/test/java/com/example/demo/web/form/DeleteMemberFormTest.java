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

@SpringBootTest
class DeleteMemberFormTest {

	@Autowired
	Validator validator;

	private DeleteMemberForm deleteMemberForm = new DeleteMemberForm();
	private BindingResult bindingResult = new BindException(deleteMemberForm, "deleteMemberForm");

	@BeforeEach
	public void setup() {
		this.deleteMemberForm.setPassword("password");
	}

	@Nested
	public class 正常系 {
		@Test
		public void パスワードが8文字以上16文字以下の場合() {
			validator.validate(deleteMemberForm, bindingResult);
			assertThat(bindingResult.getFieldError(), is(nullValue()));
		}
	}

	@Nested
	public class 異常系 {
		@Test
		public void パスワードが8文字以上16文字以下でない場合() {
			validator.validate(deleteMemberForm, bindingResult);
		}
	}
}
