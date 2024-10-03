package cu.javidev.fastdelivery.product.infraestructure.in.rest;

import cu.javidev.fastdelivery.product.domain.exceptions.ProductAlreadyExists;
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
        return new ErrorResponse(
                PRODUCT_NOT_FOUND.getCode(),
                PRODUCT_NOT_FOUND.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return new ErrorResponse(
                PRODUCT_INVALID.getCode(),
                PRODUCT_INVALID.getMessage(),
                bindingResult.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList()
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductAlreadyExists.class)
    public ErrorResponse handleProductAlreadyRegisteredException(ProductAlreadyExists exception) {

        return new ErrorResponse(
                PERSISTENCE_DUPLICATED_ENTRY_ERROR.getCode(),
                PERSISTENCE_DUPLICATED_ENTRY_ERROR.getMessage(),
                List.of(exception.getMessage())

        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericExceptions(Exception exception) {
        return new ErrorResponse(
                GENERIC_ERROR.getCode(),
                GENERIC_ERROR.getMessage(),
                List.of(exception.getMessage())
        );
    }
}
