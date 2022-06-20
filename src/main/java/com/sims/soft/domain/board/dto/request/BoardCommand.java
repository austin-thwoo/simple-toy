package com.sims.soft.domain.board.dto.request;

import com.sims.soft.domain.board.domain.Board;
import com.sims.soft.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class BoardCommand {

    @NotNull
    private String title;

    private String content;
    private User writer;
    public Board toBoard(){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }



    public void setWriter(User user){
        this.writer=user;
    }

}
