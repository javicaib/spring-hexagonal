package cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.mapper;

import cu.javidev.fastdelivery.product.domain.models.Product;
import cu.javidev.fastdelivery.product.infraestructure.in.rest.dtos.response.ProductResponse;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ImagesEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);

    List<Product> toProducts(List<ProductEntity> productEntities);

    @Mapping(source = "images", target = "images", qualifiedByName = "mapImagesToPaths")
    ProductResponse toProductResponse(ProductEntity productEntity);

    @Named("mapImagesToPaths")
    default List<String> mapImagesToPaths(List<ImagesEntity> images) {
        return images.stream()
                .map(ImagesEntity::getPath)
                .collect(Collectors.toList());
    }

}
