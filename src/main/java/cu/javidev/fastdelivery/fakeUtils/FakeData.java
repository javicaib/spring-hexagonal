package cu.javidev.fastdelivery.fakeUtils;


import cu.javidev.fastdelivery.fakeUtils.security.UserRepository;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.entity.ProductEntity;
import cu.javidev.fastdelivery.product.infraestructure.out.persistence.repository.ProductRepository;
import cu.javidev.fastdelivery.security.entity.PermissionEntity;
import cu.javidev.fastdelivery.security.entity.RoleEntity;
import cu.javidev.fastdelivery.security.entity.RoleEnum;
import cu.javidev.fastdelivery.security.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeData implements InitializingBean {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {

        // Create Fake Products
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

        // Crear permisos genéricos (solo para ejemplo, en un caso real estos estarían definidos)
        PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
        PermissionEntity writePermission = PermissionEntity.builder().name("WRITE").build();
        PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();
        PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();

        // Crear roles
        RoleEntity adminRole = RoleEntity.builder()
                .role(RoleEnum.ADMIN)
                .permissions(new HashSet<>() {{
                    add(readPermission);
                    add(writePermission);
                    add(updatePermission);
                }})
                .build();

        RoleEntity userRole = RoleEntity.builder()
                .role(RoleEnum.USER)
                .permissions(new HashSet<>() {{
                    add(readPermission);
                }})
                .build();

        RoleEntity guestRole = RoleEntity.builder()
                .role(RoleEnum.GUEST)
                .permissions(new HashSet<>())
                .build();

        RoleEntity developerRole = RoleEntity.builder()
                .role(RoleEnum.DEVELOPER)
                .permissions(new HashSet<>() {{
                    add(readPermission);
                    add(writePermission);
                    add(deletePermission);
                    add(updatePermission);
                }})
                .build();

        // Crear usuarios con diferentes roles
        UserEntity adminUser = UserEntity.builder()
                .username("adminUser")
                .password("adminPassword")  // Asegúrate de cifrar las contraseñas en un caso real
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .role(adminRole)
                .build();

        UserEntity regularUser = UserEntity.builder()
                .username("regularUser")
                .password("userPassword")
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .role(userRole)
                .build();

        UserEntity guestUser = UserEntity.builder()
                .username("guestUser")
                .password("guestPassword")
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .role(guestRole)
                .build();

        UserEntity developerUser = UserEntity.builder()
                .username("developerUser")
                .password("devPassword")
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .role(developerRole)
                .build();
        userRepository.saveAll(List.of(adminUser, regularUser, guestUser, developerUser));
    }

}
