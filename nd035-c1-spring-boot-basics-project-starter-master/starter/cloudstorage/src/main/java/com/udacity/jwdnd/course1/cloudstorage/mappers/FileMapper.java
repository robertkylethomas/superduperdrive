package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<FileModel> getAllFiles(Integer userid);

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid} AND userid = #{userid}")
  FileModel getSingleFiles(Integer fileid, Integer userid);

  @Delete("DELETE FROM FILES WHERE fileid = #{fileid} and userid = #{userid}")
  Integer deletedFile(int fileid, int userid);

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
    "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
  @Options(useGeneratedKeys = true, keyProperty = "fileid")
  int insert(FileModel file);


}
