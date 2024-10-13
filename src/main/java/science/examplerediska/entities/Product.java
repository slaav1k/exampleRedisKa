package science.examplerediska.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor()
@AllArgsConstructor()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int quantity;

    public double calculateTotalCost() {
        return price * quantity * 1.0;
    }



}
