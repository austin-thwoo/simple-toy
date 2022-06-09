package com.sims.soft.domain.user.exception;


import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class UserNameDuplicatedException extends BusinessException {
    public UserNameDuplicatedException(String value) {
        super(value, ErrorCode.DUPLICATED_ID);
    }
}
