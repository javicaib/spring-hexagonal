package cu.javidev.fastdelivery;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FastDeliveryApplication {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new
                Info()
                .title("API Documentation")
                .version("1.0")
                .description("Product API Documentation")
                .termsOfService("Terms of Service")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
        );}
    public static void main(String[] args) {
        SpringApplication.run(FastDeliveryApplication.class, args);
    }

}
