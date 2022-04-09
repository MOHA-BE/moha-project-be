package com.sparta.moha.model;

import com.sparta.moha.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post extends Timestamped{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String desc; // 내용

    @Column
    private String category; // 지역 카테고리

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POST_NAME")
    private List<ImageUrl> imageUrlList;

    public Post(PostRequestDto requestDto, User user, List<ImageUrl> imageUrlList) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.category = requestDto.getCategory();
        this.user = user;
        this.imageUrlList = imageUrlList;
    }

    // 수정
    public void updatePost(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.category = requestDto.getCategory();
        this.imageUrlList = requestDto.getImageUrlList();
    }
}
