package com.lotrel.ltserwer.userModule.repository;

import com.lotrel.ltserwer.userModule.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
