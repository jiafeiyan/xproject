package com.zeus.boot.repo;

import com.zeus.boot.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    //根据组织类型查询机构信息
    List<Organization> findAllByOrgType(String org_type);
    //根据组织类型，组织id模糊搜索机构信息
    List<Organization> findAllByOrgTypeAndOrgName(String org_type,String org_name);
    //根据组织类型，关键字模糊搜索机构信息
    List<Organization> findAllByOrgTypeAndOrgKeyword(String org_type,String org_keyword);

}
