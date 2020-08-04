package com.example.demo.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.MemberModel;
import com.example.demo.port.MemberMapper;

@Repository
public class MemberRepository {

	@Autowired
	MemberMapper memberMapper;

	public MemberModel getMember(String username) {
		return memberMapper.select(username);
	}

	public void createMember(MemberModel memberModel) {
		memberMapper.insert(memberModel);
	}
	
	public void deleteMember(MemberModel memberModel) {
		memberMapper.delete(memberModel);
	}

	public boolean updatePassword(String password, String username) {
		return memberMapper.update(password, username);
	}
}