package com.sims.soft.domain.user.dto.request;

import com.sims.soft.domain.models.Address;
import com.sims.soft.domain.user.domain.common.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
public class UserRegisterCommand {


    private String username;
    private String password;
    private String nickName;
    private UserType userType;
    private String phoneNumber;

    @Embedded
    private Address address;

    public void setEncodedPassword(String encodedPassword) {
        this.password= encodedPassword;
    }
}
