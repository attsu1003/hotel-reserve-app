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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberNotFoundException;

@ExtendWith(SpringExtension.class)
class RequestDeleteMemberControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	RequestDeleteMemberController requestDeleteMemberController;

	@Mock
	ApplicationCommandBus applicationCommandBusMock;

	@Mock
	MessageSource messageSourceMock;

	@Mock
	ApplicationContext applicationContextMock;

	@Mock
	MockHttpSession mockHttpSession;

	@BeforeEach
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestDeleteMemberController).build();
	}

	@Nested
	public class requestDeleteMember_GET {
		@Test
		public void requestDeleteMember画面へ遷移する() throws Exception {
			mockMvc.perform(get("/requestDeleteMember")).andExpect(status().isOk())
					.andExpect(view().name("/reqmail/requestDeleteMember"));
		}
	}

	@Nested
	public class requestDeleteMember_POST {
		@Test
		public void 作成したRequestDeleteMemberCommandを引数にアプリケーションサービスを呼び出しユーザ退会要求完了画面を表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());

			// when
			ArgumentCaptor<RequestDeleteMemberCommand> argument = ArgumentCaptor
					.forClass(RequestDeleteMemberCommand.class);
			// then
			mockMvc.perform(
					post("/requestDeleteMember").param("mailAddress", "mailAddress").with(new RequestPostProcessor() {

						@Override
						public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
							request.setSession(mockHttpSession);
							return request;
						}
					})).andExpect(status().isOk()).andExpect(view().name("/reqmail/requestDeleteMemberComplete"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(RequestDeleteMemberCommand.class)));
			verifyNoMoreInteractions(applicationCommandBusMock);

			verify(mockHttpSession).setAttribute("mailAddress", "mailAddress");
		}

		@Test
		public void MemberNotFoundException発生時は入力したメールアドレスが登録されていないことを画面表示する() throws Exception {
			// given
			when(applicationContextMock.getBean(HotelReserveMessages.class)).thenReturn(new HotelReserveMessages());
			when(messageSourceMock.getMessage("MSGE1002", null, null))
					.thenReturn("入力したメールアドレスは登録されていません。別のメールアドレスを入力してください。");

			doThrow(new Exception(new MemberNotFoundException("入力したメールアドレスは登録されていません。別のメールアドレスを入力してください。")))
					.when(applicationCommandBusMock).dispatch(Mockito.any(RequestDeleteMemberCommand.class));

			// when
			ArgumentCaptor<RequestDeleteMemberCommand> argument = ArgumentCaptor
					.forClass(RequestDeleteMemberCommand.class);
			ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Object[]> argumentObject = ArgumentCaptor.forClass(Object[].class);
			ArgumentCaptor<Locale> argumentLocale = ArgumentCaptor.forClass(Locale.class);

			// then
			mockMvc.perform(
					post("/requestDeleteMember").param("mailAddress", "mailAddress").with(new RequestPostProcessor() {

						@Override
						public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
							request.setSession(mockHttpSession);
							return request;
						}
					})).andExpect(status().isOk()).andExpect(view().name("/reqmail/requestDeleteMember"));

			verify(applicationCommandBusMock).dispatch(argument.capture());
			assertThat(argument.getValue(), is(instanceOf(RequestDeleteMemberCommand.class)));

			verify(messageSourceMock).getMessage(argumentString.capture(), argumentObject.capture(),
					argumentLocale.capture());
			assertThat(argumentString.getValue(), is("MSGE1002"));

			verifyNoMoreInteractions(applicationCommandBusMock);
		}
	}
}
