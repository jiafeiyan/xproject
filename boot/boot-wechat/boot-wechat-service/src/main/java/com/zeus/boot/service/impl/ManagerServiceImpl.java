package com.zeus.boot.service.impl;

import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public void rcmBatchRemove(List<Long> rcms) {
        rcms.stream().forEach(id -> recommendRepository.deleteById(id));
    }

}
