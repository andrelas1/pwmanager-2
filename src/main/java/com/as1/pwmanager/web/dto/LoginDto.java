package com.as1.pwmanager.web.dto;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;
import jakarta.annotation.Nullable;
import org.springframework.lang.NonNull;

public class LoginDto {
    String name;
    @Nullable
    Long id;
    Host host;

    @NonNull
    String password;

    public LoginDto() {
    }

    public LoginDto(Login login) {
        this.name = login.getLoginName();
        this.id = login.getId();
        this.host = login.getHost();
        this.password = login.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
