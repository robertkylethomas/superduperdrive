package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

  private final FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public void createFile(MultipartFile multipartFile, Integer userid) {

    try {
      InputStream fis = multipartFile.getInputStream();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int i;
      byte[] data = new byte[1024];
      while ((i = fis.read(data, 0, data.length)) != -1) {
        byteArrayOutputStream.write(data, 0, i);
      }
      byteArrayOutputStream.flush();
      byte[] filedata = byteArrayOutputStream.toByteArray();

      String filename = multipartFile.getOriginalFilename();
      String contenttype = multipartFile.getContentType();
      String filesize = String.valueOf(multipartFile.getSize());

      // TODO dont hardcode user id
      fileMapper.insert(new FileModel(
        null,
        filename,
        contenttype,
        filesize,
        1,
        filedata));

    } catch (IOException ioe) {
      System.out.println(ioe);
    }
    System.out.println("I THINK IT WORKED");

  }

  // TODO change userid
  public List<FileModel> getAllFiles(Integer userid) {
    return fileMapper.getAllFiles(userid);
  }

  public Integer deleteFile(int fileid, int userid) {
    return fileMapper.deletedFile(fileid, userid);
  }

  public FileModel getSingleFile(Integer fileid, Integer userid) {
    return fileMapper.getSingleFiles(fileid, userid);
  }

}
