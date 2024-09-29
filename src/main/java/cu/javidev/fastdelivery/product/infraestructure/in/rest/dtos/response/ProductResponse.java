package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;

import lombok.*;

import java.math.BigDecimal;

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

}
