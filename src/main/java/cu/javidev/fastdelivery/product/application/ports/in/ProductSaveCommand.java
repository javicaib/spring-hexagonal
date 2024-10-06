package cu.javidev.fastdelivery.product.application.ports.in;

import java.math.BigDecimal;


public record ProductSaveCommand(String name, BigDecimal price, String description) {


}
