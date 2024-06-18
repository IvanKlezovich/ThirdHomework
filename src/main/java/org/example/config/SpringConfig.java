package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.repository.SingerRepository;
import org.example.repository.SingerRepository;
import org.example.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.example.config",
                               "org.example.controller",
                               "org.example.repository",
                               "org.example.service"})
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter
                = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        converters.add(converter);
    }

    @Bean
    public SingerService singerService(SingerRepository singerRepository){
        SingerService singerService = new SingerService(singerRepository);
        return singerService;
    }
}
