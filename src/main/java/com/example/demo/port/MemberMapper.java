package com.example.demo.port;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.model.MemberModel;

@Mapper
public interface MemberMapper {
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO member (name, password) VALUES (#{name}, #{passwd})")
	void insert(MemberModel memberModel);

	@Select("SELECT id, name, password FROM member WHERE name = #{username}")
	MemberModel select(String username);
}