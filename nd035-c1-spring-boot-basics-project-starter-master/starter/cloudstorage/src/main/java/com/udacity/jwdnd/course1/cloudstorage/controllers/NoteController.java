package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

  private final NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }


  @PostMapping("/notes")
  public String postNote(@ModelAttribute("note") NoteModel note, Model model) {
//    note.setNoteid(1);
//    note.setUserid(1);
    String error = null;
    System.out.println("===========================");
    System.out.println("current note");
    System.out.println(note);
    System.out.println(model);
    System.out.println("===========================");
    int rowsAdded = this.noteService.createNote(note);
    if (rowsAdded <= 0) {
      model.addAttribute("failure", "Note not uploaded");
    } else {
      model.addAttribute("success", true);
    }
    return "result";
  }

  @GetMapping("/notes/delete/{id}")
  public String deleteNote(@PathVariable("id") int id, Model model) {
    String error = null;
    int rowsDeleted = noteService.deleteNote(id);
    if (rowsDeleted <= 0) {
      model.addAttribute("failure", true);
    } else {
      model.addAttribute("success", true);
    }
    return "result";
  }

}
