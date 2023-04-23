package com.as1.pwmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: only populate the db in the dev environment with a configuration profile
@SpringBootApplication
public class PwmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwmanagerApplication.class, args);
    }

}
