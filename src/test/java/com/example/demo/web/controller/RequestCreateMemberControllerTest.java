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
import com.example.demo.application.command.RequestCreateMemberCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.web.controller.RequestCreateMemberController;

@ExtendWith(SpringExtension.class)
class RequestCreateMemberControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	RequestCreateMemberController requestCreateMemberController;

	@Mock
	ApplicationCommandBus applicationCommandBusMock;

	@Mock
	MessageSource messageSourceMock;

	@Mock
	ApplicationContext applicationContextMock;

	@BeforeEach
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestCreateMemberController).build();
	}

	@Nested
	public class requestCreateMember_GET {
		@Test
		public void requestCreateMember画面へ遷移する() throws Exception {
			mockMvc.perform(get("/requestCreateMember")).andExpect(status().isOk())
					.andExpect(view().name("/reqmail/requestCreateMember"));
		}
	}

	@Nested
	public class requestCreateMember_POST {
		@Test
		public void 作成したRequestCreateMemberCommandを引数にアプリケーションサービスを呼び出しユーザ作成要求完了画面を表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());

			// when
			ArgumentCaptor<RequestCreateMemberCommand> argument = ArgumentCaptor
					.forClass(RequestCreateMemberCommand.class);

			// then
			mockMvc.perform(post("/requestCreateMember").param("mailAddress", "mailAddress")).andExpect(status().isOk())
					.andExpect(view().name("/reqmail/requestCreateMemberComplete"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(RequestCreateMemberCommand.class)));
			verifyNoMoreInteractions(applicationCommandBusMock);
		}

		@Test
		public void MemberAlreadyExistException発生時は入力したメールアドレスが既に登録されていることを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1008", null, null)).thenReturn("入力したメールアドレスは既に登録されています。");

			doThrow(new Exception(new MemberAlreadyExistException("入力したメールアドレスは既に登録されています。")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(RequestCreateMemberCommand.class));
			// when
			ArgumentCaptor<RequestCreateMemberCommand> argument = ArgumentCaptor
					.forClass(RequestCreateMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(post("/requestCreateMember").param("mailAddress", "mailAddress")).andExpect(status().isOk())
					.andExpect(view().name("/reqmail/requestCreateMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());

			assertThat(argument.getValue(), is(instanceOf(RequestCreateMemberCommand.class)));

			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1008"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}
	}
}
