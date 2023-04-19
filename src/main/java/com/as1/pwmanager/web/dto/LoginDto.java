package com.as1.pwmanager.web.dto;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;
import jakarta.annotation.Nullable;

public class LoginDto {
    String name;
    @Nullable
    Long id;
    Host host;

    public LoginDto() {
    }

    public LoginDto(Login login) {
        this.name = login.getLoginName();
        this.id = login.getId();
        this.host = login.getHost();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
