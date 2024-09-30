package cu.javidev.fastdelivery.security.rest;

import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static cu.javidev.fastdelivery.utils.ErrorCatalog.*;

@RestControllerAdvice
@Order(1)
public class SecurityControllerAdviser {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ErrorResponse handleAuthorizationDeniedException(AuthorizationDeniedException e) {
        return ErrorResponse.builder()
                .code(SECURITY_ERROR_DENY.getCode())
                .message(SECURITY_ERROR_DENY.getMessage())
                .details(List.of(e.getMessage()))
                .build();
    }
}
