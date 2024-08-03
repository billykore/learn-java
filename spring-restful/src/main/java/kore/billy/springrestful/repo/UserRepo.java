package kore.billy.springrestful.repo;

import kore.billy.springrestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findFirstByToken(String token);
}
