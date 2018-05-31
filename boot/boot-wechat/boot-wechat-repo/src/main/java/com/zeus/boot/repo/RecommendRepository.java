package com.zeus.boot.repo;

import com.zeus.boot.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    List<Recommend> getTop20ByRcmRcmeridOrderByRcmDateDesc(String rcmerId);
}
