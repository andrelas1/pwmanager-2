package com.as1.pwmanager.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private LocalDate dateCreated;

    /**
     * FetchType helps with (bi)directional relations between models in Spring - it's like there is some race condition somehow and that
     * will fix the issue.
     */
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Login> logins;

    public Owner() {
    }

    public Owner(String username, String password, LocalDate dateCreated) {
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
        this.logins = Set.of();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", logins=" + logins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(username, owner.username) && Objects.equals(password, owner.password) && Objects.equals(dateCreated, owner.dateCreated) && Objects.equals(logins, owner.logins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, dateCreated, logins);
    }
}
