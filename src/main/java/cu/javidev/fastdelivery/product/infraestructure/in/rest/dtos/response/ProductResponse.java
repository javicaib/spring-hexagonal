package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;

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

    public List<String> getImages() {

        return images.stream().map(image->"/media/"+image).toList();
    }
}
