package org.spring.springbootjpareply.service;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;
import org.spring.springbootjpareply.repository.BoardRepository;
import org.spring.springbootjpareply.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

  //게시글 id확인
  private final BoardRepository boardRepository;
  private final ReplyRepository replyRepository;

  public Long insertReplyDo(ReplyDto replyDto) {
    // 테이블의 게시글 id가 있는지 확인
    Optional<BoardEntity> optionalReplyEntity
            = boardRepository.findById(replyDto.getBaordId());
    if (optionalReplyEntity.isPresent()) {
      // BoardEntity- BoardDto
      BoardEntity boardEntity = optionalReplyEntity.get();
      // save-> Entity
      // replyDto -> ReplyEntity
      ReplyEntity replyEntity =
              ReplyEntity.toInsertEntity(replyDto, boardEntity);
      // 작성자, 내용  , 글번호에 해당하는 게시글
      return  replyRepository.save(replyEntity).getId();// id값 반환
    } else {
      return null;
    }
  }


  public List<ReplyDto> replyDtoListDo(Long boardId) {
    //연관관계에 있는 게시글 -> BoardEntity
    BoardEntity boardEntity = boardRepository.findById(boardId).get();
    //  findAllByBoardEntity -> ReplyEntity에서 boardId에
    //   작성 덧글 게시글 목록
    List<ReplyEntity> replytEntityList =
                      //BoardEntity id에 해당하는 덧글 리스트 get
            //replyRepository.findAllByBoardEntity(boardEntity);
            //replyRepository.findAllByBoardEntityId(boardEntity);
            replyRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
    //* EntityList -> DTOList *//*
    List<ReplyDto> replytDTOList = new ArrayList<>();

    for (ReplyEntity replyEntity : replytEntityList) {
      ReplyDto replytDTO = ReplyDto.toReplyDto(replyEntity, boardId);
      replytDTOList.add(replytDTO);
    }
    return replytDTOList;

  }

}
