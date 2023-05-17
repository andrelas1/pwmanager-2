package com.as1.pwmanager.persistence.repository;

import com.as1.pwmanager.persistence.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long>{

    Owner findByUsername(String username);
}
