package com.sims.soft.domain.board.application;

import com.sims.soft.domain.board.domain.Board;
import com.sims.soft.domain.board.domain.Likee;
import com.sims.soft.domain.board.dto.Response.BoardPageResponse;
import com.sims.soft.domain.board.dto.Response.BoardResponse;
import com.sims.soft.domain.board.exception.BoardDeletedException;
import com.sims.soft.domain.board.persistance.BoardJpaRepository;
import com.sims.soft.domain.board.persistance.BoardRepositorySupport;

import com.sims.soft.domain.board.persistance.LikeeJpaRepository;
import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.user.persistance.UserRepositorySupport;
import com.sims.soft.global.dto.request.PageRequest;
import com.sims.soft.global.dto.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardQueryService {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardRepositorySupport boardRepositorySupport;
    private final UserRepositorySupport userRepositorySupport;
    private final LikeeJpaRepository likeeJpaRepository;
    public BoardResponse findById(User principal, Long boardId){
        Board board =getBoard(boardId);
        checkDeleted(board);

        Boolean likeExitst = likeCheck(principal.getId(), boardId).isPresent();

        return new BoardResponse(board,likeExitst);
    }

    private Optional<Likee> likeCheck(Long userId, Long boardId) {
//        return likeeJpaRepository.findByUserIdAndBoardId(userId,boardId);
        return boardRepositorySupport.findLikeByUserIdAndBoardId(userId,boardId);
    }


    private void checkDeleted(Board board) {
        if (board.getDeletedDate()!= null){
            throw new BoardDeletedException(board.getId().toString());
        }
    }

    private Board getBoard(Long boardId) {
        return boardRepositorySupport.findById(boardId);
            }

    public PagingResponse<BoardPageResponse> boardList(int page, String query) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<BoardPageResponse> boardPage = boardRepositorySupport.findAll(pageRequest.of(), query);
        return new PagingResponse<>(boardPage);
    }
}
