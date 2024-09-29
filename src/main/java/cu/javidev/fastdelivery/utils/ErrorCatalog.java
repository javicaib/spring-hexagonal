package cu.javidev.fastdelivery.utils;

import lombok.Getter;


@Getter
public enum ErrorCatalog {

    PRODUCT_NOT_FOUND("ERROR_PRODUCT_001","Product not found."),
    PRODUCT_INVALID("ERROR_PRODUCT_002","Invalid product parameters."),
    GENERIC_ERROR("ERROR_GEN_001","A catastrophic error has occurred."),
    ;

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
