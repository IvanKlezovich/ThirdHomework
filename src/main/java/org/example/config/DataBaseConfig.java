package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:database.properties")
public class DataBaseConfig {
    Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

    public DataBaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {

        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("database.driver")));
            dataSource.setUrl(environment.getProperty("database.url"));
            dataSource.setUsername(environment.getProperty("database.username"));
            dataSource.setPassword(environment.getProperty("database.password"));

            return dataSource;
        }catch (Exception e){
            logger.info("DBCP DataSource bean cannot be created!", e);
            return null;
        }
    }
}
