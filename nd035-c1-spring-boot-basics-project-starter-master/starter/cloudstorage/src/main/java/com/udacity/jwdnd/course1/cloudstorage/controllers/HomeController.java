package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  private final NoteService noteService;
  private final CredentialService credentialService;
  private final FileService fileService;

  public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService) {
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.fileService = fileService;
  }

  @GetMapping("/")
  public String getHome(Model model) {
    // TODO stop hardcoding the userid
    model.addAttribute("allFiles", fileService.getAllFiles(1));
    model.addAttribute("allNotes", noteService.getAllNotesForUser(1));
    model.addAttribute("allCredentials", credentialService.getAllCredentialsForUser(1));
    System.out.println(model);
    return "home";
  }


}
