package uz.pdp.springadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }

}
