package cu.javidev.fastdelivery.security.rest;

import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static cu.javidev.fastdelivery.utils.ErrorCatalog.*;

@RestControllerAdvice
@Order(1)
public class SecurityControllerAdviser {

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAuthorizationDeniedException(AuthorizationDeniedException e) {

        return new ErrorResponse(
                SECURITY_ERROR_DENY.getCode(),
                SECURITY_ERROR_DENY.getMessage(),
                List.of(e.getMessage())
        );

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ErrorResponse(
                SECURITY_ERROR_USER_NOT_FOUND.getCode(),
                SECURITY_ERROR_USER_NOT_FOUND.getMessage(),
                List.of(e.getMessage())
                );
    }
}
