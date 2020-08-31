package com.example.demo.domain.reserve;

import com.example.demo.domain.BaseException;

public class NoVacancyRoomException extends BaseException {

	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public NoVacancyRoomException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public NoVacancyRoomException(String message) {
		super(message);
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}
}
