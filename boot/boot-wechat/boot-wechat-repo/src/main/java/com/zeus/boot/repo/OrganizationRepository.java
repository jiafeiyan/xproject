package com.zeus.boot.repo;

import com.zeus.boot.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    //根据组织类型查询机构信息
    List<Organization> findAllByOrg_type(String org_type);
}
