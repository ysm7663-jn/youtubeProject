package com.example.youtubeProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자
@Getter // 모든 Getter 생성
public class Article {

    @Id
    @GeneratedValue // 자동 생성 에노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    /*
    @Getter
    public Long getId() {
        return id;
    }
    */

    /*
    @AllArgsConstructor로 대체
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    */

    /*
    @ToString으로 대체
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */

}