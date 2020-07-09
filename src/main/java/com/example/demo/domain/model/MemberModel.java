package com.example.demo.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MemberModel implements UserDetails{
	private String id;
	private String name;
	private String passwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public MemberModel(String id, String name, String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return this.getPasswd();
	}
	@Override
	public String getUsername() {
		return this.getId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
