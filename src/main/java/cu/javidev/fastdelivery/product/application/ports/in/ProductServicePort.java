package cu.javidev.fastdelivery.product.application.ports.in;

import cu.javidev.fastdelivery.product.domain.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServicePort {

    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product saveProduct(Product product);

    Product saveProduct(ProductSaveCommand product, List<MultipartFile> files);

    Product updateProduct(Long id, Product updateProduct);

    void deleteProduct(Long id);
}
