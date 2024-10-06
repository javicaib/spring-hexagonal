package cu.javidev.fastdelivery.product.infraestructure.in.rest;

import cu.javidev.fastdelivery.commons.WebAdapter;
import cu.javidev.fastdelivery.product.application.services.ProductService;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductCreateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductUpdateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.mapper.ProductRestMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@PermitAll
public class ProductController {
    private final ProductService service;
    private final ProductRestMapper mapper;

    @GetMapping("/v1/api")
    public List<ProductResponse> findAllProducts() {
        return service.findAllProducts().stream().map(mapper::toProductResponse).toList();
    }

    @GetMapping("/v1/api/{id}")
    public ProductResponse findProductById(@PathVariable Long id) {
        return mapper.toProductResponse(service.findProductById(id));
    }

    @PostMapping("/v1/api")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse saveProduct(@Valid @RequestBody ProductCreateRequest request) {
        Product product = service.saveProduct(mapper.toProduct(request));
        log.info("Product save {}", request.getProductSummary());
        return mapper.toProductResponse(product);
    }

    @PutMapping("/v1/api/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        Product product = service.updateProduct(id, mapper.toProduct(request));
        log.info("Product update {}", request.getProductSummary());
        return mapper.toProductResponse(product);
    }

    @DeleteMapping("/v1/api/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        log.info("Product delete whit id {}", id);
        service.deleteProduct(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v2/api",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ProductResponse createProductWhitImages(@RequestPart("images") List<MultipartFile> files, @RequestPart("product") ProductCreateRequest product) {
        Product responseProduct = service.saveProduct(mapper.toProductSaveCommand(product), files);
        return mapper.toProductResponse(responseProduct);
    }
}
