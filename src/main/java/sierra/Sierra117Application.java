package sierra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;

@SpringBootApplication(exclude = H2ConsoleAutoConfiguration.class)
public class Sierra117Application {

	public static void main(String[] args) {
		SpringApplication.run(Sierra117Application.class, args);
	}

}
