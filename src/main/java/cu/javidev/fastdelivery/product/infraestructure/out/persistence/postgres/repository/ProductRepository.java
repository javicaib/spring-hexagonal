package cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.repository;

import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Boolean existsByName(String name);
}
