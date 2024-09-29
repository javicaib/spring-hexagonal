package cu.javidev.fastdelivery.product.application.services;

import cu.javidev.fastdelivery.product.application.ports.in.ProductServicePort;
import cu.javidev.fastdelivery.product.application.ports.out.ProductPersistencePort;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductNotFound;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.commons.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort repository;

    @Override
    public Product findProductById(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFound::new);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        Product product = this.findProductById(id);

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());

        return repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.findProductById(id);
        repository.delete(product.getId());
    }
}
