/**
 * 
 */
package sierra.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class JpaConfig {

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("sierra.model.entity");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(this.getProperties());

		return entityManagerFactory;
	}
	/*
	@Bean(name = "loggerDbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean loggerDbEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("loggerDb") DataSource dataSource) {
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.default_schema", "loggerDb");

		return builder.dataSource(dataSource).packages("sierra.model.entity").persistenceUnit("loggerDb")
				.properties(properties).build();
	}*/

	@Bean
	public SharedEntityManagerBean entityManager(EntityManagerFactory entityManagerFactory) {
		SharedEntityManagerBean entityManager = new SharedEntityManagerBean();
		entityManager.setEntityManagerFactory(entityManagerFactory);
		return entityManager;
	}

	private Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("spring.jpa.properties.hibernate.jdbc.time_zone", "UTC");
		properties.setProperty("spring.h2.console.enabled", "true");
		properties.setProperty("spring.h2.console.path", "/h2");

		return properties;
	}
}