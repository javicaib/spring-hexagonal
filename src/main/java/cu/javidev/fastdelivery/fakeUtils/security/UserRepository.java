package cu.javidev.fastdelivery.fakeUtils.security;

import cu.javidev.fastdelivery.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
