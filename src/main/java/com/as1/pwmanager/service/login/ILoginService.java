package com.as1.pwmanager.service.login;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;

import java.util.Collection;
import java.util.Optional;

public interface ILoginService {

    Login save(String loginName, String password, Host host);

    String hashPassword(String password);

    Collection<Login> getAllLogins();

    Collection<Login> getAllLoginsByHost(Host host);

    Optional<Login> getLoginById(Long id);

    void resetHostRelationship(Host host);

    void deleteById(Long id);
}

