package com.sparta.minimoha.model;

import com.sparta.minimoha.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Post extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String contents;

    @Column
    private String category; // 지역 카테고리

    @Column
    private String imageUrl;

    @Column
    private String imageFilename;

    @Column
    private String name;

    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
    }
    //이미지 저장
    public Post(PostRequestDto requestDto, String imageUrl, String imageFilename){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.imageUrl = imageUrl;
        this.imageFilename = imageFilename;
        this.name = requestDto.getName();
    }
    public void updatePost(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
    }
}
