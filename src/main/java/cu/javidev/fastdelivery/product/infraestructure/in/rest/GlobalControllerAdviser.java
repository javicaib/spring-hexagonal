package cu.javidev.fastdelivery.product.infraestructure.in.rest;

import cu.javidev.fastdelivery.product.domain.exceptions.ProductAlreadyRegistered;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductNotFound;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static cu.javidev.fastdelivery.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdviser {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFound.class)
    public ErrorResponse handleProductNotFoundException() {
        return ErrorResponse.builder()
                .code(PRODUCT_NOT_FOUND.getCode())
                .message(PRODUCT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(PRODUCT_INVALID.getCode())
                .message(PRODUCT_INVALID.getMessage())
                .details(
                        bindingResult.getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .toList()
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductAlreadyRegistered.class)
    public ErrorResponse handleProductAlreadyRegisteredException(ProductAlreadyRegistered exception) {
        return ErrorResponse.builder()
                .code(PERSISTENCE_DUPLICATED_ENTRY_ERROR.getCode())
                .message(PERSISTENCE_DUPLICATED_ENTRY_ERROR.getMessage())
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericExceptions(Exception exception) {
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .build();
    }
}
