package com.sims.soft.domain.board.domain;


import com.sims.soft.domain.board.dto.Request.BoardCommand;
import com.sims.soft.domain.user.domain.User;
import com.sims.soft.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_LIKEE")
public class Likee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private User User;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;


    public static Likee create(User user,Board board) {
        return Likee.builder()
                .User(user)
                .board(board)
                .build();
    }



}

