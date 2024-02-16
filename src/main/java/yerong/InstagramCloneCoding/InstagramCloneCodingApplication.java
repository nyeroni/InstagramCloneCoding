package yerong.InstagramCloneCoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InstagramCloneCodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramCloneCodingApplication.class, args);
	}

}
