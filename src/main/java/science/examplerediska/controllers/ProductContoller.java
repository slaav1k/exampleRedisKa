package science.examplerediska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import science.examplerediska.entities.Product;
import science.examplerediska.repositories.ProductRepository;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/products")
public class ProductContoller {

    @Autowired
    private final ProductRepository productRepository;

    public ProductContoller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
    }

    @GetMapping("/random")
    public Product getRandomProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }
        Random random = new Random();
        return products.get(random.nextInt(products.size()));
    }


}
