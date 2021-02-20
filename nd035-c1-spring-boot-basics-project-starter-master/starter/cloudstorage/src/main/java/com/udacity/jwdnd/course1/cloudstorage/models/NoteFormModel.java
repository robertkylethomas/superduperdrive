package com.udacity.jwdnd.course1.cloudstorage.models;

public class NoteFormModel {

  private String notetitle;
  private String notedescription;

  public NoteFormModel(String notetitle, String notedescription) {
    this.notetitle = notetitle;
    this.notedescription = notedescription;
  }

  public String getNoteTitle() {
    return notetitle;
  }

  public void setNoteTitle(String notetitle) {
    this.notetitle = notetitle;
  }

  public String getNoteDescription() {
    return notedescription;
  }

  public void setNoteDescription(String notedescription) {
    this.notedescription = notedescription;
  }

  @Override
  public String toString() {
    return "NoteFormModel{" +
      "notetitle='" + notetitle + '\'' +
      ", notedescription='" + notedescription + '\'' +
      '}';
  }
}
