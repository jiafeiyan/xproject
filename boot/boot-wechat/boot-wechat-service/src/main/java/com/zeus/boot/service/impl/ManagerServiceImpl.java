package com.zeus.boot.service.impl;

import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private RecommendRepository recommendRepository;



}
