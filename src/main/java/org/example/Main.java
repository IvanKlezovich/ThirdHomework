package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();

        context.refresh();

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    }
}
