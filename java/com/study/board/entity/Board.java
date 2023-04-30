package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Board 클래스가 DB 의 테이블을 의미한다는 뜻
@Data // 필드 가져올 수 있게 해줌
public class Board {
    @Id // PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MariaDB 에서 사용
    private Integer id; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
}
