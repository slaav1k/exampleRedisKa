package science.examplerediska.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private double avgRating;

    public ProductDTO(int id, String name, double price, double avgRating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.avgRating = avgRating;
    }
}
