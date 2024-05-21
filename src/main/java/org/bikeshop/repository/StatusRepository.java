package org.bikeshop.repository;

import org.bikeshop.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface StatusRepository extends JpaRepository<Status, Long> {

//    @Override
//    @NonNull
//    Page<Status> findAll(@NonNull Pageable pageable);

    @Query("SELECT DISTINCT s FROM Status s WHERE s.isDeleted = false AND s.isActive")
    Page<Status> findAllActiveNonDeleted(@NonNull Pageable pageable);
}
