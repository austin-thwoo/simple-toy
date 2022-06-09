package com.sims.soft.domain.user.dto.response;


import com.sims.soft.domain.models.Address;
import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.user.domain.common.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
public class UserResponse {
    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;
    private final String phoneNumber;
    private final UserType userType;
    private final Address address;


    public UserResponse(User user){
        this.id = user.getId();
        this.username= user.getUsername();
        this.password=user.getPassword();
        this.nickname= user.getNickName();
        this.userType=user.getUserType();
        this.phoneNumber=user.getPhoneNumber();
        this.address=user.getAddress();



    }


}
