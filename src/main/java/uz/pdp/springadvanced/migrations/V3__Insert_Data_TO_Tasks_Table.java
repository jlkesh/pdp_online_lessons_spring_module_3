package uz.pdp.springadvanced.migrations;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class V3__Insert_Data_TO_Tasks_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(connection);
        var query = "insert into tasks(name, description, label) values(?, ?, ?)";
        jdbcTemplate.update(query, "Read About Kafka", "Read Configuration", "Backend, DevOps");
    }
}
