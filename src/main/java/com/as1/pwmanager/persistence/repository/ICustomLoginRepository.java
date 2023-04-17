package com.as1.pwmanager.persistence.repository;

import com.as1.pwmanager.persistence.model.Login;

import java.util.Set;

public interface ICustomLoginRepository {

    public Set<Login> findAllByHostName(String hostName);
}
