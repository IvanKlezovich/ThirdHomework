//package org.example;
//
//import org.example.config.HibernateConfig;
//import org.example.config.SpringConfig;
//import org.example.repository.SingerRepository;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.GenericApplicationContext;
//
//public class HibernateTest {
//
//    @Test
//    public void test() {
//        GenericApplicationContext context =
//                new AnnotationConfigApplicationContext(
//                        SpringConfig.class);
//
//        var singerDao = context.getBean(SingerRepository.class);
//
//        System.out.println("---- Listing singers: ----");
//        singerDao.findAll().forEach(System.out::println);
//
//        context.close();
//    }
//}
