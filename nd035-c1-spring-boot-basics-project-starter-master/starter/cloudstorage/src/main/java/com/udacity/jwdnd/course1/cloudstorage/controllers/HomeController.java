package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  private final NoteService noteService;
  private final CredentialService credentialService;

  public HomeController(NoteService noteService, CredentialService credentialService) {
    this.noteService = noteService;
    this.credentialService = credentialService;
  }

  @GetMapping("/")
  public String getHome(Model model) {
    // TODO stop hardcoding the userid
    model.addAttribute("allNotes", noteService.getAllNotesForUser(1));
    model.addAttribute("allCredentials", credentialService.getAllCredentialsForUser(1));
    return "home";
  }


}
