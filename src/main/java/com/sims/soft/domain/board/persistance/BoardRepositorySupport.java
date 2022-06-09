package com.sims.soft.domain.board.persistance;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sims.soft.domain.board.domain.Board;
import com.sims.soft.domain.board.domain.Likee;
import com.sims.soft.domain.board.dto.Response.BoardPageResponse;

import com.sims.soft.domain.board.exception.BoardNotFoundException;
import com.sims.soft.domain.user.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import static com.sims.soft.domain.board.domain.QBoard.board;
import static com.sims.soft.domain.board.domain.QLikee.likee;

@Repository
public class
BoardRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public BoardRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }


    public Board findById(Long boardId) {
        Board result = queryFactory.selectFrom(board)
                .where(board.id.eq(boardId)).fetchOne();
        if (result == null) {
            throw new BoardNotFoundException(boardId.toString());
        }
        return result;
    }


    public Board findByTitle(String title) {
        Board result = queryFactory.selectFrom(board)
                .where(board.title.eq(title)).fetchOne();
        if (result == null) {
            throw new BoardNotFoundException(title);
        }
        return result;
    }


    public Page<BoardPageResponse> findAll(Pageable pageable, String query) {
        QueryResults<BoardPageResponse> result = queryFactory.select(Projections.constructor(BoardPageResponse.class, board))
                .from(board)
                .where(board.id.isNotNull()
                        .and(titleQuery(query)))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());


    }


    private BooleanExpression titleQuery(String query) {
        if (query == null) {
            return null;
        }
        return board.title.contains(query);
    }

    public Optional<Likee> findLikeByUserIdAndBoardId(Long userId, Long boardId) {
        return Optional.ofNullable(queryFactory.selectFrom(likee)
                .where(likee.User.id.eq(userId)
                        .and(likee.board.id.eq(boardId)))
                .fetchOne());
    }
}


