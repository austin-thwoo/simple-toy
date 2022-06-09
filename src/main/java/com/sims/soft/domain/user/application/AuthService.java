package com.sims.soft.domain.user.application;

import com.sims.soft.domain.user.exception.UserNameDuplicatedException;
import com.sims.soft.domain.user.persistance.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final UserJpaRepository userJpaRepository;

    public Boolean usernameOverLap(String userName) {
        boolean exist = userJpaRepository.existsByUserName(userName);

        if (exist) {
            throw new UserNameDuplicatedException(userName);
        }
        return true;
    }
}
