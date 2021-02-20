package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("SELECT * FROM USERS WHERE username = #{username}")
  UserModel getUser(String username);

  @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname)" +
    " VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
  @Options(useGeneratedKeys = true, keyProperty = "userid")
  int insert(UserModel user);

}
