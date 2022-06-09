package com.sims.soft.domain.board.persistance;


import com.sims.soft.domain.board.domain.Board;
import com.sims.soft.domain.board.domain.Likee;
import com.sims.soft.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LikeeJpaRepository extends JpaRepository<Likee, Long> {


//    Optional<Likee> findByUserIdAndBoardId(Long userId,Long boardId);
    


    void deleteById(Likee like);
}
