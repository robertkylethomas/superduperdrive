package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {

  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  ArrayList<NoteModel> getNotesForUser(Integer userid);

  @Delete("DELETE FROM NOTES WHERE noteid = #{noteid} AND userid = #{userid}")
  int deleteNote(Integer noteid, Integer userid);

  @Update("UPDATE NOTES SET noteTitle = #{notetitle}, noteDescription = #{notedescription} where noteid = #{noteid} AND userid = #{userid}")
  int updateNote(NoteModel note, Integer userid);

  @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) " +
    "VALUES" +
    "( #{notetitle}, #{notedescription}, #{userid})"
  )
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  int insertNote(NoteModel note);


}
