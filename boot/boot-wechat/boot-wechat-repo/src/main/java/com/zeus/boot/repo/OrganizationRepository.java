package com.zeus.boot.repo;

import com.zeus.boot.entity.Organization;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrganizationRepository extends JpaRepository<Organization,String> {
    @Override
    List<Organization> findAll();

    @Override
    List<Organization> findAll(Sort sort);

    @Override
    Page<Organization> findAll(Pageable pageable);

    @Override
    List<Organization> findAllById(Iterable<String> Strings);

    @Override
    long count();

    @Override
    void deleteById(String aString);

    @Override
    void delete(Organization entity);

    @Override
    void deleteAll(Iterable<? extends Organization> entities);

    @Override
    void deleteAll();

    @Override
    <S extends Organization> S save(S entity);

    @Override
    <S extends Organization> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Organization> findById(String aString);

    @Override
    boolean existsById(String aString);

    @Override
    void flush();

    @Override
    <S extends Organization> S saveAndFlush(S entity);

    @Override
    void deleteInBatch(Iterable<Organization> entities);

    @Override
    void deleteAllInBatch();

    @Override
    Organization getOne(String aString);

    @Override
    <S extends Organization> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Organization> List<S> findAll(Example<S> example);

    @Override
    <S extends Organization> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Organization> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Organization> long count(Example<S> example);

    @Override
    <S extends Organization> boolean exists(Example<S> example);
}
