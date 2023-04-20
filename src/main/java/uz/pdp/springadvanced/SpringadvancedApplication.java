package uz.pdp.springadvanced;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }

}
