package com.sims.soft.domain.board.dto.response;

import com.sims.soft.domain.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final Boolean like;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BoardResponse(Board board,Boolean like){
        this.id=board.getId();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.writer=board.getWriter().getNickName();
        this.like =like;
        this.createdAt=board.getCreatedDate();
        this.modifiedAt=board.getModifiedDate();

    }

}
