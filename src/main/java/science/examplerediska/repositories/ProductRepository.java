package science.examplerediska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import science.examplerediska.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT COUNT(p) FROM Product p")
    long countAllProducts();

    
}
