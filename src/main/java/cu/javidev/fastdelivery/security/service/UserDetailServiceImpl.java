package cu.javidev.fastdelivery.security.service;

import cu.javidev.fastdelivery.security.entity.UserEntity;
import cu.javidev.fastdelivery.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository
                .findUser(username)
                .orElseThrow(() ->
                        {
                            log.error("Username {} not found", username);
                            return new UsernameNotFoundException(String.format("Username with name %s not found", username));
                        }
                );

        List<SimpleGrantedAuthority> grantedAuthorities =
                new ArrayList<>(userEntity.getRole()
                        .getPermissions()
                        .stream()
                        .map(
                                permission -> new SimpleGrantedAuthority(permission.getName())
                        )
                        .toList());

        grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", userEntity.getRole().getRole().name())));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(grantedAuthorities)
                .accountExpired(userEntity.isAccountNonExpired())
                .accountLocked(userEntity.isAccountNonLocked())
                .credentialsExpired(userEntity.isCredentialsNonExpired())
                .disabled(!userEntity.isEnabled())
                .build();
    }


}
