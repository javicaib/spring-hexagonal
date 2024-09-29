package cu.javidev.fastdelivery.product.infraestructure.in.rest.mapper;

import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

    Product toProductCreateRequest(Product product);

    ProductResponse toProductResponse(Product product);

}
