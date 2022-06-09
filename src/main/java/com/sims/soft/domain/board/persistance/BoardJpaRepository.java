package com.sims.soft.domain.board.persistance;

import com.sims.soft.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

}
