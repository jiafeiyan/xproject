package com.zeus.boot.repo;

import com.zeus.boot.entity.Board;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    List<Board> findAll();

    @Override
    List<Board> findAll(Sort sort);

    @Override
    Page<Board> findAll(Pageable pageable);

    @Override
    List<Board> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Board entity);

    @Override
    void deleteAll(Iterable<? extends Board> entities);

    @Override
    void deleteAll();

    @Override
    <S extends Board> S save(S entity);

    @Override
    <S extends Board> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Board> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void flush();

    @Override
    <S extends Board> S saveAndFlush(S entity);

    @Override
    void deleteInBatch(Iterable<Board> entities);

    @Override
    void deleteAllInBatch();

    @Override
    Board getOne(Long aLong);

    @Override
    <S extends Board> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Board> List<S> findAll(Example<S> example);

    @Override
    <S extends Board> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Board> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Board> long count(Example<S> example);

    @Override
    <S extends Board> boolean exists(Example<S> example);
}
