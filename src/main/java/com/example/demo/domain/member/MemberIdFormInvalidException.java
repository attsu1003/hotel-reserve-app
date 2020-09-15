package com.example.demo.domain.member;

public class MemberIdFormInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public MemberIdFormInvalidException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public MemberIdFormInvalidException(String message) {
		super(message);
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}

}
