package org.spring.springbootjpareply.repository;

import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
  List<ReplyEntity> findAllByBoardEntity(BoardEntity boardEntity);

  List<ReplyEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

  void findAllByBoardEntityId(BoardEntity boardEntity);
}














