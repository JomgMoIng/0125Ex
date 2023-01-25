package org.spring.springbootjpareply.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.dto.ReplyDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "jpa_reply_tb")
public class ReplyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="reply_id")
  public Long id;

  @Column(nullable = false)
  public String replyWriter;

  @Column(nullable = false,length = 500)
  public String replyContent ;

  // N:1
  // 덧글 작성시 jpa_board_tb테이블 board_id
  @ManyToOne
  @JoinColumn(name = "board_id")
  private BoardEntity boardEntity;

  // 공통 Entity -> 별도로 공통 Entity만들어서 extends
  @CreationTimestamp  // 처음 글작성 시 자동으로 시간이 설정
  @Column(updatable = false)   // update -> 적용 X
  private LocalDateTime createTime; // 처음 글작성 시간1

  @UpdateTimestamp  // 처음 글 수정 시 자동으로 시간이 설정
  @Column(insertable = false)   // 처음 생성시 -> 적용 X
  private LocalDateTime updateTime;// 글 수정 시간

  public static ReplyEntity toInsertEntity(ReplyDto replyDto,
                                                   BoardEntity boardEntity) {
  ReplyEntity replyEntity=new ReplyEntity();

  replyEntity.setReplyWriter(replyDto.getReplyWriter());
  replyEntity.setReplyContent(replyDto.getReplyContent());
  replyEntity.setBoardEntity(boardEntity);

  return  replyEntity;
  }
}

