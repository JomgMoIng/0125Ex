package org.spring.springbootjpareply.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.entity.BoardEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FileDto {


    public Long id;  //파일 번호

    private String oldFileName; // 원래 이름

    private String newFileName; // DB 저장 이름

    private BoardEntity boardEntity;

    private LocalDateTime createTime; // 처음 글작성 시간

    private LocalDateTime updateTime;// 글 수정 시간


}
