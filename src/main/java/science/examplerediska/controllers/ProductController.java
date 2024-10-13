package science.examplerediska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import science.examplerediska.entities.ProductDTO;
import science.examplerediska.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final int TTL = 10;
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/random")
    public ProductDTO getProductRandom() {
        return productService.getProductRandom();
    }

//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable int id) {
//        return productRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Product with id " + id + " not found")
//        );
//    }

//    @GetMapping("/{id}")
//    public ProductDTO getProductByIdRedis(@PathVariable int id) {
//        try (Jedis jedis = jedisPool.getResource()){
//            String key = "product:%d".formatted(id);
//            String raw = jedis.get(key);
//            if (raw != null) {
//                return mapper.readValue(raw, ProductDTO.class);
//            }
//            var product = productRepository.findById(id).orElseThrow(
//                    () -> new ResourceNotFoundException("Product with id " + id + " not found")
//            );
//
//            ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);
//            productDTO.setTotalCost(product.calculateTotalCost());
//
//            jedis.setex(key, TTL, mapper.writeValueAsString(productDTO));
//            return productDTO;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Could not get product with id " + id);
//        }
//    }

//    @GetMapping("/random")
//    public Product getRandomProduct() {
//        List<Product> products = productRepository.findAll();
//        if (products.isEmpty()) {
//            throw new ResourceNotFoundException("No products found");
//        }
//        Random random = new Random();
//        return products.get(random.nextInt(products.size()));
//    }

//    @GetMapping("/random")
//    public Product getRandomProductRedis() {
//        List<Product> products = productRepository.findAll();
//        if (products.isEmpty()) {
//            throw new ResourceNotFoundException("No products found");
//        }
//        Random random = new Random();
//        int randomId = random.nextInt(products.size());
//        try (Jedis jedis = jedisPool.getResource()){
//            String key = "product:%d".formatted(randomId);
//            String raw = jedis.get(key);
//            if (raw != null) {
//                return mapper.readValue(raw, Product.class);
//            }
//            Product product = products.get(randomId);
//            jedis.setex(key, TTL, mapper.writeValueAsString(product));
//            return product;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Could not get product with id " + randomId);
//        }
//    }



//    @GetMapping("/random")
//    public ProductDTO getRandomProductRedis() {
//        long count = productRepository.countAllProducts();
//        if (count == 0) {
//            throw new ResourceNotFoundException("No products found");
//        }
//        Random random = new Random();
//        int randomIndex = random.nextInt((int) count);
//        try (Jedis jedis = jedisPool.getResource()){
//            String key = "product:%d".formatted(randomIndex);
//            String raw = jedis.get(key);
//            if (raw != null) {
//                return mapper.readValue(raw, ProductDTO.class);
//            }
//            var product = productRepository.findById(randomIndex).orElseThrow(
//                    () -> new ResourceNotFoundException("Product with id " + randomIndex + " not found")
//            );
//
//            ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);
//            productDTO.setTotalCost(product.calculateTotalCost());
//
//            jedis.setex(key, TTL, mapper.writeValueAsString(productDTO));
//            return productDTO;
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Could not get product with id " + randomIndex);
//        }
//
//    }


//    @GetMapping("/random")
//    public ProductDTO getRandomProduct() {
//        long count = productRepository.countAllProducts();
//        if (count == 0) {
//            throw new ResourceNotFoundException("No products found");
//        }
//        Random random = new Random();
//        int randomIndex = random.nextInt((int) count);
//        Product product = productRepository.findById(randomIndex).orElseThrow(
//                () -> new ResourceNotFoundException("Product with id " + randomIndex + " not found")
//        );
//        ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);
//        productDTO.setTotalCost(product.calculateTotalCost());
//        return productDTO;
//    }


}
