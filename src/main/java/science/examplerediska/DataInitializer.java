package science.examplerediska;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import science.examplerediska.entities.Product;
import science.examplerediska.repositories.ProductRepository;

import java.util.Random;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(0, "Laptop", 1200.00, 3));
            productRepository.save(new Product(0, "Phone", 800.00, 10));
            productRepository.save(new Product(0, "Tablet", 500.00, 2));
            productRepository.save(new Product(777, "PC", 1500.00, 1));
            Random rand = new Random();
            for (int i = 1; i <= 1_000; i++) {
                double randomPrice = 10 + (3500 - 10) * rand.nextDouble();
                randomPrice = Math.round(randomPrice * 100.0) / 100.0;
                int randomTotalPrice = 1 + rand.nextInt(10_000);
                productRepository.save(new Product(0, "Product " + i, randomPrice, randomTotalPrice));
            }
        };
    }
}
