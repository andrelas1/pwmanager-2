package com.as1.pwmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// TODO: only populate the db in the dev environment with a configuration profile
@SpringBootApplication
@ComponentScan(basePackages = {"com.as1.pwmanager.config"})
public class PwmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwmanagerApplication.class, args);
    }

}
