package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor

public class ProductResponse {
    Long id;
    String name;
    BigDecimal price;
    String description;
    List<String> images;

    public ProductResponse(Long id, String name, BigDecimal price, String description, List<String> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images.stream().map(image->"/media/"+image).toList();
    }
}
