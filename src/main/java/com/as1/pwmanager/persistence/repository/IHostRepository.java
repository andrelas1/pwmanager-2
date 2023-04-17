package com.as1.pwmanager.persistence.repository;

import com.as1.pwmanager.persistence.model.Host;
import org.springframework.data.repository.CrudRepository;

public interface IHostRepository extends CrudRepository<Host, Long> {
    Iterable<Host> findByName(String name);
}
