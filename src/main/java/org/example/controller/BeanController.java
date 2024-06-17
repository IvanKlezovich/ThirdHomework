package org.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/monitoring")
public class BeanController {

    private final ApplicationContext context;

    public BeanController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/beans")
    public @ResponseBody List<String> beans() {
        return Arrays.asList(context.getBeanDefinitionNames());
    }
}
