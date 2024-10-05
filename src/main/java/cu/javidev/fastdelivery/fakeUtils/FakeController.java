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


@RestController
@RequestMapping("/fake")
@Slf4j
public class FakeController {
    private final RoleEntityRepository roleEntityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @GetMapping("/check")
    public Boolean checkPassword() {
        UserEntity jeremias = userRepository.findUser("jeremias").orElse(null);


        assert jeremias != null;
        return passwordEncoder.matches("123456", jeremias.getPassword());
    }
}
