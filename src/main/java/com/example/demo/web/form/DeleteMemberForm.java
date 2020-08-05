package com.example.demo.web.form;

import javax.validation.constraints.Size;

public class DeleteMemberForm {
	
	@Size(min = 8, max = 16)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
