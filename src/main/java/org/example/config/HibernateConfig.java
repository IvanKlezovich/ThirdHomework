package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.repository")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {

    public final DataSource dataSource;
    public final Environment environment;

    public HibernateConfig(DataSource dataSource,
                           Environment environment){
        this.dataSource = dataSource;
        this.environment = environment;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var em = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        em.setDataSource(dataSource);
        em.setPackagesToScan("org.example.entity");
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));

        return properties;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
