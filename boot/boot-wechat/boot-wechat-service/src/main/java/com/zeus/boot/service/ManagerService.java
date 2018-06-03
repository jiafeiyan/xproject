package com.zeus.boot.service;


import java.util.List;

public interface ManagerService {
    void rcmBatchRemove(List<String> list);
    void orgBatchRemove(List<String> list);
    void brdBatchRemove(List<String> list);
}
