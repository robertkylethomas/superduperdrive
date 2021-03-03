package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
  private final UserService userService;

  public SignUpController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public String getSignUpPage(@ModelAttribute("user") UserModel user, Model model) {
    return "signup";
  }

  @PostMapping()
  public String postSignUpPage(@ModelAttribute("user") UserModel user, Model model) {

    String signupError = null;
    if (!userService.isUsernameAvailable(user.getUsername())) {
      signupError = "This username already exists";
    }

    if (signupError == null) {
      int rowsAdded = userService.createUser(user);
      if (rowsAdded < 0) {
        signupError = "There was an error signing up. Please try again";
      }
    }

    if (signupError == null) {
      model.addAttribute("signupSuccess", true);
    } else {
      model.addAttribute("signupError", signupError);
    }

    return "signup";
  }

}
