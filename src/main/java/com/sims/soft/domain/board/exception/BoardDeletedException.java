package com.sims.soft.domain.board.exception;


import com.sims.soft.global.error.exception.BusinessException;
import com.sims.soft.global.error.model.ErrorCode;

public class BoardDeletedException extends BusinessException {
    public BoardDeletedException(String value) {
        super(value, ErrorCode.BOARD_DELETED);
    }
}
