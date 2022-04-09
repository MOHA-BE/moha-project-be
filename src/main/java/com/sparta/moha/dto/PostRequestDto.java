package com.sparta.moha.dto;

import com.sparta.moha.model.ImageUrl;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String desc;
    private List<ImageUrl> imageUrlList;
    private String category;
}
