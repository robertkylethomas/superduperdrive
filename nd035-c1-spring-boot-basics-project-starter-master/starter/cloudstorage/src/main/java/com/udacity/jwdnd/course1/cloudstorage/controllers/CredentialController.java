package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.models.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

  private final CredentialService credentialService;
  private final HashService hashService;
  private final UserService userService;

  public CredentialController(CredentialService credentialService, HashService hashService, UserService userService) {
    this.credentialService = credentialService;
    this.hashService = hashService;
    this.userService = userService;
  }

  // TODO dont hardcaode the user
  @PostMapping("/credential")
  public String postNote(CredentialModel credential, Model model) {
    // TODO remove hardcoded userid
    credential.setUserid(1);
    String error = null;
    int rowsAdded = 0;
    credential.setKey(this.hashService.getHashedValue(credential.getPassword(), this.userService.getEncodedSalt()));
    System.out.println(credential);
    if (credential.getCredentialid() == null) {
      rowsAdded = credentialService.createCredential(credential);
    } else {
      // TODO remove hardcoded userid
     rowsAdded = credentialService.updateCredential(credential);
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
