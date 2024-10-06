package cu.javidev.fastdelivery.product.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    Long id;
    String name;
    BigDecimal price;
    String description;
    List<Image> images;
}
