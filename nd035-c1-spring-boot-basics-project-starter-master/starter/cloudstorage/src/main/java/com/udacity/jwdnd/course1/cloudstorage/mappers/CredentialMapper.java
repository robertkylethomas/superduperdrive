package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialModel;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {
  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  ArrayList<CredentialModel> getCredentialsForUser(Integer user);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid  = #{credentialid} AND userid = #{userid}")
  int deleteCredential(Integer credentialid, Integer userid);

  @Update("update CREDENTIALS set url=#{url},username=#{username}, key=#{key}, password=#{password} where credentialid=#{credentialid}")
  int updateCredentials(CredentialModel credential);

  @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userid)" +
    "VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  int insertCredential(CredentialModel credentialModel);
}
