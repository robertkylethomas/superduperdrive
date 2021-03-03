package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }
  // TODO user cannot upload two files with the same name
  @PostMapping("/file")
  public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
    fileService.createFile(file, 1);
    model.addAttribute("success", true);
    return "/result";
  }

  @GetMapping("/file/delete/{id}")
  public String deleteFile(@PathVariable("id") Integer id, Model model) {
    // TODO CHANTGE USER NAE,
    fileService.deleteFile(id, 1);
    model.addAttribute("success", true);
    return "/result";
  }

  @GetMapping("/file/download/{id}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Integer id, Model model) {

    // TODO agian with the hardcoded ids
    FileModel fm = fileService.getSingleFile(id, 1);

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(fm.getContenttype()))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fm.getFilename() + "\"")
      .body(new ByteArrayResource(fm.getFiledata()));
  }
}
