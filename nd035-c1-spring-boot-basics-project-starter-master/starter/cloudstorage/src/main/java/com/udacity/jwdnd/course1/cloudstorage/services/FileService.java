package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class FileService {

  public void doSomething(File file) {
    try {
      FileOutputStream fos = new FileOutputStream(file);
      System.out.println(fos);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
