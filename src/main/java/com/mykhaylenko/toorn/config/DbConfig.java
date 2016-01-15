package com.mykhaylenko.toorn.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * DataBase configuration, we implement {@link EnvironmentAware} in cases
 * when we can't Autowired the {@link Environment}, this is work around.
 * Created by pavlo.mykhaylenko on 8/26/2015.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mykhaylenko.toorn.repository")
@PropertySource("classpath:database.properties")
public class DbConfig implements EnvironmentAware {

    private final static String PROP_DATABASE_DRIVER = "db.driver";

    private final static String PROP_DATABASE_URL = "db.url";

    private final static String PROP_DATABASE_USERNAME = "db.username";

    private final static String PROP_DATABASE_PASSWORD = "db.password";

    private final static String PROP_HIBERNATE_DIALECT = "hibernate.dialect";

    private final static String PROP_HIBERNATE_PACKAGES = "hibernate.packages.to.scan";

    private final static String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    private final static String PROP_HIBERNATE_HB2DDl_AUTO = "hibernate.hbm2ddl.auto";

    private final static String PROP_HIBERNATE_LAZY_LOAD = "hibernate.enable_lazy_load_no_trans";

    private final static String PROP_HIBERNATE_CONNECTION_ENCODING = "hibernate.connection.characterEncoding";

    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty(PROP_DATABASE_DRIVER));
        ds.setUrl(environment.getProperty(PROP_DATABASE_URL));
        ds.setUsername(environment.getProperty(PROP_DATABASE_USERNAME));
        ds.setPassword(environment.getProperty(PROP_DATABASE_PASSWORD));
        return ds;
    }

    //    Hibernate configurations

    /**
     * Configuration of Hibernate LocalSessionFactoryBean.
     * Note: do not use in production, because this bean creates only one connection, instead use
     * pooled connection, for instance, c3po
     *
     * @param dataSource
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(environment.getProperty(PROP_HIBERNATE_PACKAGES));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    //    JPA configuration
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan(environment.getProperty(PROP_HIBERNATE_PACKAGES));
        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(environment.getProperty(PROP_HIBERNATE_DIALECT));
        return adapter;
    }

    @Bean// can be used insted of HibernateTransactionManager
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor postProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROP_HIBERNATE_DIALECT, environment.getProperty(PROP_HIBERNATE_DIALECT));
        properties.setProperty(PROP_HIBERNATE_SHOW_SQL, environment.getProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.setProperty(PROP_HIBERNATE_HB2DDl_AUTO, environment.getProperty(PROP_HIBERNATE_HB2DDl_AUTO));
        properties.setProperty(PROP_HIBERNATE_LAZY_LOAD, environment.getProperty(PROP_HIBERNATE_LAZY_LOAD));
        properties.setProperty(PROP_HIBERNATE_CONNECTION_ENCODING, environment.getProperty(PROP_HIBERNATE_CONNECTION_ENCODING));
        return properties;
    }
}
