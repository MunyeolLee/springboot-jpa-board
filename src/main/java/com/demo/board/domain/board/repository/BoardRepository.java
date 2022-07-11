package com.demo.board.domain.board.repository;

import com.demo.board.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    Page<Board> findByTitleContaining(String title, Pageable pageable);

    Page<Board> findByContentContaining(String content, Pageable pageable);

}
