package com.example.demo.port;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.model.MemberModel;

@Mapper
public interface MemberMapper {

	@Insert("INSERT INTO member (id, password) VALUES (#{memberId}, #{password})")
	void insert(Member member);

	@Select("SELECT id, password FROM member WHERE id = #{id}")
	MemberModel select(String id);

	@Update("UPDATE member SET password = #{password} WHERE id = #{id}")
	boolean update(String password, String id);

	@Delete("DELETE FROM member WHERE id = #{id}")
	void delete(MemberModel memberModel);

}