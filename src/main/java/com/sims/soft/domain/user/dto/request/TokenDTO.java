package com.sims.soft.domain.user.dto.request;


import com.sims.soft.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private User user;
}
