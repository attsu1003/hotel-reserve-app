package com.example.demo.port;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.model.MemberModel;

@Mapper
public interface MemberMapper {
	
	 @Insert("INSERT INTO member (id, name, passwd) VALUES (#{id}, #{name}, #{passwd})")
	 void insert(MemberModel memberModel);
	
	@Select("SELECT id, name, passwd FROM member WHERE id = #{memberId}")
	MemberModel select(String memberId);
}
