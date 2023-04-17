package com.as1.pwmanager.service;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;

import java.util.Collection;
import java.util.Optional;

public interface ILoginService {

    Login save(String loginName, Host host);

    Collection<Login> getAllLoginsByHost(Host host);

    Optional<Login> getLoginById(Host host);

    void resetHostRelationship(Host host);
}

