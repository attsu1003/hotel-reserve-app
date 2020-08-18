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
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.DeleteMemberCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.WrongPasswordException;

@ExtendWith(SpringExtension.class)
class MemberControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	MemberController memberController;

	@Mock
	ApplicationCommandBus applicationCommandBusMock;

	@Mock
	MessageSource messageSourceMock;

	@Mock
	ApplicationContext applicationContextMock;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.memberController).build();
	}

	@Nested
	public class test_createMember_GET {
		@Test
		public void createMember画面へ遷移する() throws Exception {
			mockMvc.perform(get("/createMember")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/createMember"));
		}
	}

	@Nested
	public class test_createMember_POST {
		@Test
		public void 作成したCreateMemberCommandを引数にアプリケーションサービスを呼び出しユーザ登録が正常終了したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGM1001", null, null)).thenReturn("ユーザ登録が完了しました。");

			// when
			ArgumentCaptor<CreateMemberCommand> argument = ArgumentCaptor.forClass(CreateMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/createdMember").param("username", "test@gmail.com").param("password", "password"))
					.andExpect(status().isOk()).andExpect(view().name("/usermgmt/createMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(CreateMemberCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGM1001"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void MemberAlreadyExistException発生時はパスワード変更が失敗したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1001", null, null))
					.thenReturn("入力したユーザ名は既に登録されています。別のユーザ名を指定してください。");

			doThrow(new Exception(new MemberAlreadyExistException("入力したユーザ名は既に登録されています。別のユーザ名を指定してください。")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(CreateMemberCommand.class));

			// when
			ArgumentCaptor<CreateMemberCommand> argument = ArgumentCaptor.forClass(CreateMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/createdMember").param("username", "test@gmail.com").param("password", "password"))
					.andExpect(status().isOk()).andExpect(view().name("/usermgmt/createMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());

			assertThat(argument.getValue(), is(instanceOf(CreateMemberCommand.class)));
			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1001"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}
	}

	@Nested
	public class test_deleteMember_GET {
		@Test
		public void deleteMember画面へ遷移する() throws Exception {
			mockMvc.perform(get("/deleteMember")).andExpect(status().isOk())
					.andExpect(view().name("/usermgmt/deleteMember"));
		}
	}

	@Nested
	public class test_deleteMember_POST {
		@Test
		public void 作成したDeleteMemberCommandを引数にアプリケーションサービスを呼び出しユーザ退会が正常終了したことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());

			// when
			ArgumentCaptor<DeleteMemberCommand> argument = ArgumentCaptor.forClass(DeleteMemberCommand.class);

			// then
			mockMvc.perform(post("/deleteMember").param("password", "password").with(request -> {
				request.getSession().setAttribute("mailAddress", "test@gmail.com");
				return request;
			})).andExpect(status().isOk()).andExpect(view().name("deleteMemberComplete"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(DeleteMemberCommand.class)));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void MemberNotFoundException発生時はユーザ情報が見つからないことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1006", null, null))
					.thenReturn("ユーザ情報が見つかりません。恐れ入りますが、退会依頼を実施してください。");

			doThrow(new Exception(new MemberNotFoundException("ユーザ情報が見つかりません。恐れ入りますが、退会依頼を実施してください。")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(DeleteMemberCommand.class));
			// when
			ArgumentCaptor<DeleteMemberCommand> argument = ArgumentCaptor.forClass(DeleteMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/deleteMember").param("password", "password").with(request -> {
				request.getSession().setAttribute("mailAddress", "mailAddress");
				return request;
			})).andExpect(status().isOk()).andExpect(view().name("/usermgmt/deleteMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(DeleteMemberCommand.class)));

			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1006"));

			verifyNoMoreInteractions(applicationCommandBusMock);
			verifyNoMoreInteractions(messageSourceMock);
		}

		@Test
		public void WrongPasswordException発生時は入力したパスワードが誤っていることを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1007", null, null)).thenReturn("入力したパスワードが誤っています。");

			doThrow(new Exception(new WrongPasswordException("入力したパスワードが誤っています。"))).when(applicationCommandBusMock)
					.dispatch(Mockito.any(DeleteMemberCommand.class));
			// when
			ArgumentCaptor<DeleteMemberCommand> argument = ArgumentCaptor.forClass(DeleteMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/deleteMember").param("password", "password").with(request -> {
				request.getSession().setAttribute("mailAddress", "mailAddress");
				return request;
			})).andExpect(status().isOk()).andExpect(view().name("/usermgmt/deleteMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(DeleteMemberCommand.class)));

			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1007"));

			verifyNoMoreInteractions(applicationCommandBusMock);
			verifyNoMoreInteractions(messageSourceMock);
		}
	}

}
