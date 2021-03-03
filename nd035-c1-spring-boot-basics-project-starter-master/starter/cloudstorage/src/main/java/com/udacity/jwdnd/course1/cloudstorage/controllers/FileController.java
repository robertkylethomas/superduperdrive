package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class FileController {

  FileService fileService;
  UserService userService;

  public FileController(FileService fileService, UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @PostMapping("/file")
  public String handleFileUpload(Authentication auth, @RequestParam("file") MultipartFile file, Model model) throws IOException {
    int userId = userService.getUser(auth.getName()).getUserid();
    Boolean uploadedSuccesfully = fileService.createFile(file, userId);
    if (uploadedSuccesfully) {
      model.addAttribute("success", true);

    } else {
      model.addAttribute("failure", true);
    }
    return "/result";
  }

  @GetMapping("/file/delete/{id}")
  public String deleteFile(Authentication auth, @PathVariable("id") Integer id, Model model) {

    int userId = userService.getUser(auth.getName()).getUserid();
    fileService.deleteFile(id, 1);
    model.addAttribute("success", true);
    return "/result";
  }

  @GetMapping("/file/download/{id}")
  public ResponseEntity<ByteArrayResource> downloadFile(Authentication auth, @PathVariable("id") Integer id, Model model) {
    int userId = userService.getUser(auth.getName()).getUserid();
    FileModel fm = fileService.getSingleFile(id, userId);
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(fm.getContenttype()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fm.getFilename() + "\"")
      .body(new ByteArrayResource(fm.getFiledata()));
  }
}
