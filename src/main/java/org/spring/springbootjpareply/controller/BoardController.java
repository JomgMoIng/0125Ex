package org.spring.springbootjpareply.controller;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.BoardDto;
import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.service.BoardService;
import org.spring.springbootjpareply.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;
  private  final ReplyService replyService;

  @GetMapping("/write")  //보이기, DB데이터를 get
  public String write(){
    return "boardWrite";
  }

  @PostMapping("/write") // DB추가
  public String writeOk(@ModelAttribute BoardDto boardDto) throws IOException {

    boardService.insertBoad(boardDto);
    // 회원가입 -> boardList이동
    return "redirect:/board/boardList";
  }

  @GetMapping("/boardList")
  public String boardList(Model model){
  //DB 데이터를 가지고  -> View boardList.html
    List<BoardDto> boardDtoList=boardService.boardListDo();

    model.addAttribute("boardDtoList",boardDtoList);

    return "boardList";
  }
  @GetMapping("/detail/{id}")
  public String boardDetail(@PathVariable Long id,Model model) {
    {
      boardService.upHitDo(id);  // 조회수 증가
      //id해당하는 게시글
      BoardDto boardDto = boardService.findByBoard(id);
      if (boardDto != null) {
        System.out.println("게시글 OK-> 글상세보기로 이동");
        model.addAttribute("board", boardDto);
        // 덧글 목록 get
        List<ReplyDto> replyDtoList= replyService.replyDtoListDo(id);
        model.addAttribute("replyDtoList",replyDtoList);
        return "boardDetail";

      } else {
        System.out.println("게시글 NO-> 글작성 페이지로 이동");
        return "redirect:/board/write";
      }

    }
  }




}
