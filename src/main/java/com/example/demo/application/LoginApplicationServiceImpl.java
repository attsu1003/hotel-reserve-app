package com.example.demo.application;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.model.MemberModel;

@Service
public class LoginApplicationServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// DBからユーザ情報を取得。
		MemberModel memberModel = Optional.ofNullable(memberRepository.getMember(username))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		return new LoginUserDetails(memberModel, getAuthorities(memberModel));
	}

	/**
	 * 認証が通った時にこのユーザに与える権限の範囲を設定する。
	 * 
	 * @param account DBから取得したユーザ情報。
	 * @return 権限の範囲のリスト。
	 */
	private Collection<GrantedAuthority> getAuthorities(MemberModel memberModel) {
		// 認証が通った時にユーザに与える権限の範囲を設定する。
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}