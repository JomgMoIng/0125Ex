package org.spring.springbootjpareply.controller;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.repository.ReplyRepository;
import org.spring.springbootjpareply.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

  private  final ReplyService replyService;

  @PostMapping("/replyWrite")
  public String replyWrite1(@ModelAttribute ReplyDto replyDto,Model model){

    // 확인 boardId 확->    1.덧글저장
     Long result= replyService.insertReplyDo(replyDto); // 확인 boardId 확->    1.덧글저장
      // 덧글 리스트 -> 게시글 id(덧글을 단 게시글의 id)의 리스트만 get
    System.out.println("id "+replyDto.getBaordId());
     List<ReplyDto> replyDtoList=
             replyService.replyDtoListDo(replyDto.getBaordId());
      //덧글을 작성하고 -> 게시글이 존재하는지 확인 -> DTO-> Entity -> 저장
    // 상세페이지에   글번호와 덧글리스트를 가지고 같이 보내야된다.
    model.addAttribute("replyDtoList",replyDtoList);
    //게시글 id  -> replyDto.getBoardId()
      return "redirect:/board/detail/"+replyDto.getBaordId();
  }















/*
  @PostMapping("/replyWrite")
  public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model){

  */
/* Long rs=replyService.insertReplyDo(replyDto);

    List<ReplyDto> replyDtoList = replyService.findReplyList(replyDto.getBoardId());
    model.addAttribute("replyDtoList", replyDtoList);
    return "redirect:/board/detail/"+replyDto.getBoardId();*//*

  }
*/











}
