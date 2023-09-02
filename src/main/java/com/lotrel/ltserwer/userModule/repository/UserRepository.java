package com.lotrel.ltserwer.userModule.repository;

import com.lotrel.ltserwer.userModule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserPortalToken(String portalUserToken);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByIdAndAccountActiveTrue(Long aLong);
}
