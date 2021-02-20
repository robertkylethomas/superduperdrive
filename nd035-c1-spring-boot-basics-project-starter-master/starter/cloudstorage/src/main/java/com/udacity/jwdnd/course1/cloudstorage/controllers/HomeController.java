package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


  private final NoteService noteService;

  public HomeController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping("/")
  public String getHome(Model model) {

    List<NoteModel> allNotes = new ArrayList();
    NoteModel note1 = new NoteModel(1, "noteTitle1", "noteDesc1", 1);
    NoteModel note2 = new NoteModel(2, "noteTitle2", "noteDesc2", 1);
    NoteModel note3 = new NoteModel(3, "noteTitle3", "noteDesc3", 1);

    allNotes.add(note1);
    allNotes.add(note2);
    allNotes.add(note3);
    model.addAttribute("allNotes", allNotes);
//    model.addAttribute("allNotes", noteService.getAllNotesForUser(1));
    return "home";
  }


}
