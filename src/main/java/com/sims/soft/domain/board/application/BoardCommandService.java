package com.sims.soft.domain.board.application;

import com.sims.soft.domain.board.domain.Board;
import com.sims.soft.domain.board.domain.Likee;
import com.sims.soft.domain.board.dto.response.BoardResponse;
import com.sims.soft.domain.board.persistance.BoardJpaRepository;
import com.sims.soft.domain.board.persistance.BoardRepositorySupport;


import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.board.dto.request.BoardCommand;
import com.sims.soft.domain.user.exception.UserMisMatchException;
import com.sims.soft.domain.user.persistance.UserRepositorySupport;
import com.sims.soft.domain.board.persistance.LikeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardCommandService {

    private final UserRepositorySupport userRepositorySupport;
    private final BoardRepositorySupport boardRepositorySupport;
    private final BoardJpaRepository boardJpaRepository;
    private final LikeeJpaRepository likeeJpaRepository;



    public BoardResponse save(User principal, BoardCommand boardCommand) {
        boardCommand.setWriter(getUserById(principal.getId()));
        Board board = Board.create(boardCommand);
        boardJpaRepository.save(board);
        return new BoardResponse(board,false);
    }

    private User getUserById(Long userId) {
        return userRepositorySupport.findById(userId);
    }

    public BoardResponse modify(User principal, Long boardId, BoardCommand boardCommand) {

        Board board = getBoardById(boardId);
        writerCheck(board, principal.getId());
        board.modify(boardCommand);

        return new BoardResponse(board,likeCheck(principal.getId(),boardId).isPresent());
    }
    private Optional<Likee> likeCheck(Long userId, Long boardId) {
//        return likeeJpaRepository.findByUserIdAndBoardId(userId,boardId);
        return boardRepositorySupport.findLikeByUserIdAndBoardId(userId,boardId);
    }

    private void writerCheck(Board board, Long userId) {
        if (!board.getWriter().getId().equals(userId)) {
            throw new UserMisMatchException(userId.toString());

        }
    }

    private Board getBoardById(Long boardId) {
        return boardRepositorySupport.findById(boardId);
    }

    public Boolean deleteById(User principal, Long boardId) {
        Board board = getBoardById(boardId);
        writerCheck(board, principal.getId());
        boardJpaRepository.deleteById(boardId);
        return true;
    }

    public String like(User principal, Long boardId) {
        Board board=getBoardById(boardId);

        Optional<Likee> like=getLike(principal.getId(),boardId);
        if (like.isPresent()){
            likeeJpaRepository.deleteById(like.get().getId());
            return "delete";
        }

        Likee likee=Likee.create(getUserById(principal.getId()),board);
        likeeJpaRepository.save(likee);
        return "insert";
    }

    private Optional<Likee> getLike(Long userId, Long boardId) {
        return boardRepositorySupport.findLikeByUserIdAndBoardId(userId,boardId);
    }
}

