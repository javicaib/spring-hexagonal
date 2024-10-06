package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;

import cu.javidev.fastdelivery.product.domain.models.Image;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    Long id;
    String name;
    BigDecimal price;
    String description;
    List<String> images;
}
