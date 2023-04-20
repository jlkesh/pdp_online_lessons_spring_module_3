package uz.pdp.springadvanced;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@SpringBootApplication
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }
}


