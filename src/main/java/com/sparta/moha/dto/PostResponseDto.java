package com.sparta.moha.dto;

import com.sparta.moha.model.ImageUrl;
import com.sparta.moha.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private final Long postId;
    private final String title;
    private final String desc;
    private final List<ImageUrl> imageUrls;
    private final String category;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.title = post.getTitle();
        this.desc = post.getDesc();
        this.imageUrls = post.getImageUrlList();
        this.category = post.getCategory();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
