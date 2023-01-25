package org.spring.springbootjpareply.repository;

import org.spring.springbootjpareply.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // 생략
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

  @Modifying //수정,삭제,추가
  @Query(value = "update BoardEntity b set b.hit=b.hit+1 where b.id=:id ")
  void updateHit(Long id);
  // 반드시 Entity객체를 사용
}
