package org.spring.springbootjpareply.repository;

import org.spring.springbootjpareply.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository   spring 에선 생략해도 repository 인식
public interface FileRepository extends JpaRepository<FileEntity,Long> {

    // file 저장 save()이용




}
