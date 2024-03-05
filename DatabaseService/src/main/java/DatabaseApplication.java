import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaRepositories("com.api.*")
@ComponentScan(basePackages = { "com.sapient.*" })
//@EntityScan("com.api.*")
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}


}
