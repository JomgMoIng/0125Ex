package org.spring.springbootjpareply.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReplyDto {

  public Long id;

  public String replyWriter;

  public String replyContent ;

  public Long baordId;    // BoardEntity  의  board_id  //게시글 조회시

  private LocalDateTime createTime; // 처음 글작성 시간1

  private LocalDateTime updateTime;// 글 수정 시간


  public static ReplyDto toReplyDto(ReplyEntity replytEntity,
                                    Long boardId) {
    ReplyDto replytDTO = new ReplyDto();
    replytDTO.setId(replytEntity.getId());
    replytDTO.setReplyWriter(replytEntity.getReplyWriter());
    replytDTO.setReplyContent(replytEntity.getReplyContent());
    replytDTO.setCreateTime(replytEntity.getCreateTime());
    replytDTO.setBaordId(boardId); // /baord/detail/{id}
    return replytDTO;
  }


}
