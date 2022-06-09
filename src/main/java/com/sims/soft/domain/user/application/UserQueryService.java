package com.sims.soft.domain.user.application;

import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.user.dto.request.TokenDTO;
import com.sims.soft.domain.user.dto.response.TokenResponse;
import com.sims.soft.domain.user.persistance.UserJpaRepository;
import com.sims.soft.domain.user.persistance.UserRepositorySupport;
import com.sims.soft.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserQueryService {
    private final UserRepositorySupport userRepositorySupport;
    private final TokenProvider tokenProvider;


    public TokenResponse findById(User principal) {

        User user=getUser(principal.getId());
        String token = tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
        TokenDTO dto = new TokenDTO(token, user);

   return new TokenResponse(dto);

    }

    private User getUser(Long userId) {
        return userRepositorySupport.findById(userId);
    }


}
