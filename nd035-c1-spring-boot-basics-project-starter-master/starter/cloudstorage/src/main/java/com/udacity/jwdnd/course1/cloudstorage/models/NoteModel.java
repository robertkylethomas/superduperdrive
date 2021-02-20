package com.udacity.jwdnd.course1.cloudstorage.models;

public class NoteModel {
  private Integer noteid;
  private String notetitle;
  private String notedescription;
  private Integer userid;

  public NoteModel(Integer noteid, String notetitle, String notedescription, Integer userid) {
    this.noteid = noteid;
    this.notetitle = notetitle;
    this.notedescription = notedescription;
    this.userid = userid;
  }

  public Integer getNoteid() {
    return noteid;
  }

  public void setNoteid(Integer noteid) {
    this.noteid = noteid;
  }

  public String getNotetitle() {
    return notetitle;
  }

  public void setNotetitle(String notetitle) {
    this.notetitle = notetitle;
  }

  public String getNotedescription() {
    return notedescription;
  }

  public void setNotedescription(String notedescription) {
    this.notedescription = notedescription;
  }

  public Integer getUserid() {
    return 1;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  @Override
  public String toString() {
    return "NoteModel{" +
      "noteid=" + noteid +
      ", notetitle='" + notetitle + '\'' +
      ", notedescription='" + notedescription + '\'' +
      ", userid=" + userid +
      '}';
  }
}
