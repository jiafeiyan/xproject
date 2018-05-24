package com.zeus.boot.repo;

import com.zeus.boot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Override
    Admin getOne(String s);
}
