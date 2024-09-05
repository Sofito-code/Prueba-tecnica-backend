package co.edu.udea.lis.pruebat.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Actividades")
                        .version("1.0.0")
                        .description("Documentación de la API para la gestión de actividades.")
                        .contact(new Contact()
                                .name("Sofía")
                                .email("sofia.vanegasc@udea.edu.co")));
    }
}

