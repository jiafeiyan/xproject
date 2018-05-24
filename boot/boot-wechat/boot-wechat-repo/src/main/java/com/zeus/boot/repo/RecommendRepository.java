package com.zeus.boot.repo;

import com.zeus.boot.entity.Recommend;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, String> {
    @Override
    List<Recommend> findAll();

    @Override
    List<Recommend> findAll(Sort sort);

    @Override
    Page<Recommend> findAll(Pageable pageable);

    @Override
    List<Recommend> findAllById(Iterable<String> Strings);

    @Override
    long count();

    @Override
    void deleteById(String aString);

    @Override
    void delete(Recommend entity);

    @Override
    void deleteAll(Iterable<? extends Recommend> entities);

    @Override
    void deleteAll();

    @Override
    <S extends Recommend> S save(S entity);

    @Override
    <S extends Recommend> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Recommend> findById(String aString);

    @Override
    boolean existsById(String aString);

    @Override
    void flush();

    @Override
    <S extends Recommend> S saveAndFlush(S entity);

    @Override
    void deleteInBatch(Iterable<Recommend> entities);

    @Override
    void deleteAllInBatch();

    @Override
    Recommend getOne(String aString);

    @Override
    <S extends Recommend> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Recommend> List<S> findAll(Example<S> example);

    @Override
    <S extends Recommend> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Recommend> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Recommend> long count(Example<S> example);

    @Override
    <S extends Recommend> boolean exists(Example<S> example);
}
