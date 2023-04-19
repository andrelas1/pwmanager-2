package com.as1.pwmanager.web.controller;

import com.as1.pwmanager.persistence.model.Login;
import com.as1.pwmanager.service.impl.HostServiceImpl;
import com.as1.pwmanager.service.impl.LoginServiceImpl;
import com.as1.pwmanager.web.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logins")
public class LoginController {

    private LoginServiceImpl loginService;
    private HostServiceImpl hostService;

    public LoginController(LoginServiceImpl loginService, HostServiceImpl hostService) {
        this.loginService = loginService;
        this.hostService = hostService;
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
        this.hostService.findById(loginDto.getHost().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login with not-found host"));
        return this.loginService.save(loginDto.getName(), loginDto.getHost());
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
        return new Login(loginDto.getName(), loginDto.getHost());
    }
}
