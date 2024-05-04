package org.bikeshop.repository;

import java.util.Optional;
import org.bikeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u inner join fetch u.role where u.email =:email")
    Optional<User> findByEmail(String email);
}
