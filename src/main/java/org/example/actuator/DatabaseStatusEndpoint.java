package org.example.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "databases")
@ConditionalOnEnabledHealthIndicator("databases")
public class DatabaseStatusEndpoint {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseStatusEndpoint(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(jdbcTemplate.getDataSource());
        this.jdbcTemplate.setQueryTimeout(5);
    }

    @ReadOperation
    public Map<String, Status> databases() {
      return Map.of("postgresql", postgresqlStatus());
    }

    private Status postgresqlStatus(){
      try {
        Long result = this.jdbcTemplate.queryForObject("SELECT 1", Long.class);
        return result != null && result > 0 ? Status.UP : Status.DOWN;
      } catch (Exception e) {
        return Status.DOWN;
      }
    }
}
