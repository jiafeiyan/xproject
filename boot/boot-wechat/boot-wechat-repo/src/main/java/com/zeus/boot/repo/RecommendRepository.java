package com.zeus.boot.repo;

import com.zeus.boot.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    //根据推荐人ID查询近20场比赛记录
    List<Recommend> getTop20ByRcmRcmeridOrderByRcmDateDesc(Long rcmerId);
}
