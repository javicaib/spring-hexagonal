package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class ErrorResponse {
    String code;
    String message;
    List<String> details;
    LocalDateTime timestamp;
}
