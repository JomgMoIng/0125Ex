package org.spring.springbootjpareply.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardDto {

  public Long id;  //글번호
    
  //공란이면 > 에러메시지 출력
  @NotEmpty(message = "공란입니다. 제목을 입력하세요")
  @NotBlank(message = "Null입니다!")
  @NonNull  // 공백과 Null 허용하지 않는다
  private String title;//글제목

  @NotEmpty(message = "공란입니다. 제목을 입력하세요")
  @Size(min = 5, max = 250) //5글자 이상~ 250글자 미만
  private String content;//글내용

  @NotEmpty(message = "공란입니다. 제목을 입력하세요")
  private String writer;//글작성자

  private int hit; // 조회수

  private String boardPw;  //글비빌번호 -> 글삭제 시 사용 할 수 있다.

  private LocalDateTime createTime; // 처음 글작성 시간1

  private LocalDateTime updateTime;// 글 수정 시간
    
  // 파일 업로드 파일을 저장 할 수 있는 객체
  private MultipartFile boardFile;
    
    
  // 파일이 있을 경우 처리
    
  private String oldFileName; // 원래 이름

  private String newFileName; // DB 저장 이름

  private int attachFile; // 파일 유무 확인(1,0)

  // Entity -> Dto    글목록 , 글상세내역
  public static BoardDto toBoardDtoDo(BoardEntity boardEntity){
        BoardDto boardDto=new BoardDto();// @NoArgsConstructor
        boardDto.setId(boardEntity.getId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setWriter(boardEntity.getWriter());
        boardDto.setHit(boardEntity.getHit());
        boardDto.setBoardPw(boardEntity.getBoardPw());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());
        if(boardEntity.getAttachFile()==0){
            boardDto.setAttachFile(boardEntity.getAttachFile());  //파일이 없을 경우 0
        } else{
            boardDto.setAttachFile(boardEntity.getAttachFile()); // 파일이 있을 경우 1

            // 파일이 있을 경우
            // 원래 파일 이름을 가져온다 > DB에서 파일의 원래 이름 가져오는 방법
            boardDto.setOldFileName(boardEntity.getFileEntities().get(0).getOldFileName());

            // 새 파일 이름(DB저장이름) 가져온다 > DB에서 파일의 새파일 이름 가져오는 방법
            boardDto.setNewFileName(boardEntity.getFileEntities().get(0).getNewFileName());



        }

        return boardDto;
  }





}
