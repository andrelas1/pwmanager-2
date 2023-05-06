package com.as1.pwmanager.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateCreated;

    /**
     * FetchType helps with (bi)directional relations between models in Spring - it's like there is some race condition somehow and that
     * will fix the issue.
     * <p>
     * TODO: check the use of CascadeType.REMOVE with deletion -> better to not use FetchType.EAGER
     */
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Login> logins;

    public Host() {
    }

    public Host(String name, LocalDate dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.logins = Set.of();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(id, host.id) && Objects.equals(name, host.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
}
