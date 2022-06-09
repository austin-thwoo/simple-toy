package com.sims.soft.domain.board.domain;


import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.board.dto.Request.BoardCommand;
import com.sims.soft.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_BOARD")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likee> likee= new ArrayList<>();
    //    private List<Board> boards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;


    public static Board create(BoardCommand boardCommand) {
        return Board.builder()
                .title(boardCommand.getTitle())
                .content(boardCommand.getContent())
                .writer(boardCommand.getWriter())
                .build();
    }

    public void modify(BoardCommand boardCommand) {
        this.title = boardCommand.getTitle();
        this.content = boardCommand.getContent();
    }




}

