package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;


public record ProductUpdateRequest (
        @NotBlank(message = "Field name cannot be empty or null")
        String name,

        @NotNull(message = "Field price cannot be empty or null")
        @Min(value = 0, message = "Price cannot be less than 0")
        BigDecimal price,

        @NotBlank(message = "Field description cannot be empty or null")
        String description
) {
    public String getProductSummary() {
        return String.format("Product Name: %s, Price: %s", name, price);
    }
}
