package cu.javidev.fastdelivery;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
@PreAuthorize("denyAll()")
public class FakeController {


    @GetMapping("/say-hello")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String sayHello() {
        return "Hello World";
    }
}
