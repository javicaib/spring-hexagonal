package cu.javidev.fastdelivery.product.infraestructure.out.persistence;

import cu.javidev.fastdelivery.commons.PersistenceAdapter;
import cu.javidev.fastdelivery.product.application.ports.out.ProductPersistencePort;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.entity.ProductEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.mapper.ProductPersistenceMapper;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductRepository repository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return mapper.toProducts(repository.findAll());
    }

    @Override
    public Product save(Product product) {
        ProductEntity saveProduct = mapper.toProductEntity(product);
        return mapper.toProduct(repository.save(saveProduct));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
