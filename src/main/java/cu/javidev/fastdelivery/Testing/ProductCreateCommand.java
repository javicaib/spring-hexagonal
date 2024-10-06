package cu.javidev.fastdelivery.Testing;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateCommand(
        @NotBlank(message = "Field name cannot be empty or null")
        String name,

        @NotNull(message = "Field price cannot be empty or null")
        @Min(value = 0, message = "Price cannot be less than 0")
        BigDecimal price,

        @NotBlank(message = "Field description cannot be empty or null")
        String description
) {
}
