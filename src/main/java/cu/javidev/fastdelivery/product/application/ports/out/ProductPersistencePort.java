package cu.javidev.fastdelivery.product.application.ports.out;

import cu.javidev.fastdelivery.product.domain.models.Product;


import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void delete(Long id);
}
