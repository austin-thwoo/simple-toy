package com.sims.soft.domain.board.dto.Response;

import com.sims.soft.domain.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class BoardPageResponse {
    private final Long id;
    private final String title;
    private final String writer;
    private final Long likes;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BoardPageResponse(Board board){
        this.id=board.getId();
        this.title=board.getTitle();
        this.writer= board.getWriter().getNickName();
        this.likes= (long) board.getLikee().size();
        this.createdAt=board.getCreatedDate();
        this.modifiedAt=board.getModifiedDate();
    }

}
