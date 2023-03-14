package MuDuck.MuDuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MuDuckApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuDuckApplication.class, args);
    }

}
