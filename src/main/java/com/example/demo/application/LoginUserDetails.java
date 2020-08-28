package com.example.demo.application;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.model.MemberModel;

public class LoginUserDetails extends User {

	private static final long serialVersionUID = -6884241187482383607L;
	private MemberModel memberModel;

	public LoginUserDetails(MemberModel memberModel, Collection<GrantedAuthority> authorities) {
		super(memberModel.getUsername(), memberModel.getPassword(), true, true, true, true, authorities);

		this.memberModel = memberModel;
	}

	public MemberModel getMemberModel() {
		return memberModel;
	}

}