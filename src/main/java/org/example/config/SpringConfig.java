package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
/**
 * Spring configuration class for the application.
 * This class is responsible for configuring various beans and components required for the application.
 * It enables Spring MVC, scans for components in specified packages, and configures message converters.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.example.controller",
        "org.example.service",
        "org.example.repository",
        "org.example.config"})
public class SpringConfig implements WebMvcConfigurer {

    /**
     * Configures the message converters for the application.
     * This method adds a MappingJackson2HttpMessageConverter to the list of converters,
     * which is used to convert JSON data to Java objects and vice versa.
     *
     * @param converters The list of HttpMessageConverters to be configured.
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter
                = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        converters.add(converter);
    }

    /**
     * Creates and returns a ModelMapper bean.
     * The ModelMapper is used to map between Java objects and DTOs (Data Transfer Objects).
     *
     * @return A ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Creates and returns a JdbcTemplate bean.
     * The JdbcTemplate is used to execute SQL queries and updates against a relational database.
     *
     * @param dataSource The DataSource to be used by the JdbcTemplate.
     * @return A JdbcTemplate instance.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
