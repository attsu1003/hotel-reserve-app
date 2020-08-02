package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HotelReserveMessages {

	private static final String MSG_TYPE_INFO = "info";
	private static final String MSG_TYPE_ERROR = "error";

	private List<Message> messages = new ArrayList<>();

	public List<Message> getMessages() {
		return messages;
	}

	public void addMessage(String code, String message) {
		messages.add(new Message(MSG_TYPE_INFO, code, message));
	}

	public void addErrorMessage(String code, String message) {
		messages.add(new Message(MSG_TYPE_ERROR, code, message));
	}

	public static class Message {
		public String type;
		public String code;
		public String value;

		public Message(String type, String code, String value) {
			this.type = type;
			this.code = code;
			this.value = value;
		}

	}

}
