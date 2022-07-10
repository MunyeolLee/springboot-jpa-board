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

    List<Board> findByTitleContainingOrContentContaining(String title, String content, Sort sort);

    List<Board> findByTitleContaining(String title, Sort sort);

    List<Board> findByContentContaining(String content, Sort sort);

}
