package com.sims.soft.domain.board.api;

import com.sims.soft.domain.board.application.BoardQueryService;
import com.sims.soft.domain.board.dto.Response.BoardPageResponse;
import com.sims.soft.domain.board.dto.Response.BoardResponse;
import com.sims.soft.domain.board.application.BoardCommandService;
import com.sims.soft.domain.user.domain.User;
import com.sims.soft.domain.board.dto.Request.BoardCommand;
import com.sims.soft.global.dto.response.ApiResponse;
import com.sims.soft.global.dto.response.PagingResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class boardApi {

    private final BoardCommandService boardCommandService;
    private final BoardQueryService boardQueryService;



    @ApiOperation(value = "글쓰기")
    @PostMapping
    public ApiResponse<BoardResponse> save(@AuthenticationPrincipal User principal,
                                           @Valid @RequestBody BoardCommand boardCommand) {
        return new ApiResponse<>(boardCommandService.save(principal, boardCommand));
    }

    @ApiOperation(value = "글보기")
    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponse> findBoardById(@AuthenticationPrincipal User principal,
                                           @PathVariable(name = "boardId") Long boardId) {
        return new ApiResponse<>(boardQueryService.findById(principal, boardId));
    }

    @ApiOperation(value = "글목록")
    @GetMapping()
    public PagingResponse<BoardPageResponse> boardList(@RequestParam(required=false, defaultValue ="1")int page,
                                                       @RequestParam(required = false)String query){
        return boardQueryService.boardList(page, query);
    }

    @ApiOperation(value = "글수정")
    @PostMapping("/update/{boardId}")
    public ApiResponse<BoardResponse> modify(@AuthenticationPrincipal User principal,
                                             @PathVariable(name = "boardId") Long boardId,
                                             @Valid @RequestBody BoardCommand boardCommand) {
        return new ApiResponse<>(boardCommandService.modify(principal, boardId, boardCommand));
    }

    @ApiOperation(value = "글삭제")
    @DeleteMapping("/delte/{boardId}")
    public ApiResponse<Boolean> modify(@AuthenticationPrincipal User principal,
                                       @PathVariable(name = "boardId") Long boardId) {
        return new ApiResponse<>(boardCommandService.deleteById(principal, boardId));
    }

    @ApiOperation(value = "좋아요")
    @PostMapping("/like/{boardId}")
    public String like(@AuthenticationPrincipal User principal,
                        @PathVariable(name = "boardId") Long boardId) {
        return boardCommandService.like(principal,boardId);
    }


}
