package org.spring.springbootjpareply.service;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.BoardDto;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.FileEntity;
import org.spring.springbootjpareply.repository.BoardRepository;
import org.spring.springbootjpareply.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
  // 1.@Autowired 주입
/*  @Autowired
  private BoardRepository boardRepository;*/
  //2. 생성자로 주입
/*  private BoardRepository boardRepository;
  public BoardService(BoardRepository boardRepository){
    this.boardRepository=boardRepository;
  }*/
  //3.@RequiredArgsConstructor
  private final BoardRepository boardRepository;  // 게시글
  private final FileRepository fileRepository;  // 파일 업로드

  //파일이 있으면 > 게시글 + 파일
  //파일이 없으면 > 게시글만
  
  // 추가,삭제, 수정
  @Transactional                          // 파일 입출력시 예외처리 필요
  public void insertBoad(BoardDto boardDto) throws IOException {

      // 파일이 없으면
    if(boardDto.getBoardFile().isEmpty()){
      // Dto-> Entity
      BoardEntity boardEntity = BoardEntity.toBoardDtoD(boardDto);
      boardRepository.save(boardEntity);
    } else {
      //파일이 있을 경우 -> 파일저장 별도의 테이블 생성 1:N
      // 원본파일,이름 -> 서버 저장이름
      // 저장경로(실제경로) -> 파일 저장
      // 테이블 저장(게시글, 파일)
      MultipartFile boardFile = boardDto.getBoardFile();// 파일
      String originalFilename = boardFile.getOriginalFilename();//원본파일이름
      UUID uuid = UUID.randomUUID(); //random id > 랜덤한 값을 추출하는 클래스
      String newFileName = uuid + "_" + originalFilename; // 저장파일이름 (보안)

      //String filePath="D:\\\\saveFiles\\\\";
      String filePath="C:/saveFiles/"+newFileName; //실제 파일 저장경로+이름
      // 파일 저장 실행
      boardFile.transferTo(new File(filePath));// 저장 ,예외처리 -> 경로에 파일 저장

      // 파일을 Db 테이블에 저장
      // 게시글에 저장
      // Dto > Entity 변경
      
      // 1. 게시글 저장 > 게시글 아이디 get
      // 2. 게시글 아이디에 해당하는 게시글에 파일을 저장
      
      // 1. 게시글 저장
      BoardEntity boardEntity=BoardEntity.toBoardFileDtoD(boardDto); //게시글에 저정 한후 파일저정
      Long boardId = boardRepository.save(boardEntity).getId();  // 저장 실행 > 게시글의 아이디를 가져온다

//      Long boardId=boardRepository.save(BoardEntity.toBoardFileDtoD(boardDto)).getId();

      // 2. 저정장 한후 ID에 해당하는 게시글 조회
      Optional<BoardEntity> boardEntity2=boardRepository.findById(boardId);// id에 해당하는 게시글
      BoardEntity boardEntity3=boardEntity2.get();// id에 해당하는 게시글

//      BoardEntity boardEntity4 = boardRepository.findById(boardId).get();

      // 3. 게시글 Id에 해당하는 파일 테이블에 저장           //게시글 id, 원래 파일이름, 새파일 이름
      FileEntity fileEntity= FileEntity.toFileEntity(boardEntity3,originalFilename,newFileName);
      fileRepository.save(fileEntity);// 파일 저정

    }
  }

  public List<BoardDto> boardListDo() {
    List<BoardDto> boardDtoList = new ArrayList<>();
    //1. DB -> get
    List<BoardEntity> boardEntityList = boardRepository.findAll();
    for (BoardEntity boardEntity : boardEntityList) {
      // Entity -> Dto
      boardDtoList.add(BoardDto.toBoardDtoDo(boardEntity));
    }
    return boardDtoList;
  }

  @Transactional  // 추가,삭제, 수정
  public void upHitDo(Long id) {
    boardRepository.updateHit(id);
  }

  public BoardDto findByBoard(Long id) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
    if (optionalBoardEntity.isPresent()) {
      //optionalBoardEntity.get();// 하나의 Entity get
   /*   BoardDto boardDto=BoardDto.toBoardDtoDo(optionalBoardEntity.get());
      return boardDto;*/

      return BoardDto.toBoardDtoDo(optionalBoardEntity.get());
    } else {
        return  null;
    }
  }

}
