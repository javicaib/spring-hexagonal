package cu.javidev.fastdelivery.security.rest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    String username;
    String password;
}
