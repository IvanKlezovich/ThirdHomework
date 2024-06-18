//package org.example.config;
//
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.*;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
////@EnableJpaRepositories("org.example.repository")
//@EnableTransactionManagement
//@PropertySource("classpath:hibernate.properties")
//@Import(DataBaseConfig.class)
//public class HibernateConfig {
//
//    private final Logger logger =
//            LoggerFactory.getLogger(HibernateConfig.class);
//    DataSource dataSource;
//    Environment environment;
//
//    public HibernateConfig(DataSource dataSource,
//                           Environment environment) {
//        this.dataSource = dataSource;
//        this.environment = environment;
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//
//        hibernateProperties.setProperty("hibernate.dialect",
//                environment.getProperty("hibernate.dialect"));
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.format_sql", "true");
//        hibernateProperties.setProperty("hibernate.current_session_context_class",
//                environment.getProperty("hibernate.current_session_context_class"));
//
//        return hibernateProperties;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory =
//                new LocalSessionFactoryBean();
//
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan("com.example.entities");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        var manager = new HibernateTransactionManager();
//
//        manager.setSessionFactory(sessionFactory().getObject());
//
//        return manager;
//        //return new HibernateTransactionManager(sessionFactory());
//    }
//}
