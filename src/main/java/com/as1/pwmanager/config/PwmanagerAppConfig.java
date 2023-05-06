package com.as1.pwmanager.config;

import com.as1.pwmanager.service.host.IHostService;
import com.as1.pwmanager.service.login.ILoginService;
import com.as1.pwmanager.setup.PopulateDbService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@ComponentScan(basePackages = {"com.as1.pwmanager.persistence", "com.as1.pwmanager.service", "com.as1.pwmanager.web"})
public class PwmanagerAppConfig {

    private IHostService hostService;
    private ILoginService loginService;

    public PwmanagerAppConfig(IHostService hostService, ILoginService loginService) {
        this.hostService = hostService;
        this.loginService = loginService;
    }

    @Bean
    @Profile("local")
    PopulateDbService populateDbService() {
        return new PopulateDbService(this.hostService, this.loginService);
    }
}
