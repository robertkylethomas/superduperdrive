//package com.udacity.jwdnd.course1.cloudstorage.mappers;
//
//import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//@Mapper
//public interface FileMapper {
//
//  @Select("SELECT * FROM FILES WHERE fileName = #{fileName} AND userid = #{userId}")
//  FileModel getUserFile(String fileName, Integer userId);
//
//  @Select("SELECT * FROM FILE WHERE userid = #{userId}")
//  List<FileModel> getAllFiles(Integer userId);
//
//  @Delete("DELETE FROM FILE WHERE fileid = #{fileId} and userid = #{userId}")
//  Integer deletedFile(int fileId,  int userid);
//
//  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
//    "VALUES (#{filename}, #{contenttype}, #{userid), #{filedata}")
//  @Options(useGeneratedKeys = true, keyProperty = "fileid")
//  Integer saveFile(FileModel file);
//
//
//}
