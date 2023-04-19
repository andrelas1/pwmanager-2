package com.as1.pwmanager.service.impl;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.persistence.repository.ILoginRepository;
import com.as1.pwmanager.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginServiceImpl implements ILoginService {

    private ILoginRepository loginRepository;

    public LoginServiceImpl(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login save(String name, Host host) {
        Login login = new Login(name, host);
        this.loginRepository.save(login);

        return login;
    }

    public Collection<Login> getAllLoginsByHost(Host host) {
        return this.loginRepository.findAllByHostName(host.getName());
    }

    public Optional<Login> getLoginById(Long id) {
        return this.loginRepository.findById(id);
    }

    public void resetHostRelationship(Host host) {
        Set<Login> logins = this.loginRepository.findAllByHostName(host.getName());
        logins.forEach(login -> login.setHost(null));

        this.loginRepository.saveAll(logins);
    }

    @Override
    public void deleteById(Long id) {
        this.loginRepository.deleteById(id);
    }

    public Collection<Login> getAllLogins() {
        return (Collection<Login>) this.loginRepository.findAll();
    }
}
