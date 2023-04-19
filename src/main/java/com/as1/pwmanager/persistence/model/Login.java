package com.as1.pwmanager.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String loginName;

    // TODO: Need to fix this relation - things don't look good here
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "host_id")
    private Host host;

    public Login() {
    }

    public Login(String loginName, Host host) {
        this.loginName = loginName;
        this.host = host;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", host=" + host +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(id, login.id) && Objects.equals(loginName, login.loginName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
