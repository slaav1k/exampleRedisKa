package science.examplerediska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExampleRedisKaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleRedisKaApplication.class, args);
    }

}
