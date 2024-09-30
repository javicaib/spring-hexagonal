package cu.javidev.fastdelivery.utils;

import lombok.Getter;


@Getter
public enum ErrorCatalog {

    PRODUCT_NOT_FOUND("ERROR_PRODUCT_001","Product not found."),
    PRODUCT_INVALID("ERROR_PRODUCT_002","Invalid product parameters."),
    GENERIC_ERROR("ERROR_GEN_001","A catastrophic error has occurred."),
    PERSISTENCE_DUPLICATED_ENTRY_ERROR("ERROR_PERSISTENCE_001","A duplicated entry has occurred."),
    SECURITY_ERROR_DENY("ERROR_SECURITY_001","Request Denied."),
    ;

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
