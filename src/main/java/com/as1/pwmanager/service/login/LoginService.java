package com.as1.pwmanager.service.login;

import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.persistence.model.Login;

import java.util.Collection;
import java.util.Optional;

public interface LoginService {

    Login save(String loginName, String loginPassword, Owner owner);

    String hashPassword(String password);

    Collection<Login> getAllLogins();

    Collection<Login> getAllLoginsByHostName(Owner owner, String hostname);

    Optional<Login> getLoginById(Long id);

    void resetHostRelationship(Owner owner);

    void deleteById(Long id);
}

