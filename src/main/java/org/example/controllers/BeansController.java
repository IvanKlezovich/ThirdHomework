package org.example.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/monitoring")
public class BeansController {

    private final ApplicationContext context;

    public BeansController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/")
    public @ResponseBody List<String> beans() {
        return Arrays.asList(context.getBeanDefinitionNames());
    }
}
