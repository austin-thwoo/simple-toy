package com.sims.soft.domain.user.exception;


import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class DuplicationPasswordException extends BusinessException {
    public DuplicationPasswordException(String value) { super(value, ErrorCode.NOT_MATCH_PASSWORD);
    }
}
