package com.accountingapi;

import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.RoleName;
import com.accountingapi.security.repository.RoleRepository;
import com.accountingapi.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AccountingApiApplication implements CommandLineRunner{

    @Autowired
    RoleRepository roleRepository;
    public static void main(String[] args)

    {
        SpringApplication.run(AccountingApiApplication.class, args);
    }

    @Override
    public void run(String... params) throws Exception {

        Role role_user = new Role(RoleName.ROLE_USER);
        Role role_admin =new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(role_user);
        roleRepository.save(role_admin);
        System.out.println(LogicService.getCurrentTimeUsingCalendar());

    }
}
