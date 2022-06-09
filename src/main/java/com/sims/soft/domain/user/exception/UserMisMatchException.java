package com.sims.soft.domain.user.exception;

import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;
public class UserMisMatchException extends BusinessException {

    public UserMisMatchException(String message) {
        super(message, ErrorCode.USER_MIS_MATCH);
    }
}
