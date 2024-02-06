/**
 * 
 */
package sierra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 
 */
@Configuration
public class DataBase {
	

	@Bean(name = "mainDb")
	@Primary
	public EmbeddedDatabase dataSource1() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("mainDb").addScript("mainDb-schema.sql").build();

	}

	@Bean(name = "loggerDb")
	public EmbeddedDatabase dataSource2() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("loggerDb").addScript("loggerDb-schema.sql").build();

	}
}
