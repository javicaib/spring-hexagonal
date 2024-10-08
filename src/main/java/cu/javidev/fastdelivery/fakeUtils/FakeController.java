package cu.javidev.fastdelivery.fakeUtils;

import cu.javidev.fastdelivery.security.repository.RoleEntityRepository;
import cu.javidev.fastdelivery.security.entity.RoleEnum;
import cu.javidev.fastdelivery.security.entity.UserEntity;
import cu.javidev.fastdelivery.security.repository.UserRepository;
import cu.javidev.fastdelivery.security.rest.UserRegisterDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/fake")
@Slf4j
public class FakeController {
    private final RoleEntityRepository roleEntityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${upload.media.folder}")
    private  String UPLOAD_DIR;

    public FakeController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleEntityRepository roleEntityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleEntityRepository = roleEntityRepository;
    }


    @GetMapping("/say-hello")
    public String sayHello() {
        return "Hello World";
    }


    @GetMapping("/say-secured")
    @PreAuthorize("hasAnyRole('DEVELOPER')")
    public String sayHello2() {
        return "Hello Secured";
    }


    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public String sayHello3(@RequestBody @Valid UserRegisterDTO user) {
        user.isPasswordsMatch();
        UserEntity registerUser = UserEntity.builder().username(user.username()).password(passwordEncoder.encode(user.password_confirm())).isEnabled(true).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).role(roleEntityRepository.findByRole(RoleEnum.DEVELOPER).orElse(null)).build();

        userRepository.save(registerUser);
        log.info(user.username());
        return String.format("Created user %s", user.username());
    }

    @PermitAll
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName, HttpServletRequest request) {
        try {
            // Crear la ruta del archivo
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            // Verificar si el archivo existe y es legible
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Determinar el tipo de contenido (MIME type)
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            // Si no se puede determinar el tipo de contenido, usar un tipo por defecto
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            // Retornar el archivo con el tipo de contenido correcto
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
