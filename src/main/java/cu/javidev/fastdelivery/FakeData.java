package cu.javidev.fastdelivery;


import cu.javidev.fastdelivery.product.infraestructure.out.persistence.entity.ProductEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeData implements InitializingBean {
    private final ProductRepository productRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        productRepository.saveAll(List.of(
                ProductEntity.builder().id(1L).name("Croquetas Fritas").description("Croquetas Fritas hechas de pollo y cerdo").price(new BigDecimal("10.00")).build(),
                ProductEntity.builder().id(2L).name("Empanadas de Queso").description("Empanadas rellenas de queso fresco").price(new BigDecimal("15.50")).build(),
                ProductEntity.builder().id(3L).name("Tostones").description("Tostones de plátano verde").price(new BigDecimal("5.00")).build(),
                ProductEntity.builder().id(4L).name("Tamales").description("Tamales de maíz con carne de cerdo").price(new BigDecimal("12.00")).build(),
                ProductEntity.builder().id(5L).name("Arroz con Pollo").description("Arroz con pollo y vegetales").price(new BigDecimal("20.00")).build(),
                ProductEntity.builder().id(6L).name("Ropa Vieja").description("Carne de res desmenuzada en salsa").price(new BigDecimal("18.50")).build(),
                ProductEntity.builder().id(7L).name("Yuca con Mojo").description("Yuca hervida con mojo de ajo").price(new BigDecimal("8.00")).build(),
                ProductEntity.builder().id(8L).name("Flan de Leche").description("Flan de leche condensada").price(new BigDecimal("6.50")).build(),
                ProductEntity.builder().id(9L).name("Churros").description("Churros rellenos de dulce de leche").price(new BigDecimal("7.00")).build(),
                ProductEntity.builder().id(10L).name("Picadillo a la Habanera").description("Picadillo de carne molida con papas").price(new BigDecimal("14.00")).build(),
                ProductEntity.builder().id(11L).name("Vaca Frita").description("Carne de res frita con cebolla").price(new BigDecimal("19.00")).build(),
                ProductEntity.builder().id(12L).name("Camarones al Ajillo").description("Camarones salteados en ajo y aceite").price(new BigDecimal("22.00")).build()
                ));

    }
}
