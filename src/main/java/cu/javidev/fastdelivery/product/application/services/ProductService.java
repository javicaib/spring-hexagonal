package cu.javidev.fastdelivery.product.application.services;

import cu.javidev.fastdelivery.product.application.ports.in.ProductServicePort;
import cu.javidev.fastdelivery.product.application.ports.out.ProductPersistencePort;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductAlreadyExists;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductNotFound;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.commons.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@UseCase
@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort repository;

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productExists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {

        if (repository.existsByName(product.getName())) {
            throw new ProductAlreadyExists("Product whit name " + product.getName() + " already exists");
        }

        return repository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product updateProduct) {
        Product product = productExists(id);

        if (!Objects.equals(product.getName(), updateProduct.getName())) {
            if (repository.existsByName(updateProduct.getName()))
                throw new ProductAlreadyExists("Product whit name " + updateProduct.getName() + " already exists");
        }

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());

        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productExists(id);
        log.info("Deleting product with id: {}", id);
        repository.delete(id);
    }

    private Product productExists(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFound::new);
    }

}
