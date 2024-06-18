package org.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class for monitoring the beans in the Spring application context.
 * This class is annotated with @RestController and @RequestMapping to specify the base path for monitoring-related endpoints.
 * It uses the ApplicationContext to retrieve the names of all beans defined in the context.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/monitoring")
public class BeansController {

    /**
     * The ApplicationContext used to access the beans in the Spring application context.
     */
    private final ApplicationContext context;

    /**
     * Constructor that injects the ApplicationContext.
     *
     * @param context The ApplicationContext to be used by this controller.
     */
    public BeansController(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Handles GET requests to retrieve the names of all beans in the application context.
     *
     * @return A list of bean names.
     */
    @GetMapping("/beans")
    public @ResponseBody List<String> beans() {
        return Arrays.asList(context.getBeanDefinitionNames());
    }
}