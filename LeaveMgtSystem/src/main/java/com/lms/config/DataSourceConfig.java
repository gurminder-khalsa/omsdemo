/**
 * 
 */
package com.lms.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gurminder.singh
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.lms.repository")
@PropertySource("classpath:lms-db.properties")
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[] { "com.lms.db.model" });
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(addAdditionalProperties());
        return factoryBean;
	}
	
	 private Properties addAdditionalProperties() {
		 Properties properties = new Properties();
	     properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	     properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	     properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	     properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	     return properties;
	}

	@Bean
	 public DataSource dataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	     dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	     dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	     dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	     dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	     return dataSource;
	 }
	
	@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

}
