package cu.javidev.fastdelivery.product.infraestructure.in.rest.mapper;

import cu.javidev.fastdelivery.Testing.ProductCreateCommand;
import cu.javidev.fastdelivery.product.application.ports.in.ProductSaveCommand;
import cu.javidev.fastdelivery.product.domain.models.Image;
import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductCreateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.requests.ProductUpdateRequest;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

    Product toProduct(ProductCreateRequest productCreateRequest);

    Product toProduct(ProductUpdateRequest productUpdateRequest);

    @Mapping(source = "images", target = "images",qualifiedByName = "mapImagesToPathsDomain")
    ProductResponse toProductResponse(Product product);

    ProductSaveCommand toProductSaveCommand(ProductCreateCommand product);

    @Named("mapImagesToPathsDomain")
    default List<String> mapImagesToPathsDomain(List<Image> images) {
        return images.stream()
                .map(Image::path)
                .collect(Collectors.toList());
    }


}
