package com.zeus.boot.repo;

import com.zeus.boot.entity.Board;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //查询近10条公告
    List<Board> findAll();
}
