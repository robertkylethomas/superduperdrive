package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.models.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

  private final CredentialService credentialService;

  public CredentialController(CredentialService credentialService) {
    this.credentialService = credentialService;
  }

  // TODO dont hardcaode the user
  @PostMapping("/credential")
  public String postNote(@ModelAttribute("credential") CredentialModel credential, Model model) {
    // TODO remove hardcoded userid
    credential.setUserid(1);
    String error = null;
    int rowsAdded = 0;
    System.out.println(model);
    if (true) {
      rowsAdded = credentialService.createCredential(credential);
    } else {
      // TODO remove hardcoded userid
      rowsAdded = credentialService.updateCredential(credential, 1);
    }

    if (rowsAdded <= 0) {
      model.addAttribute("failure", "Note not uploaded");
    } else {
      model.addAttribute("success", true);
    }
    return "result";
  }

  // TODO dont hardcode the the userid
  @GetMapping("/credentials/delete/{id}")
  public String deleteNote(@PathVariable("id") int id, Model model) {
    String error = null;
    int rowsDeleted = credentialService.deleteCredential(id, 1);
    if (rowsDeleted <= 0) {
      model.addAttribute("failure", true);
    } else {
      model.addAttribute("success", true);
    }
    return "result";
  }
}
