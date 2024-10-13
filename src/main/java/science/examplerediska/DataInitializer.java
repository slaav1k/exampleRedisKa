package science.examplerediska;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import science.examplerediska.entities.Product;
import science.examplerediska.entities.Review;
import science.examplerediska.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Random;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            Random rand = new Random();

            // Генерация случайных продуктов и отзывов
            for (int i = 1; i <= 100; i++) {
                double randomPrice = 10 + (3500 - 10) * rand.nextDouble();
                randomPrice = Math.round(randomPrice * 100.0) / 100.0;

                Product product = new Product(0, "Product " + i, randomPrice, new ArrayList<>());

                // Генерация случайного количества отзывов (1-5)
                int reviewCount = 20 + rand.nextInt(100);
                for (int j = 0; j < reviewCount; j++) {
                    int randomRating = 1 + rand.nextInt(5); // Рейтинг от 1 до 5
                    String randomComment = "This is a review for Product " + i;

                    Review review = new Review(0, product, randomRating, randomComment);
                    product.getReviews().add(review);  // Добавляем отзыв к продукту
                }

                // Сохранение продукта и отзывов в БД
                productRepository.save(product);
            }
        };
    }
}
