package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

  @GetMapping("/home")
  public String getHome(Model model) {
    // TODO stop hardcoding the userid
    model.addAttribute("allFiles", fileService.getAllFiles(1));
    model.addAttribute("allNotes", noteService.getAllNotesForUser(1));
    model.addAttribute("allCredentials", credentialService.getAllCredentialsForUser(1));
    return "home";
  }

  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
  public ModelAndView projectBase() {
    return new ModelAndView("redirect:/home");
  }
}
