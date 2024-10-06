package cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres;

import cu.javidev.fastdelivery.commons.PersistenceAdapter;
import cu.javidev.fastdelivery.product.application.ports.out.ProductPersistencePort;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ProductEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.mapper.ProductPersistenceMapper;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Optional;

@Slf4j
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
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
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
