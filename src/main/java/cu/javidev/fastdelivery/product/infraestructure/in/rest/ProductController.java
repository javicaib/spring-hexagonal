package cu.javidev.fastdelivery.product.infraestructure.in.rest;

import cu.javidev.fastdelivery.commons.WebAdapter;
import cu.javidev.fastdelivery.product.application.services.ProductService;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.mapper.ProductRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductRestMapper mapper;

    @GetMapping("/v1/api")
    public List<ProductResponse> findAllProducts() {
        return service.findAllProducts().stream().map(mapper::toProductResponse).toList();
    }
}
