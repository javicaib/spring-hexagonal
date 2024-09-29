package cu.javidev.fastdelivery.product.domain.exceptions;

public class ProductAlreadyRegistered extends RuntimeException {
    public ProductAlreadyRegistered(String message) {
        super(message);
    }
}
