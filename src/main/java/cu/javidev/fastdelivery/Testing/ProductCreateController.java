package cu.javidev.fastdelivery.Testing;

import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ImagesEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ProductEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.mapper.ProductPersistenceMapper;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.repository.ProductRepository;
import cu.javidev.fastdelivery.utils.uploads.services.IFileApiService;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-fake")
@Slf4j
public class ProductCreateController {
    private final IFileApiService fileApiService;
    private final ProductRepository repository;
    private final ProductPersistenceMapper mapper;

    public ProductCreateController(IFileApiService fileApiService, ProductRepository repository, ProductPersistenceMapper mapper) {
        this.fileApiService = fileApiService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PermitAll
    public ProductResponse createProductWhitImages(
            @RequestPart("images") List<MultipartFile> files,
            @RequestPart("product") ProductCreateCommand product
    ) {
        ProductEntity productToSave = new ProductEntity();


        productToSave.setName(product.name());

        productToSave.setDescription(product.description());

        productToSave.setPrice(product.price());

        List<ImagesEntity> paths = new ArrayList<>();

        files.forEach(image -> {
            paths.add(ImagesEntity.builder().path(fileApiService.saveFile(image)).build());
        });

        productToSave.setImages(paths);

        ProductEntity savedProduct = repository.save(productToSave);

        log.info("Saved product {}", mapper.toProductResponse(savedProduct));


        return mapper.toProductResponse(savedProduct);
    }
}
