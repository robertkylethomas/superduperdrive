package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CredentialService {
  private final CredentialMapper credentialMapper;

  public CredentialService(CredentialMapper credentialMapper) {
    this.credentialMapper = credentialMapper;
  }

  public ArrayList<CredentialModel> getAllCredentialsForUser(Integer userid) {
    return credentialMapper.getCredentialsForUser(userid);
  }

  public int deleteCredential(Integer credentialid, Integer userid) {
    return credentialMapper.deleteCredential(credentialid, userid);
  }

  public int updateCredential(CredentialModel credential) {
    System.out.println("=========CREDENTIAL=======================");
    System.out.println(credential);
    System.out.println("==========================================");
    return this.credentialMapper.updateCredentials(credential);
  }

  public int createCredential(CredentialModel credentialModel) {
    return this.credentialMapper.insertCredential(new CredentialModel(
      null,
      credentialModel.getUrl(),
      credentialModel.getUsername(),
      credentialModel.getKey(),
      credentialModel.getPassword(),
      credentialModel.getUserid()
    ));

  }

}
