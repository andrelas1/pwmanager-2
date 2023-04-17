package com.as1.pwmanager.persistence.repository.impl;

import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.persistence.repository.ICustomLoginRepository;
import com.as1.pwmanager.persistence.repository.ILoginRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CustomImplLoginRepository implements ICustomLoginRepository {
    private ILoginRepository loginRepository;

    public CustomImplLoginRepository(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Set<Login> findAllByHostName(String hostName) {
        Set<Login> loginsSet = Set.of();

        for (Login login : this.loginRepository.findAll()) {
            if (login.getHost().getName() == hostName) {
                loginsSet.add(login);
            }
        }

        return loginsSet;
    }
}
