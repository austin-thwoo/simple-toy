package com.sims.soft.domain.board.exception;


import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class BoardNotFoundException extends BusinessException {
    public BoardNotFoundException(String value) {
        super(value, ErrorCode.BOARD_NOT_FOUND);
    }
}
