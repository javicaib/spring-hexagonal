package cu.javidev.fastdelivery.utils;

import lombok.Getter;

/**
 * Enum que representa un catálogo de errores utilizados en la aplicación.
 * Cada error tiene un código único y un mensaje asociado que describe el error.
 */
@Getter
public enum ErrorCatalog {
    // PRODUCT ERRORS
    /** Error que indica que un producto no se encuentra. */
    PRODUCT_NOT_FOUND("ERROR_PRODUCT_001", "Product not found."),
    /** Error que indica que los parámetros al crear el producto son inválidos. */
    PRODUCT_INVALID("ERROR_PRODUCT_002", "Invalid product parameters."),

    // SECURITY ERRORS
    /** Error de seguridad que indica que la solicitud ha sido denegada. (Es el que se debe usar al usar en Spring Security denyAll() ) */
    SECURITY_ERROR_DENY("ERROR_SECURITY_001", "Request Denied."),
    /** Error de seguridad que indica que el usuario no fue encontrado. */
    SECURITY_ERROR_USER_NOT_FOUND("ERROR_SECURITY_002", "User not found."),

    // PERSISTENCE ERRORS
    /** Error que indica que ha ocurrido una entrada duplicada. */
    PERSISTENCE_DUPLICATED_ENTRY_ERROR("ERROR_PERSISTENCE_001", "A duplicated entry has occurred."),

    // GENERIC ERRORS
    /** Error genérico que indica que ha ocurrido un error catastrófico. */
    GENERIC_ERROR("ERROR_GEN_001", "A catastrophic error has occurred."),
    ;

    private final String code;
    private final String message;
    /**
     * Constructor para crear un nuevo ErrorCatalog con un código y un mensaje.
     *
     * @param code    el código del error
     * @param message el mensaje descriptivo del error
     */
    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
