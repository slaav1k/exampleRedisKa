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

//    public Product(String name, double price) {
//        this.name = name;
//        this.price = price;
//    }

}
