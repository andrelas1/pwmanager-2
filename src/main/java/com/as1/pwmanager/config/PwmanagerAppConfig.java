package com.as1.pwmanager.config;

import com.as1.pwmanager.service.host.impl.HostServiceImpl;
import com.as1.pwmanager.service.login.impl.LoginServiceImpl;
import com.as1.pwmanager.service.setup.PopulateDbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


// define beans
// get props from application props
/*
This configuration class is present in case we want to override Spring's auto-configuration classes. I.e. inject
other beans instead of the default ones
 */
@Configuration
@ComponentScan(basePackages = {"com.as1.pwmanager.persistence", "com.as1.pwmanager.service", "com.as1.pwmanager.web"})
public class PwmanagerAppConfig {

    @Value("{spring.profiles.active}")
    private String profile;

    private HostServiceImpl hostService;
    private LoginServiceImpl loginService;

    public PwmanagerAppConfig(HostServiceImpl hostService, LoginServiceImpl loginService) {
        this.hostService = hostService;
        this.loginService = loginService;
    }

    @Bean
    @Profile("local")
    PopulateDbService populateDbService() {
        return new PopulateDbService(this.hostService, this.loginService);
    }
}
