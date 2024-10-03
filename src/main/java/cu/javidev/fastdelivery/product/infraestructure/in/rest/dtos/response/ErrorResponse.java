package cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response;


import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        List<String> details,
        Instant timestamp
) {
    // Constructor personalizado para asignar el timestamp automáticamente
    public ErrorResponse(String code, String message, List<String> details) {
        this(code, message, details, Instant.now());
    }

    // Constructor personalizado para asignar el timestamp automáticamente y no haga falta detallar la excepcion
    public ErrorResponse(String code, String message) {
        this(code, message,null, Instant.now());
    }
}
