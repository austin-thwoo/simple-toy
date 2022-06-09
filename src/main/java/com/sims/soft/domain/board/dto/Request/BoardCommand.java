package com.sims.soft.domain.board.dto.Request;

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



    public void setWriter(User user){
        this.writer=user;
    }

}
