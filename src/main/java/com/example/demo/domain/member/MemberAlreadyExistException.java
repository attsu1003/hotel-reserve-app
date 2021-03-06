package com.example.demo.domain.member;

import com.example.demo.domain.BaseException;

public class MemberAlreadyExistException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public MemberAlreadyExistException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public MemberAlreadyExistException(String message) {
		super(message);
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}

}
