package science.examplerediska.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private double totalCost;

    public ProductDTO(int id, String name, double price, int quantity, double totalCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }
}
