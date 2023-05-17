package com.as1.pwmanager.service.login.impl;

import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.persistence.repository.LoginRepository;
import com.as1.pwmanager.service.login.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private LoginRepository loginRepository;
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Login save(String loginName, String loginPassword, Owner owner) {
        String encodedPw = this.passwordEncoder.encode(loginPassword);
        Login login = new Login(loginName, encodedPw, owner);

        this.loginRepository.save(login);

        return login;
    }

    @Override
    public String hashPassword(String password) {
        return password;
    }

    public Optional<Login> getLoginById(Long id) {
        return this.loginRepository.findById(id);
    }

    public void resetHostRelationship(Owner owner) {

    }

    @Override
    public void deleteById(Long id) {
        this.loginRepository.deleteById(id);
    }

    public Collection<Login> getAllLogins() {

        Collection<Login> logins = (Collection<Login>) this.loginRepository.findAll();

        return logins;
    }

    @Override
    public Collection<Login> getAllLoginsByHostName(Owner owner, String hostname) {
        return null;
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
