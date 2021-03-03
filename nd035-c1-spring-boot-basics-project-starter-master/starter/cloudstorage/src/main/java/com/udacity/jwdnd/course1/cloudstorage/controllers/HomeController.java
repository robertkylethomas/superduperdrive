package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  private final NoteService noteService;
  private final CredentialService credentialService;
  private final FileService fileService;
  private final UserService userService;

  public HomeController(
    NoteService noteService,
    CredentialService credentialService,
    FileService fileService,
    UserService userService) {
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.fileService = fileService;
    this.userService = userService;
  }

  @GetMapping("/home")
  public String getHome(Authentication auth, Model model) {
    int userId = userService.getUser(auth.getName()).getUserid();

    model.addAttribute("allFiles", fileService.getAllFiles(userId));
    model.addAttribute("allNotes", noteService.getAllNotesForUser(userId));
    model.addAttribute("allCredentials", credentialService.getAllCredentialsForUser(userId));
    return "home";
  }
}
