package com.as1.pwmanager.web.controller;

import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.service.login.LoginService;
import com.as1.pwmanager.service.user.OwnerService;
import com.as1.pwmanager.web.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private LoginService loginService;
    private OwnerService ownerService;

    public LoginController(LoginService loginService, OwnerService ownerService) {
        this.loginService = loginService;
        this.ownerService = ownerService;
    }

    @GetMapping
    Collection<LoginDto> getLogins() {
        Collection<Login> foundLogins = this.loginService.getAllLogins();
        // TODO : is this the best way to do this?
        return foundLogins.stream().map(this::convertToLoginDto).collect(Collectors.toSet());

    }

    @GetMapping("/{id}")
    LoginDto getLogin(@PathVariable Long id) {
        Login foundLogin = this.loginService.getLoginById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "login not found"));

        return new LoginDto(foundLogin);
    }

    // TODO: should I use transactional here or in the service??
    @PostMapping
    Login createLogin(@RequestBody LoginDto loginDto) {
        Login login = this.convertToLogin(loginDto);
        Owner owner = this.ownerService.findById(loginDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login with not-found user"));
        return this.loginService.save(loginDto.getName(), loginDto.getPassword(), owner);
    }

    @DeleteMapping("/{id}")
    Login deleteLogin(@PathVariable Long id) {
        Login foundLogin = this.loginService.getLoginById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login not found"));
        this.loginService.deleteById(foundLogin.getId());

        return foundLogin;
    }

    private LoginDto convertToLoginDto(Login login) {
        return new LoginDto(login);
    }

    private Login convertToLogin(LoginDto loginDto) {
        Owner owner = this.ownerService.findById(loginDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new Login(loginDto.getName(), loginDto.getPassword(), owner);
    }
}
