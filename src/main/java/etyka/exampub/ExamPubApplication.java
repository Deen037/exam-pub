package etyka.exampub;

import etyka.exampub.models.Role;
import etyka.exampub.models.User;
import etyka.exampub.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamPubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamPubApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            User bartender = new User();
            bartender.setUsername("bartender");
            bartender.setPassword("bartender");
            bartender.setRole(Role.valueOf("BARTENDER"));

            userService.save(bartender);

        };
    }
}
