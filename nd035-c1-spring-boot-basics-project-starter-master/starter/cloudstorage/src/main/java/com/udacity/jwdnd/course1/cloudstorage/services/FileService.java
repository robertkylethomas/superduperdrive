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

  public boolean createFile(MultipartFile multipartFile, Integer userid) {

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

      int fileNames = fileMapper.findExistingFileNames(filename);
      System.out.println(fileNames);
      if (fileNames == 0) {
        fileMapper.insert(new FileModel(
          null,
          filename,
          contenttype,
          filesize,
          userid,
          filedata));
        return true;
      } else {
        return false;
      }

    } catch (IOException ioe) {
      return false;

    }


  }


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
