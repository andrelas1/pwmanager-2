package com.as1.pwmanager.web.dto;

import com.as1.pwmanager.persistence.model.Login;
import jakarta.annotation.Nullable;
import org.springframework.lang.NonNull;

public class LoginDto {
    String name;
    @Nullable
    Long id;

    @NonNull
    String hostname;

    @NonNull
    String password;

    Long userId;

    public LoginDto() {
    }

    public LoginDto(Login login) {
        this.name = login.getLoginName();
        this.id = login.getId();
        this.userId = login.getUser().getId();
        this.password = login.getPassword();
    }

    @NonNull
    public String getHostname() {
        return hostname;
    }

    public void setHostname(@NonNull String hostname) {
        this.hostname = hostname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
