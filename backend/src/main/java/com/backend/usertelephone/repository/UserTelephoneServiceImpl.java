package com.backend.usertelephone.repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.telephonetype.domain.TelephoneType;
import com.backend.user.domain.User;
import com.backend.usertelephone.application.UserTelephoneService;
import com.backend.usertelephone.domain.UserTelephone;
import com.backend.usertelephone.domain.UserTelephoneDto;
import com.backend.utils.enums.Status;

@Service
public class UserTelephoneServiceImpl implements UserTelephoneService {
  @Autowired
  private UserTelephoneRepository repository;

  @Override
  @Transactional(readOnly = true)
  public Set<UserTelephone> findAll() {
    Set<UserTelephone> telephones = new LinkedHashSet<>((List<UserTelephone>) repository.findAll());
    return telephones;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserTelephone> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public UserTelephone save(UserTelephoneDto userTelephone) {
    UserTelephone userTelephoneDb = new UserTelephone();
    userTelephoneDb.setNumber(userTelephone.getNumber());
    userTelephoneDb.setStatus(Status.ENABLED);

    User user = new User();
    user.setId(userTelephone.getUserId());
    userTelephoneDb.setUser(user);

    TelephoneType telephoneType = new TelephoneType();
    telephoneType.setId(userTelephone.getTelephoneTypeId());
    userTelephoneDb.setTelephoneType(telephoneType);

    return repository.save(userTelephoneDb);
  }

  @Override
  @Transactional
  public Optional<UserTelephone> update(Long id, UserTelephoneDto userTelephone) {
    Optional<UserTelephone> telephoneInstance = repository.findById(id);
    if (telephoneInstance.isPresent()) {
      UserTelephone userTelephoneDb = new UserTelephone();
      userTelephoneDb.setNumber(userTelephone.getNumber());

      User user = new User();
      user.setId(userTelephone.getUserId());
      userTelephoneDb.setUser(user);

      TelephoneType telephoneType = new TelephoneType();
      telephoneType.setId(userTelephone.getTelephoneTypeId());
      userTelephoneDb.setTelephoneType(telephoneType);

      return Optional.of(repository.save(userTelephoneDb));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<UserTelephone> delete(Long id) {
    Optional<UserTelephone> userTelefoneInstance = this.findById(id);
        if (userTelefoneInstance.isPresent()) {
            userTelefoneInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(userTelefoneInstance.orElseThrow()));
        }
            return Optional.empty();
  }

}
