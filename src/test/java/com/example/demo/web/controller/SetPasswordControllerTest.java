package com.example.demo.web.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;

@ExtendWith(SpringExtension.class)
public class SetPasswordControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private SetPasswordController setPasswordController;

	@Mock
	ApplicationCommandBus applicationCommandBusMock;

	@Mock
	MessageSource messageSourceMock;

	@Mock
	ApplicationContext applicationContextMock;

	@BeforeEach
	public void setupMockMvc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.setPasswordController).build();
	}

	@Nested
	public class test_setPassword_GET {
		@Test
		public void setPassword画面へ遷移する() throws Exception {
			mockMvc.perform(get("/setPassword")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/setPassword"));
		}
	}

	@Nested
	public class test_setPassword_POST {
		@Test
		public void 作成したsetPasswordCommandを引数にアプリケーションサービスを呼び出しsetPasswordが正常終了したことを画面表示する() throws Exception {

			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGM1003", null, null))
					.thenReturn("パスワード再設定が完了しました。ログイン画面に戻り、ログインしてください。");

			// when
			ArgumentCaptor<SetPasswordCommand> argument = ArgumentCaptor.forClass(SetPasswordCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/setPassword").param("password", "password")
					.param("confirmPassword", "confirmPassword").with(request -> {
						request.getSession().setAttribute("mailAddress", "mailAddress");
						return request;
					})).andExpect(status().isOk()).andExpect(view().name("/usermgmt/setPassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(SetPasswordCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGM1003"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void PasswordNotMatchException発生時はパスワード設定が失敗したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());

			doThrow(new Exception(new PasswordNotMatchException("入力したパスワードとパスワード(確認用)が一致しません。", "password")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(SetPasswordCommand.class));

			// when
			ArgumentCaptor<SetPasswordCommand> argument = ArgumentCaptor.forClass(SetPasswordCommand.class);

			// then
			mockMvc.perform(post("/setPassword").param("password", "password")
					.param("confirmPassword", "confirmPassword").with(request -> {
						request.getSession().setAttribute("mailAddress", "mailAddress");
						return request;
					})).andExpect(status().isOk()).andExpect(view().name("/usermgmt/setPassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());

			assertThat(argument.getValue(), is(instanceOf(SetPasswordCommand.class)));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void MemberNotFoundException発生時はパスワード設定が失敗したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());

			doThrow(new Exception(new MemberNotFoundException("ユーザ情報が見つかりません。恐れ入りますがパスワード再設定依頼を実施してください。")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(SetPasswordCommand.class));

			// when
			ArgumentCaptor<SetPasswordCommand> argument = ArgumentCaptor.forClass(SetPasswordCommand.class);

			// then
			mockMvc.perform(post("/setPassword").param("password", "password")
					.param("confirmPassword", "confirmPassword").with(request -> {
						request.getSession().setAttribute("mailAddress", "mailAddress");
						return request;
					})).andExpect(status().isOk()).andExpect(view().name("/usermgmt/setPassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(SetPasswordCommand.class)));
			verifyNoMoreInteractions(applicationCommandBusMock);

		}
	}
}
