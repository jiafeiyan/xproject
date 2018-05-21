package com.zeus.boot.repo;

import com.zeus.boot.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    List<User> findAllById(Iterable<String> strings);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(User entity);

    @Override
    void deleteAll(Iterable<? extends User> entities);

    @Override
    void deleteAll();

    @Override
    <S extends User> S save(S entity);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<User> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    void flush();

    @Override
    <S extends User> S saveAndFlush(S entity);

    @Override
    void deleteInBatch(Iterable<User> entities);

    @Override
    void deleteAllInBatch();

    @Override
    User getOne(String s);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends User> long count(Example<S> example);

    @Override
    <S extends User> boolean exists(Example<S> example);
}
