package cu.javidev.fastdelivery.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Long id;

    @Column(unique = true)
    String username;

    String password;

    @Column(name = "is_enabled")
    boolean isEnabled;

    @Column(name = "account_No_Expired")
    boolean accountNonExpired;

    @Column(name = "account_No_Locked")
    boolean accountNonLocked;

    @Column(name = "credential_No_Expired")
    boolean credentialsNonExpired;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
