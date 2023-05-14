package com.as1.pwmanager.service.login.impl;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.persistence.repository.ILoginRepository;
import com.as1.pwmanager.service.login.ILoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginServiceImpl implements ILoginService {

    private ILoginRepository loginRepository;
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(ILoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Login save(String name, String password, Host host) {
        String encodedPw = this.passwordEncoder.encode(password);
        Login login = new Login(name, encodedPw, host);

        this.loginRepository.save(login);

        return login;
    }

    @Override
    public String hashPassword(String password) {
        return password;
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

    private byte[] genRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }


    private byte[] genHashedPassword(String rawPassword) {
        byte[] salt = this.genRandomSalt();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
