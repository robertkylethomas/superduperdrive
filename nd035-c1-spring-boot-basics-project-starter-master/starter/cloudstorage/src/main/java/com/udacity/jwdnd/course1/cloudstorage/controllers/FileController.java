package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class FileController {

  @PostMapping("/file")
  public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
    String fileName = file.getOriginalFilename();
    byte[] b = file.getInputStream().readAllBytes();
    String contentType = file.getContentType();
    Long size = file.getSize();

    FileService fs = new FileService();
//    fs.doSomething(file);

    try {
      file.transferTo(new File("C:\\uploads\\" + fileName));
      System.out.println("=========================");
      System.out.println(fileName);
      System.out.println(b);
      System.out.println("=========================");
    } catch (Exception e) {
      System.out.println(e);
      model.addAttribute("failure", "Note not uploaded");
      return "result";
    }
    model.addAttribute("success", true);
    return "/result";
  }
}
