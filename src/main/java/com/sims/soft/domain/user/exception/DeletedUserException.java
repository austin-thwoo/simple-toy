package com.sims.soft.domain.user.exception;

import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class DeletedUserException extends BusinessException {

    public DeletedUserException(String message) {
        super(message, ErrorCode.USER_DELETED);
    }
}
