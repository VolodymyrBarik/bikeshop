package org.bikeshop.repository;

import java.util.Optional;
import org.bikeshop.model.WholesaleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WholesaleUserRepository extends JpaRepository<WholesaleUser, Long> {
    @Query("from WholesaleUser wu inner join fetch wu.role where wu.email =:email")
    Optional<WholesaleUser> findByEmail(String email);
}
