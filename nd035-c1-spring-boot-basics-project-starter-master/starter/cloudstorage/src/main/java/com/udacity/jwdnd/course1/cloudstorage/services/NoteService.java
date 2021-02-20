package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
  private final NoteMapper noteMapper;


  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public NoteModel getNote(int noteid) {
    return noteMapper.getNote(noteid);
  }

  public ArrayList<NoteModel> getAllNotesForUser(Integer userid) {
    return noteMapper.getNotesForUser(userid);
  }

  public int deleteNote(Integer noteid) {
    return noteMapper.deleteNote(noteid);
  }

  public int createNote(NoteModel note) {
    System.out.println("================");
    System.out.println("This is the note");
    System.out.println(note);
    System.out.println("================");
    return noteMapper.insertNote(new NoteModel(
      null,
      note.getNotetitle(),
      note.getNotedescription(),
      note.getUserid()
    ));
  }
}
