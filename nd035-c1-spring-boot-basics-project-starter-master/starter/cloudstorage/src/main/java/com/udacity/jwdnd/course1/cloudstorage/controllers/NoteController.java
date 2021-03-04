package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

  private final NoteService noteService;
  private final UserService userService;

  public NoteController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @PostMapping("/notes")
  public String postNote(Authentication auth, @ModelAttribute("note") NoteModel note, Model model) {
    int userid = userService.getUser(auth.getName()).getUserid();
    note.setUserid(userid);
    String error = null;
    int rowsAdded = 0;
    System.out.println(note);
    if (note.getNoteid() == null) {
      rowsAdded = noteService.createNote(note);
    } else {
      rowsAdded = noteService.updateNote(note);
    }

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
