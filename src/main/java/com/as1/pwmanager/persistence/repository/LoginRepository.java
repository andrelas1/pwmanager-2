package com.as1.pwmanager.persistence.repository;


import com.as1.pwmanager.persistence.model.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long>{
}
