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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.ChangePasswordCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;

@ExtendWith(SpringExtension.class)
class ChangePasswordControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	ChangePasswordController changePasswordController;

	@Mock
	ApplicationCommandBus applicationCommandBusMock;

	@Mock
	MessageSource messageSourceMock;

	@Mock
	ApplicationContext applicationContextMock;

	@BeforeEach
	public void setup() {
		// mockMvcのbuild
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.changePasswordController).build();

		// 認証設定
		Authentication authentication = Mockito.mock(Authentication.class);
		when(authentication.getName()).thenReturn("test@gmail.com");
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Nested
	public class test_changePassword_GET {
		@Test
		public void changePassword画面へ遷移する() throws Exception {
			mockMvc.perform(get("/changePassword")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/changePassword"));
		}
	}

	@Nested
	public class test_changePassword_POST {
		@Test
		public void 作成したchangePasswordCommandを引数にアプリケーションサービスを呼び出しパスワード変更が正常終了したことを画面表示する() throws Exception {

			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGM1002", null, null)).thenReturn("パスワード変更が完了しました。");

			// when
			ArgumentCaptor<ChangePasswordCommand> argument = ArgumentCaptor.forClass(ChangePasswordCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/changePassword").param("password", "password").param("newPassword", "newPassword")
					.param("newConfirmPassword", "newPassword")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/changePassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(ChangePasswordCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGM1002"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void PasswordNotMatchException発生時はパスワード変更が失敗したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1004", null, null)).thenReturn("入力した新パスワードと新パスワード(確認用)が一致しません。");

			doThrow(new Exception(new PasswordNotMatchException("入力した新パスワードと新パスワード(確認用)が一致しません。", "password")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(ChangePasswordCommand.class));

			// when
			ArgumentCaptor<ChangePasswordCommand> argument = ArgumentCaptor.forClass(ChangePasswordCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/changePassword").param("password", "password").param("newPassword", "newPassword")
					.param("newConfirmPassword", "newPassword")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/changePassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());

			assertThat(argument.getValue(), is(instanceOf(ChangePasswordCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1004"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void MemberNotFoundException発生時はパスワード変更が失敗したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1007", null, null)).thenReturn("入力した新パスワードと新パスワード(確認用)が一致しません。");

			doThrow(new Exception(new MemberNotFoundException("現在のパスワードの入力が誤っています。"))).when(applicationCommandBusMock)
					.dispatch(Mockito.any(ChangePasswordCommand.class));

			// when
			ArgumentCaptor<ChangePasswordCommand> argument = ArgumentCaptor.forClass(ChangePasswordCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/changePassword").param("password", "password").param("newPassword", "newPassword")
					.param("newConfirmPassword", "newPassword")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/changePassword"));

			verify(applicationCommandBusMock).dispatch(argument.capture());

			assertThat(argument.getValue(), is(instanceOf(ChangePasswordCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1007"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}
	}
}
