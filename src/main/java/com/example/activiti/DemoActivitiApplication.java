package com.example.activiti;

import com.example.activiti.dao.ActivitiUserRepository;
import com.example.activiti.model.ActivitiUser;
import org.activiti.engine.RuntimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoActivitiApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ActivitiUserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            repository.deleteAll();
            repository.save(new ActivitiUser("scrynkaa@gmail.com",
                    passwordEncoder.encode("password"),
                    "ROLE_ADMIN"));

            System.out.println(repository
                    .findActivitiUserByUsername("scrynkaa@gmail.com")
                    .orElseThrow(() -> new UsernameNotFoundException("")));
        };
    }

    @Bean
    public CommandLineRunner process(RuntimeService runtimeService) {
        return args -> {
            runtimeService.startProcessInstanceByKey("nop").getDescription();
        };
    }
}
