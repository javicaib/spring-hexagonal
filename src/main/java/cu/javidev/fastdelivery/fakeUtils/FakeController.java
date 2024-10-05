package cu.javidev.fastdelivery.fakeUtils;

import cu.javidev.fastdelivery.security.repository.RoleEntityRepository;
import cu.javidev.fastdelivery.security.entity.RoleEnum;
import cu.javidev.fastdelivery.security.entity.UserEntity;
import cu.javidev.fastdelivery.security.repository.UserRepository;
import cu.javidev.fastdelivery.security.rest.UserRegisterDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/fake")
@Slf4j
public class FakeController {
    private final RoleEntityRepository roleEntityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "uploads/";

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

    @PostMapping("/uploadImage")
    @PreAuthorize("permitAll()")
    public String uploadImage(@RequestParam("image") MultipartFile file) {

        try {
            // Asegúrate de que la carpeta existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
               boolean isCreated = uploadDir.mkdirs();  // Crea la carpeta si no existe
            }

            // Nombre de archivo que incluya el id del producto
            String fileName = "roberto";

            // Ruta donde guardar la imagen
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // Guardar el archivo
            Files.write(filePath, file.getBytes());

            return "Imagen subida con éxito: " + filePath;
        } catch (IOException e) {
            return "Error al subir la imagen: " + e.getMessage();
        }
    }
}
