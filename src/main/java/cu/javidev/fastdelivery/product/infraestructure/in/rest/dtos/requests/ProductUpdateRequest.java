package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    @NotBlank(message = "Field name cannot empty or null")
    String name;

    @NotNull(message = "Field price cannot empty or null")
    @Min(message = "Price cannot be less than 0",value = 0)
    BigDecimal price;

    @NotBlank(message = "Field name description empty or null")
    String description;
}
