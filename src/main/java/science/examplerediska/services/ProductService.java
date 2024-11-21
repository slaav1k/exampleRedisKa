package science.examplerediska.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import science.examplerediska.entities.Product;
import science.examplerediska.entities.ProductDTO;
import science.examplerediska.entities.Review;
import science.examplerediska.repositories.ProductRepository;

import java.util.Random;



@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private JedisPool jedisPool;
    private ObjectMapper mapper;
    private static final int TTL = 10;

    public ProductService(ProductRepository productRepository, ObjectMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    public ProductDTO getProductById(int id) throws InterruptedException {
        Thread.sleep(200);
        return productRepository.findById(id)
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getReviews().stream()
                                .mapToDouble(Review::getRating)
                                .average().orElse(0)
                )).orElse(null);
    }

    public ProductDTO getProductByIdRedisCached(int id) {
        try (Jedis jedis = jedisPool.getResource()){
            String key = "product:%d".formatted(id);
            String raw = jedis.get(key);
            if (raw != null) {
                return mapper.readValue(raw, ProductDTO.class);
            }
            var productDTO = getProductById(id);
            if (productDTO == null) {
                throw new ResourceNotFoundException();
            }


            jedis.setex(key, TTL, mapper.writeValueAsString(productDTO));
            return productDTO;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not get product with id " + id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDTO getProductRandom() throws InterruptedException {
        long countProducts = productRepository.countAllProducts();
        int randomId = new Random().nextInt(1, (int) countProducts);
//        return getProductById(randomId);
        return getProductByIdRedisCached(randomId);
    }
}


