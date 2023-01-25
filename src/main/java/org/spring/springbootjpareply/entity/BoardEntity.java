package org.spring.springbootjpareply.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.dto.BoardDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "jpa_board_tb")
public class BoardEntity {

  @Id // 기본키
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
  @Column(name = "board_id")
  public Long id;  //글번호

  @Column(nullable = false)
  private String title;//글제목

  @Column(nullable = false, length = 500)
  private String content;//글내용

  @Column(nullable = false)
  private String writer;//글작성자

  @Column(columnDefinition = "integer default 0", nullable = false)
  private int hit; // 조회수

  @Column(nullable = false)
  private String boardPw;  //글비빌번호 -> 글삭제 시 사용 할 수 있다.

  @CreationTimestamp  // 처음 글작성 시 자동으로 시간이 설정
  @Column(updatable = false)   // update -> 적용 X
  private LocalDateTime createTime; // 처음 글작성 시간1

  @UpdateTimestamp  // 처음 글 수정 시 자동으로 시간이 설정
  @Column(insertable = false)   // 처음 생성시 -> 적용 X
  private LocalDateTime updateTime;// 글 수정 시간

  //   게시글 조회 시  덧글목록도 보이게
  // mappedBy = "boardEntity"  // 연관관계의 주인(외래키설정 테이블)
  // 1: 다 -> 1의 Entity에 작성
  @OneToMany(mappedBy = "boardEntity",
              cascade = CascadeType.REMOVE,orphanRemoval = true )
  private List<ReplyEntity> replyEntityList=new ArrayList<>();

  // 게시글 1:N 파일                    cascade remove 게시글이 삭제되면 파일목록도 삭제
  @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<FileEntity> fileEntities = new ArrayList<>();

  @Column(nullable = false)
  private int attachFile; // 파일 유무 확인(1,0)

  // Dto -> Entity
  //글작성 시에는 id, createTime, updateTime 는 사용하시 않는다(자동추가)
  // 글쓰기form -> Dto
  public  static BoardEntity toBoardDtoD(BoardDto boardDto){
    BoardEntity boardEntity=new BoardEntity();
    boardEntity.setTitle(boardDto.getTitle());
    boardEntity.setContent(boardDto.getContent());
    boardEntity.setHit(boardDto.getHit());
    boardEntity.setWriter(boardDto.getWriter());
    boardEntity.setBoardPw(boardDto.getBoardPw());
    boardEntity.setAttachFile(0); // 파일이 없다
    return boardEntity;
  }
  
  // Dto > Entity 변경
  public static BoardEntity toBoardFileDtoD(BoardDto boardDto) {
    BoardEntity boardEntity=new BoardEntity();
    boardEntity.setTitle(boardDto.getTitle());
    boardEntity.setContent(boardDto.getContent());
    boardEntity.setHit(boardDto.getHit());
    boardEntity.setWriter(boardDto.getWriter());
    boardEntity.setBoardPw(boardDto.getBoardPw());
    boardEntity.setAttachFile(1); // 파일이 있다 > 파일이 있으면 > fileEntity에서 파일 정보를 가져온다
    return boardEntity;
  }


  //글수정 시에는 id 를 추가 ,  updateTime 는 자동 createTime시간추가








}
