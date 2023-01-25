package org.spring.springbootjpareply.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "jpa_file_tb")
public class FileEntity {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
    @Column(name = "file_id")
    public Long id;  //파일 번호

    @Column(nullable = false)
    private String oldFileName; // 원래 이름

    @Column(nullable = false)
    private String newFileName; // DB 저장 이름

    // 파일 N : 1 게시글
    @ManyToOne(fetch = FetchType.LAZY)  // fetch = FetchType.LAZY 기본
    @JoinColumn(name = "board_id")      // 참조하는 테이블의 기본키 또는 기본키 성질
    private BoardEntity boardEntity;

    @CreationTimestamp  // 처음 파일 생성 시 자동으로 시간이 설정
    @Column(updatable = false)   // update -> 적용 X
    private LocalDateTime createTime; // 처음 글작성 시간

    @UpdateTimestamp  // 처음 파일 수정 시 자동으로 시간이 설정
    @Column(insertable = false)   // 처음 생성시 -> 적용 X
    private LocalDateTime updateTime;// 글 수정 시간


    public static FileEntity toFileEntity(BoardEntity boardEntity3, String originalFilename, String newFileName) {

        FileEntity fileEntity = new FileEntity();

        fileEntity.setBoardEntity(boardEntity3);
        fileEntity.setOldFileName(originalFilename);
        fileEntity.setNewFileName(newFileName);

        return fileEntity;
    }
}
