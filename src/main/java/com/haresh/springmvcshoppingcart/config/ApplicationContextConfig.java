/**
 * Author: Haresh Shaha
 * Date: 21-11-2020
 */

package com.haresh.springmvcshoppingcart.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.haresh.springmvcshoppingcart.*")
@EnableTransactionManagement
@PropertySource("classpath:ds-hibernate-cfg.properties") // Load to Environment.
public class ApplicationContextConfig {
	// The Environment class serves as the property holder and stores all the
	// properties loaded by the @PropertySources
	@Autowired
	private Environment environment;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
//		// Load property in message/validator.properties
//		rb.setBasenames(new String[] { "messages/validator" });
//		return rb;
//	}
//
//	// Config for Upload.
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//
//		// Set Max Size...
//		// commonsMultipartResolver.setMaxUploadSize(...);
//
//		return commonsMultipartResolver;
//	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// See: ds-hibernate-cfg.properties
		dataSource.setDriverClassName(environment.getProperty("ds.database-driver"));
		dataSource.setUrl(environment.getProperty("ds.url"));
		dataSource.setUsername(environment.getProperty("ds.username"));
		dataSource.setPassword(environment.getProperty("ds.password"));

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
		Properties properties = new Properties();

		// See: ds-hibernate-cfg.properties
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", environment.getProperty("current_session_context_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setPackagesToScan(new String[] { "" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();

		SessionFactory sessionFactory = factoryBean.getObject();
		System.out.println("Session factory: " + sessionFactory);

		return sessionFactory;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

//	@Bean(name = "accountDAO")
//	public AccountDAO getApplicantDAO() {
//		return new AccountDAOImpl();
//	}
//
//	@Bean(name = "productDAO")
//	public ProductDAO getProductDAO() {
//		return new ProductDAOImpl();
//	}
//
//	@Bean(name = "orderDAO")
//	public OrderDAO getOrderDAO() {
//		return new OrderDAOImpl();
//	}
//
//	@Bean(name = "accountDAO")
//	public AccountDAO getAccountDAO() {
//		return new AccountDAOImpl();
//	}
}
