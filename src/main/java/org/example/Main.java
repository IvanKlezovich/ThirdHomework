package org.example;

import org.example.config.SpringConfig;
import org.example.repository.SingerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        var singerDao = context.getBean(SingerRepository.class);

        logger.info("---- Listing singers: ----");
        singerDao.findAll().forEach(s -> logger.info(s.toString()));

        context.close();
    }
}
