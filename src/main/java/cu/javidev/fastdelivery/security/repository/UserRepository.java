package cu.javidev.fastdelivery.security.repository;

import cu.javidev.fastdelivery.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> findUser(String username);


}
