package com.zeus.boot.repo;

import com.zeus.boot.entity.Recommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    //根据推荐人ID查询近20场比赛记录
    Page<Recommend> findByRcmRcmerid(String rcmerId, Pageable pageable);
    //根据推荐人ID查询所有
    List<Recommend> findByRcmRcmerid(String rcmerId);
}
