package cu.javidev.fastdelivery.product.application.ports.out;

import cu.javidev.fastdelivery.product.domain.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPersistencePort {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void delete(Product product);
}
