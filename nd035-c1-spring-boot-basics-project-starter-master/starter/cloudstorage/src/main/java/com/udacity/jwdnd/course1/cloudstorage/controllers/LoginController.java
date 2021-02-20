package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  private final UserService userService;
  Logger logger = LoggerFactory.getLogger(LoginController.class);

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @Override
  public String toString() {
    return "LoginController{" +
      "userService=" + userService +
      '}';
  }

}
