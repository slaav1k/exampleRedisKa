package science.examplerediska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import science.examplerediska.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

