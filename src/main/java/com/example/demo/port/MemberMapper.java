package com.example.demo.port;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.model.MemberModel;

@Mapper
public interface MemberMapper {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO member (name, password) VALUES (#{name}, #{passwd})")
	void insert(MemberModel memberModel);

	@Select("SELECT id, name, password FROM member WHERE name = #{username}")
	MemberModel select(String username);

	@Update("UPDATE member SET password = #{password} WHERE name = #{username}")
	boolean update(String password, String username);

	@Delete("DELETE FROM member WHERE name = #{username}")
	void delete(MemberModel memberModel);

}