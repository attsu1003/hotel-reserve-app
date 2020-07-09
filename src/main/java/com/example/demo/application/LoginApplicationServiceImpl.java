package com.example.demo.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.model.MemberModel;

@Service
public class LoginApplicationServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		MemberModel memberModel = memberRepository.getMember(userid);
		if (memberModel == null) {
			throw new UsernameNotFoundException(userid + "is not found");
		}
		return memberModel;
	}
	
//	@Bean
//    public PasswordEncoder passwordEncoder(){
//		return NoOpPasswordEncoder.getInstance();
//    }

}
