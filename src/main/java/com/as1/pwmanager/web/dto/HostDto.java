package com.as1.pwmanager.web.dto;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.model.Login;

import java.util.Set;

public class HostDto {

    public String name;

    public Long id;

    public Set<Login> logins;

    public HostDto(Host host) {
        this.name = host.getName();
        this.id = host.getId();
        this.logins = host.getLogins();
    }
}
