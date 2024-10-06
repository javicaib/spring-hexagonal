package cu.javidev.fastdelivery.product.application.services;

import cu.javidev.fastdelivery.product.application.ports.in.ProductSaveCommand;
import cu.javidev.fastdelivery.product.application.ports.in.ProductServicePort;
import cu.javidev.fastdelivery.product.application.ports.out.ProductPersistencePort;
import cu.javidev.fastdelivery.product.application.ports.out.UploadPersistencePort;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductAlreadyExists;
import cu.javidev.fastdelivery.product.domain.exceptions.ProductNotFound;
import cu.javidev.fastdelivery.product.domain.models.Image;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.commons.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@UseCase
@RequiredArgsConstructor
@Slf4j
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort repository;
    private final UploadPersistencePort uploadRepository;

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
    public Product saveProduct(ProductSaveCommand product, List<MultipartFile> files) {
        Product saveProduct = new Product();

        saveProduct.setDescription(product.description());
        saveProduct.setName(product.name());
        saveProduct.setPrice(product.price());

        List<Image> images = new ArrayList<>();

        files.forEach(file -> {
            images.add(new Image(uploadRepository.saveFileToMediaFolder(file)));
        });

        saveProduct.setImages(images);

        log.info("Saving product {}", saveProduct);

        return repository.save(saveProduct);
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
