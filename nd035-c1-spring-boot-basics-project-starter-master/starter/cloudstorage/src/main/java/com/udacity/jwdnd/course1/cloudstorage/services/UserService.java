package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UserModel;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
  private final HashService hashService;
  private final UserMapper userMapper;

  public UserService(HashService hashService, UserMapper userMapper) {
    this.hashService = hashService;
    this.userMapper = userMapper;
  }

  public boolean isUsernameAvailable(String username) {
    return userMapper.getUser(username) == null;
  }

  public UserModel getUser(String username) {
    return userMapper.getUser(username);
  }


  public int createUser(UserModel user) {
    String encodedSalt = getEncodedSalt();
    String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

    return userMapper.insert(new UserModel(
      null,
      user.getUsername(),
      encodedSalt,
      hashedPassword,
      user.getFirstname(),
      user.getLastname()));
  }

  public String getEncodedSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    return encodedSalt;
  }
}
