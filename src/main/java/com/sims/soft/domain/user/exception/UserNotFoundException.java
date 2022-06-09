package com.sims.soft.domain.user.exception;


import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String value) {
        super(value, ErrorCode.USER_NOT_FOUND);
    }
}
