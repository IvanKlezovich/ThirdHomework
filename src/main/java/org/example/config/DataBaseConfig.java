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

/**
 * Configuration class for setting up the database connection.
 * This class reads properties from 'database.properties' file and configures a DataSource bean.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DataBaseConfig {

    /**
     * The Environment object used to access properties.
     */
    Environment environment;

    /**
     * Logger for logging messages related to database configuration.
     */
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

    /**
     * Constructor that injects the Environment object.
     *
     * @param environment The Environment object to be used for accessing properties.
     */
    public DataBaseConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Creates and returns a DataSource bean.
     * The DataSource is configured using properties from the 'database.properties' file.
     *
     * @return A DataSource instance, or null if an exception occurs during configuration.
     */
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
