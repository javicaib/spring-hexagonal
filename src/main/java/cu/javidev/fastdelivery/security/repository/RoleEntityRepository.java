package cu.javidev.fastdelivery.security.repository;

import cu.javidev.fastdelivery.security.entity.RoleEntity;
import cu.javidev.fastdelivery.security.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByRole(RoleEnum role);
}