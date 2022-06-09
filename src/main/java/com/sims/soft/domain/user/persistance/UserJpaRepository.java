package com.sims.soft.domain.user.persistance;

import com.sims.soft.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    boolean existsByUserName(String userName);
}
