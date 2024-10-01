package cu.javidev.fastdelivery.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    String password;

    @Column(name = "enabled")
    Boolean isEnabled;

    @Column(name = "account_non_expired")
    Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    Boolean credentialsNonExpired;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
