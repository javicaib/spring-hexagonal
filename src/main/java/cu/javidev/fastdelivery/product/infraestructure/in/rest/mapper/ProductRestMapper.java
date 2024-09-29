package cu.javidev.fastdelivery.product.infraestructure.in.rest.mapper;

import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductCreateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductUpdateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

    Product toProduct(ProductCreateRequest productCreateRequest);

    Product toProduct(ProductUpdateRequest productUpdateRequest);

    ProductResponse toProductResponse(Product product);

}
