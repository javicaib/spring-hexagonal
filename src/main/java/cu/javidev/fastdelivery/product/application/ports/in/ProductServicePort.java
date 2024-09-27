package cu.javidev.fastdelivery.product.application.ports.in;

import cu.javidev.fastdelivery.product.domain.models.Product;

import java.util.List;

public interface ProductServicePort {

    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product updateProduct);

    void deleteProduct(Long id);
}
