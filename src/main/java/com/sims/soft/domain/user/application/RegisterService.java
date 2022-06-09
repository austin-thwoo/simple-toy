package com.sims.soft.domain.user.application;

import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.user.dto.request.TokenDTO;
import com.sims.soft.domain.user.dto.request.UserRegisterCommand;
import com.sims.soft.domain.user.dto.response.TokenResponse;
import com.sims.soft.domain.user.exception.UserNameDuplicatedException;
import com.sims.soft.domain.user.persistance.UserJpaRepository;
import com.sims.soft.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider jwtTokenProvider;

    public TokenResponse register(UserRegisterCommand registerCommand) {
        User savedUser=this.save(registerCommand);
        TokenDTO dto = new TokenDTO(getToken(savedUser), savedUser);
        return new TokenResponse(dto);
    }

    public User save(UserRegisterCommand registerCommand) {

        return place(registerCommand);
    }

    public User place(UserRegisterCommand registerCommand) {


        checkUserName(registerCommand.getUsername());
//        checkUserEmail(registerCommand.getUserEmail());
        registerCommand.setEncodedPassword(passwordEncoder.encode(registerCommand.getPassword()));
        User user = User.create(registerCommand);
        user.addRole("ROLE_USER");

        return userJpaRepository.save(user);

    }

    private void checkUserName(String username) {
        Optional<User> user = userJpaRepository.findByUserName(username);
        if (user.isPresent()) {
            throw new UserNameDuplicatedException(username);
        }


    }
    private String getToken(User user) {
        return jwtTokenProvider.createToken(user.getId().toString(), user.getRoles());
    }
}
