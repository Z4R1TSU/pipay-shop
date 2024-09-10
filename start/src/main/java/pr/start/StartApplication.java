package pr.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"pr", "pr.common", "pr.user", "pr.chat", "pr.online", "pr.physical", "pr.hotel"}) // 包扫描
@EnableJpaRepositories(basePackages = {"pr", "pr.common", "pr.user", "pr.chat", "pr.online", "pr.physical", "pr.hotel"})
@EntityScan(basePackages = {"pr", "pr.common", "pr.user", "pr.chat", "pr.online", "pr.physical", "pr.hotel"})
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
